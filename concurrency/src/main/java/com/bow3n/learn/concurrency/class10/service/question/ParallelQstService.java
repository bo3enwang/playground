package com.bow3n.learn.concurrency.class10.service.question;

import com.bow3n.learn.concurrency.class10.assist.SL_QuestionBank;
import com.bow3n.learn.concurrency.class10.vo.QuestionInCacheVO;
import com.bow3n.learn.concurrency.class10.vo.QuestionInDBVo;
import com.bow3n.learn.concurrency.class10.vo.TaskResultVO;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * @author bowen
 */
public class ParallelQstService {
    private final static int THREAD_COUNT = Runtime.getRuntime().availableProcessors() * 2;

    private static ExecutorService makeQuestionService = Executors.newFixedThreadPool(THREAD_COUNT);

    // 已处理题目缓存
    private static ConcurrentHashMap<Integer, QuestionInCacheVO> questionCache = new ConcurrentHashMap<>();

    // 正常处理的题目
    private static ConcurrentHashMap<Integer, Future<QuestionInCacheVO>> processingCache = new ConcurrentHashMap<>();

    public static TaskResultVO makeQuestion(Integer questionId) {
        QuestionInCacheVO questionInCacheVO = questionCache.get(questionId);
        if (questionInCacheVO == null) {
            System.out.println(String.format("加载题目进入缓存 [%d]", questionId));
            Future<QuestionInCacheVO> questionInCacheVOFuture = getFuture(questionId);
            return new TaskResultVO(questionInCacheVOFuture);
        } else {
            String questionSha = SL_QuestionBank.getSha(questionId);
            if (Objects.equals(questionInCacheVO.getQuestionSha(), questionSha)) {
                System.out.println(String.format("从缓存中读取到题目 [%d]", questionId));
                return new TaskResultVO(questionInCacheVO.getQuestionDetail());
            } else {
                System.out.println(String.format("更新缓存中的任务 [%d]", questionId));
                return new TaskResultVO(getFuture(questionId));
            }
        }
    }

    private static Future<QuestionInCacheVO> getFuture(Integer questionId) {
        Future<QuestionInCacheVO> questionInCacheVOFuture = processingCache.get(questionId);
        if (questionInCacheVOFuture == null) {
            QuestionInDBVo questionInDBVo = SL_QuestionBank.getQuetion(questionId);
            MakeQuestionTask makeQuestionTask = new MakeQuestionTask(questionId, questionInDBVo);
            //避免提交两次 ， 先在Map中占位，后提交
            FutureTask<QuestionInCacheVO> futureTask = new FutureTask<>(makeQuestionTask);
            questionInCacheVOFuture = processingCache.putIfAbsent(questionId, futureTask);
            if (questionInCacheVOFuture == null) {
                questionInCacheVOFuture = futureTask;
                makeQuestionService.execute(futureTask);
                System.out.println(String.format("任务启动 [%d]", questionId));
            } else {
                System.out.println(String.format("有其他线程已经启动任务 [%d]，本任务无需开启", questionId));
            }
        }
        return questionInCacheVOFuture;
    }

    private static class MakeQuestionTask implements Callable<QuestionInCacheVO> {

        private final Integer questionId;
        private final QuestionInDBVo questionInDBVo;

        private MakeQuestionTask(Integer questionId, QuestionInDBVo questionInDBVo) {
            this.questionId = questionId;
            this.questionInDBVo = questionInDBVo;
        }

        @Override
        public QuestionInCacheVO call() throws Exception {
            try {
                String detail = BaseQuestionProcessor.makeQuestion(questionId,
                        SL_QuestionBank.getQuetion(questionId).getDetail());
                String questionSha = questionInDBVo.getSha();
                QuestionInCacheVO questionInCacheVO = new QuestionInCacheVO(detail, questionSha);
                questionCache.put(questionId, questionInCacheVO);
                return questionInCacheVO;
            } finally {
                processingCache.remove(questionId);
            }
        }
    }

}

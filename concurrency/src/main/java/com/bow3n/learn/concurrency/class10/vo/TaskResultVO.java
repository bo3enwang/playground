package com.bow3n.learn.concurrency.class10.vo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author bowen
 */
public class TaskResultVO {

    private final String questionDetail;
    private final Future<QuestionInCacheVO> questionFuture;


    public TaskResultVO(String questionDetail) {
        this.questionDetail = questionDetail;
        this.questionFuture = null;
    }

    public TaskResultVO(Future<QuestionInCacheVO> questionFuture) {
        this.questionDetail = null;
        this.questionFuture = questionFuture;
    }

//    public String getDetail() {
//        try {
//
//            if (this.questionDetail == null) {
//                return this.questionDetail;
//            } else {
//                return this.questionFuture.get().getQuestionDetail();
//            }
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public String getQuestionDetail() {
        return questionDetail;
    }

    public Future<QuestionInCacheVO> getQuestionFuture() {
        return questionFuture;
    }

    @Override
    public String toString() {
        if (getQuestionDetail() != null) {
            return getQuestionDetail();
        } else {
            try {
                return getQuestionFuture().get().getQuestionDetail();
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("获取题目详情失败");
                e.printStackTrace();
                return null;
            }
        }
    }
}

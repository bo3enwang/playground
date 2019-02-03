package com.bow3n.learn.concurrency.class10;


import com.bow3n.learn.concurrency.class10.assist.CreatePendingDocs;
import com.bow3n.learn.concurrency.class10.assist.SL_QuestionBank;
import com.bow3n.learn.concurrency.class10.service.ProduceDocService;
import com.bow3n.learn.concurrency.class10.vo.SrcDocVo;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * RPC 服务端
 * <p>
 * 生产者 消费者模式
 */
public class RpcModeWeb {
    private final static int THREAD_COUNT = Runtime.getRuntime().availableProcessors() * 2;

    private static ExecutorService docMakeService = Executors.newFixedThreadPool(THREAD_COUNT);
    private static ExecutorService docUploadService = Executors.newFixedThreadPool(THREAD_COUNT);

    private static CompletionService<String> docCompletionService = new ExecutorCompletionService<>(docMakeService);

    private static CompletionService<String> docUploadCompletionService = new ExecutorCompletionService<>(docUploadService);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<SrcDocVo> questionList = initQuestion();
        long startTotal = System.currentTimeMillis();
        for (SrcDocVo srcDocVo : questionList) {
            docCompletionService.submit(new MakeDocTask(srcDocVo));
        }
        for (SrcDocVo srcDocVo : questionList) {
            Future<String> future = docCompletionService.take();
            docUploadCompletionService.submit(new UploadTask(future.get()));
        }

        for (SrcDocVo srcDocVo : questionList) {
            docUploadCompletionService.take().get();
        }
        System.out.println("------------共耗时："
                + (System.currentTimeMillis() - startTotal) + "ms-------------");
    }

    private static List<SrcDocVo> initQuestion() {
        System.out.println("题库开始初始化...........");
        SL_QuestionBank.initBank();
        System.out.println("题库初始化完成。");
        return CreatePendingDocs.makePendingDoc(60);
    }


    private static class MakeDocTask implements Callable<String> {

        private SrcDocVo pendingDocVo;

        private MakeDocTask(SrcDocVo pendingDocVo) {
            this.pendingDocVo = pendingDocVo;
        }

        @Override
        public String call() throws Exception {
            System.out.println("开始处理文档：" + pendingDocVo.getDocName() + ".......");
            long start = System.currentTimeMillis();
            String localName = ProduceDocService.makeDocWithCache(pendingDocVo);
            System.out.println("文档" + localName + "生成耗时：" + (System.currentTimeMillis() - start) + "ms");
            return localName;
        }
    }

    private static class UploadTask implements Callable<String> {
        private String filePath;

        private UploadTask(String filePath) {
            this.filePath = filePath;
        }

        @Override
        public String call() throws Exception {
            long start = System.currentTimeMillis();
            String remoteUrl = ProduceDocService.upLoadDoc(filePath);
            System.out.println("已上传至[" + remoteUrl + "]耗时：" + (System.currentTimeMillis() - start) + "ms");
            return remoteUrl;
        }
    }


}

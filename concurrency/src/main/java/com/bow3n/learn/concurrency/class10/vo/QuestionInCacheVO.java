package com.bow3n.learn.concurrency.class10.vo;

/**
 * @author bowen
 */
public class QuestionInCacheVO {
    private final String questionDetail;
    private final String questionSha;

    public QuestionInCacheVO(String questionDetail, String questionSha) {
        this.questionDetail = questionDetail;
        this.questionSha = questionSha;
    }


    public String getQuestionDetail() {
        return questionDetail;
    }

    public String getQuestionSha() {
        return questionSha;
    }
}

package com.lxchild.bean;

/**
 * Created by LXChild on 29/10/2016.
 */

public class OtherTopicBean {
    private String question;
    private String answer;

    public OtherTopicBean(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

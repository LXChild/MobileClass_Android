package com.lxchild.bean;

/**
 * Created by LXChild on 28/10/2016.
 */

public class SingleChoiceBean {
    private String question_name;
    private String answer_a;
    private String answer_b;
    private String answer_c;
    private String answer_d;
    private String answer_r;

    public SingleChoiceBean(String question_name, String answer_a, String answer_b, String answer_c, String answer_d, String answer_r) {
        this.question_name = question_name;
        this.answer_a = answer_a;
        this.answer_b = answer_b;
        this.answer_c = answer_c;
        this.answer_d = answer_d;
        this.answer_r = answer_r;
    }

    public String getQuestion_name() {
        return question_name;
    }

    public void setQuestion_name(String question_name) {
        this.question_name = question_name;
    }

    public String getAnswer_a() {
        return answer_a;
    }

    public void setAnswer_a(String answer_a) {
        this.answer_a = answer_a;
    }

    public String getAnswer_b() {
        return answer_b;
    }

    public void setAnswer_b(String answer_b) {
        this.answer_b = answer_b;
    }

    public String getAnswer_c() {
        return answer_c;
    }

    public void setAnswer_c(String answer_c) {
        this.answer_c = answer_c;
    }

    public String getAnswer_d() {
        return answer_d;
    }

    public void setAnswer_d(String answer_d) {
        this.answer_d = answer_d;
    }

    public String getAnswer_r() {
        return answer_r;
    }

    public void setAnswer_r(String answer_r) {
        this.answer_r = answer_r;
    }
}

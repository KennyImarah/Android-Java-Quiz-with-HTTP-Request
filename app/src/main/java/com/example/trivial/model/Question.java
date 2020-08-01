package com.example.trivial.model;

public class Question {

    private String answer;
    private boolean answerTrue;

    public Question(String answer, boolean answerTrue) {
        this.answer = answer;
        this.answerTrue = answerTrue;
    }

    public Question() {
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isAnswerTrue() {
        return answerTrue;
    }

    // override toString method
    @Override
    public String toString() {
        return "Question{" +
                "answer='" + answer + '\'' +
                ", answerTrue=" + answerTrue +
                '}';
    }

    public void setAnswerTrue(boolean answerTrue) {
        this.answerTrue = answerTrue;



    }
}

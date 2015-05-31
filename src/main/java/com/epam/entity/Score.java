package com.epam.entity;

public class Score extends BaseEntity {
    private String score;

    public Score() {
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "score='" + score + '\'' +
                '}';
    }
}

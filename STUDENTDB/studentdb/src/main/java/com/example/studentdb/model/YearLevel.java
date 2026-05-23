package com.example.studentdb.model;

public enum YearLevel {
    FIRST_YEAR("1st Year"),
    SECOND_YEAR("2nd Year"),
    THIRD_YEAR("3rd Year"),
    FOURTH_YEAR("4th Year");

    private final String label;

    YearLevel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
package ru.job4j.ood.lsp;

public class Student {

    private int diplomaMark;
    private int graduationMark;
    private int examMark;
    private boolean isAdmit;

    public Student(int diplomaMark, int graduationMark, int examMark, boolean isAdmit) {
        this.diplomaMark = diplomaMark;
        this.graduationMark = graduationMark;
        this.examMark = examMark;
        this.isAdmit = isAdmit;
    }

    protected void validate(int diplomaMark, int graduationMark, int examMark) {
        if (diplomaMark > 5 || diplomaMark < 2) {
            throw new IllegalArgumentException("Invalid mark!");
        }
        if (graduationMark > 5 || graduationMark < 2) {
            throw new IllegalArgumentException("Invalid mark!");
        }
        if (examMark > 5 || examMark < 2) {
            throw new IllegalArgumentException("Invalid mark!");
        }
    }

    public int getDiplomaMark() {
        return diplomaMark;
    }

    public int getGraduationMark() {
        return graduationMark;
    }

    public int getExamMark() {
        return examMark;
    }

    public int getFinalGrade() {
        if (isAdmit) {
            throw new IllegalArgumentException("Student does not admit!");
        }
        validate(diplomaMark, graduationMark, examMark);
        return (diplomaMark + graduationMark + examMark) / 3;
    }

    @Override
    public String toString() {
        return "Student{"
                + "diplomaMark=" + diplomaMark
                + ", graduationMark=" + graduationMark
                + ", examMark=" + examMark
                + ", isAdmit=" + isAdmit
                + '}';
    }
}

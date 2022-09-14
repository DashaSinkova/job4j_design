package ru.job4j.ood.lsp;

public class MasterStudent extends Student {

    public MasterStudent(int diplomaMark, int graduationMark, int examMark, boolean isAdmit) {
        super(diplomaMark, graduationMark, examMark, isAdmit);
    }
    @Override
    public int getFinalGrade() {
        return (getGraduationMark() + getDiplomaMark() + getExamMark()) / 3;
    }

    public static void main(String[] args) {
        Student student = new MasterStudent(4, 5, 5, false);
        Student student1 = new MasterStudent(90, 92, 80, false);
        System.out.println(student1.getFinalGrade());

        Student student2 = new Student(90, 92, 80, true);
        System.out.println(student2.getFinalGrade());
    }
}

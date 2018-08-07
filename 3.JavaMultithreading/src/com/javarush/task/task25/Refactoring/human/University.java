package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {

    public String name;
    public int age;
    private List<Student> students = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        //TODO:
        for (Student student : students) {
            if (student.getAverageGrade() == averageGrade) return student;
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        Student studentWithMaxAverageGrade = students.get(0);
        for (Student studentMax : students) {
            if (studentMax.getAverageGrade() >= studentWithMaxAverageGrade.getAverageGrade()) {
                studentWithMaxAverageGrade = studentMax;
            }
        }

        return studentWithMaxAverageGrade;
    }

    public Student getStudentWithMinAverageGrade() {
        //TODO:
        Student studentWithMinAverageGrade = students.get(0);
        for (Student studentMin : students) {
            if (studentMin.getAverageGrade() < studentWithMinAverageGrade.getAverageGrade()) {
                studentWithMinAverageGrade = studentMin;
            }
        }

        return studentWithMinAverageGrade;
    }

    public void expel(Student student) {
        for (Student studentExpel : students) {
            if (studentExpel.equals(student)) students.remove(studentExpel);
        }

    }

}

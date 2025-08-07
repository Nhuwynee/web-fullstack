package ss5_polymorphism;

import java.util.Objects;

public class Student {// Java tự ngầm định extends Object
    private int id;
    private String name;
    private double score;


    /// Tạo constructor + getter & setter -> Hãy tự tạo lại
    public Student() {
    }

    public Student(int id, String name, double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }


    /// Override lại phương thức của lớp Object
    /// Cách 1: dùng getClass()
//    @Override
//    public boolean equals(Object o) {
//        if (o == null || getClass() != o.getClass()) return false;
//        Student student = (Student) o;
//        return id == student.id && Objects.equals(name, student.name);
//    }

    /// Cách 2: dùng instanceof -> Cách này có gì khác với cách trên???
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Student student)) return false;
        return id == student.id && Objects.equals(name, student.name);
    }


    /// Override lại phương thức toString() của lớp Object
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
/// Hoặc tự viết lại toString()
//    @Override
//    public String toString() {
//        return String.format("ID: %s, name: %s, score: %s", this.id, this.name, this.score);
//    }
}


/// Override equals() sử dụng instanceof
class GraduateStudent extends Student {
    String thesis;

    public GraduateStudent(int id, String name, double score) {
        super(id, name, score);
    }
}

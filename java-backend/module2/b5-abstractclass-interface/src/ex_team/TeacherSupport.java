package ex_team;

import java.util.ArrayList;

public class TeacherSupport extends Person implements ITeacher {
    private int soBuoiThucHanh;
    private final ArrayList<Teacher> teachers = new ArrayList<>();


    @Override
    public double getSalary() {
        return 0;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Teacher teacher) {
        this.teachers.add(teacher);
    }
}

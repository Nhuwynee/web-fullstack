public class Teacher {

    private static int autoId = 1;

    int id;
    String name;
    int age;
    String subject;
    double teachHours;
    static int countTeacher;

    public Teacher() {
        this.id = autoId++;
        countTeacher++;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getTeachHours() {
        return teachHours;
    }

    public void setTeachHours(double teachHours) {
        this.teachHours = teachHours;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Tên: %s | Tuổi: %d | Môn: %s | Giờ dạy: %.2f",
                id, name, age, subject, teachHours);
    } 
}

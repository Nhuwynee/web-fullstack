package chieu;

import java.util.Objects;
import java.util.Scanner;

public abstract class Person {
    private String id;
    private String fullName;
    private int age;
    private String email;

    public Person() {
    }

    public Person(String id, String fullName, int age, String email) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.email = email;
    }

    public void input() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Nhập tên: ");
            this.fullName = sc.nextLine().trim();
            if (this.fullName.matches("[a-zA-ZÀ-Ỹà-ỹ\\s]+")) {
                break;
            } else {
                System.out.println("Tên không hợp lệ! Không chứa số hoặc ký tự đặc biệt.\n");
            }
        }

        while (true) {
            System.out.print("Nhập tuổi: ");
            if (sc.hasNextInt()) {
                this.age = sc.nextInt();
                if (this.age < 0) {
                    System.out.println("Tuổi không hợp lệ! Phải >= 0.\n");
                    continue;
                }
                sc.nextLine();
                break;
            } else {
                System.out.println("Tuổi không hợp lệ! Nhập số nguyên.\n");
                sc.nextLine();
            }
        }

        while (true) {
            System.out.print("Nhập email: ");
            this.email = sc.nextLine().trim();
            if (this.email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
                break;
            } else {
                System.out.println("Email không hợp lệ! Vui lòng nhập đúng định dạng (vd: ten@gmail.com).\n");
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n"
                + "Họ tên: " + fullName + "\n"
                + "Tuổi: " + age + "\n"
                + "Email: " + email + "\n";
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

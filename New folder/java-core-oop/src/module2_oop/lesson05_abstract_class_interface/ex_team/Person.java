package ex_team;

import java.util.Scanner;

public abstract class Person {
    private String id;
    private String name;
    private int age;
    private String email;

    public Person() {
    }

    public Person(String id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;

    }

    public void input(Scanner sc) {
        // Nhập tên
        while (true) {
            System.out.print("Nhập tên: ");
            this.name = sc.nextLine().trim();
            if (this.name.matches("[a-zA-ZÀ-Ỹà-ỹ\\s]+")) break;
            else System.out.println("❌ Tên không hợp lệ! Không chứa số hoặc ký tự đặc biệt.");
        }

        // Nhập tuổi
        while (true) {
            System.out.print("Nhập tuổi: ");
            if (sc.hasNextInt()) {
                this.age = sc.nextInt();
                if (this.age < 0) {
                    System.out.println("❌ Tuổi không hợp lệ! Phải >= 0.");
                    continue;
                }
                sc.nextLine(); // Clear buffer
                break;
            } else {
                System.out.println("❌ Tuổi không hợp lệ! Nhập số nguyên.");
                sc.nextLine(); // Clear buffer
            }
        }
        // Nhập email
        while (true) {
            System.out.print("Nhập email: ");
            this.email = sc.nextLine().trim();
            if (this.email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) break;
            else System.out.println("❌ Email không hợp lệ! Vui lòng nhập đúng định dạng (vd: ten@gmail.com).");
        }


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // ===== toString (tuỳ chọn) =====
    @Override
    public String toString() {
        return String.format("| %-6s | %-20s | %-8d | %-25s", id, name, age, email);
    }
}
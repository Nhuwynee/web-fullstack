package ex_team;

import ex_team.custom_exception.InvalidAgeException;
import ex_team.custom_exception.InvalidEmailException;
import ex_team.custom_exception.NullOrEmptyException;

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

    public void input() throws InvalidEmailException, NumberFormatException, InvalidAgeException {
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
                if (this.age > 0 && this.age < 100) {
                    break;
                }
                throw new InvalidAgeException("Lỗi InvalidAgeException ! Nhập tuổi từ (0 - 100).");
            } else {
                throw new NumberFormatException("Lỗi NullOrEmptyException ! Phải nhập số.");
            }
        }

        while (true) {
            System.out.print("Nhập email: ");
            this.email = sc.nextLine().trim();
            if (this.email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
                break;
            } else {
                throw new InvalidEmailException("Lỗi InvalidEmailException ! : Email không hợp lệ!");
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() throws NullOrEmptyException {
        if (this.fullName == null) {
            throw new NullOrEmptyException("Lỗi NullOrEmptyException - Trường tên bị trống!");
        } else {
            return fullName;
        }
    }

    public void setFullName(String fullName){
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() throws NullOrEmptyException {
        if (this.email == null) {
            throw new NullOrEmptyException("Lỗi NullOrEmptyException ! - Trường email bị trống");
        } else {
            return email;
        }
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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
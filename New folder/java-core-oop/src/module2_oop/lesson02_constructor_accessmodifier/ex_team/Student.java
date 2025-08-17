package ex_team;

import java.util.Scanner;

public class Student {
    private int id;
    private String name;
    private int age;
    private double avgScore;

    private static int autoId = 1;
    private static int countStudent = 0;

    public Student() {
        this.id = autoId++;
        countStudent++;
    }

    public Student(String name, int age, double avgScore) {
        this.id = autoId++;
        this.name = name;
        this.age = age;
        this.avgScore = avgScore;

        countStudent++;
    }

    public void input() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Thêm sinh viên với id #" + (autoId - 1));

        while (true) {
            System.out.print("Nhập tên sinh viên: ");
            this.name = sc.nextLine().trim();
            if (this.name.matches("[a-zA-ZÀ-Ỹà-ỹ\\s]+")) break;
            else System.out.println("Tên không hợp lệ!. Vui lòng nhập tên không chứa ký tự đặt biệt và chữ số");
        }

        while (true) {
            System.out.print("Nhập tuổi: ");
            if (sc.hasNextInt()) {
                this.age = sc.nextInt();
                if (this.age < 0) {
                    System.out.println("Tuổi không hợp lệ!. Vui lòng nhập tuổi lớn hơn 0!");
                    continue;
                }
                sc.nextLine();
                break;
            } else {
                System.out.println("Tuổi không hợp lệ!. Vui lòng nhập tuổi lớn hơn 0!");
                sc.nextLine();
            }
        }

        while (true) {
            System.out.print("Nhập điểm trung bình của sinh viên: ");
            if (sc.hasNextFloat()) {
                this.avgScore = sc.nextFloat();
                if (this.avgScore < 0 || this.avgScore > 10) {
                    System.out.println("Điểm không hợp lệ!. Vui lòng nhập điểm từ 0 - 10!");
                    continue;
                }
                sc.nextLine();
                break;
            } else {
                System.out.println("Điểm không hợp lệ!. Vui lòng nhập điểm từ 0 - 10!");
                sc.nextLine();
            }
        }
    }

    public String getName() {
        return name;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public static int getCountStudent() {
        return countStudent;
    }

    @Override
    public String toString() {
        return String.format("%-5d | %-20s | %-5d | %-8.2f", id, name, age, avgScore);
    }
}

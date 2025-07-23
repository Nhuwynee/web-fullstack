package ex_morning.ex01;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentManager  {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;

        while (true) {
            System.out.print("Nhập số lượng sinh viên (số nguyên dương): ");
            String input = sc.nextLine();
            try {
                n = Integer.parseInt(input);
                if (n > 0) {
                    break;
                } else {
                    System.out.println("⚠️ Vui lòng nhập số nguyên DƯƠNG (> 0).");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Không hợp lệ! Vui lòng nhập một số nguyên dương.");
            }
        }



        ArrayList<Student> students = new ArrayList<>();
        Student student;

        for (int i = 0; i < n; i++) {
            System.out.printf("\n=== Nhập sinh viên thứ %d ===\n", i + 1);
            student = new Student();
            student.input();
            students.add(student);
        }

        System.out.println("\n\n\033[1;34m========== DANH SÁCH SINH VIÊN ==========\033[0m\n");

        for (int i = 0; i < students.size(); i++) {
            System.out.printf("\033[1;33m--- Sinh viên thứ %d ---\033[0m\n", i + 1);
            students.get(i).output();
            System.out.println("=".repeat(35) + "\n");
        }
    }

}

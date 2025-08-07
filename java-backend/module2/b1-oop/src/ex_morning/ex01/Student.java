package ex_morning.ex01;

import java.util.Scanner;

public class Student {
    String name;
    Double literature;
    Double math;

    public void input() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Nhập tên sinh viên: ");
            name = sc.nextLine().trim();
            if (name.matches("[a-zA-ZÀ-Ỹà-ỹ\\s]+")) break;
            else System.out.println("❌ Tên không hợp lệ. Vui lòng nhập lại (chỉ chữ và khoảng trắng)!");
        }

        while (true) {
            System.out.print("Nhập điểm Văn (0.0–10.0): ");
            if (sc.hasNextDouble()) {
                literature = sc.nextDouble();
                sc.nextLine();
                if (literature >= 0 && literature <= 10) break;
            } else sc.nextLine();
            System.out.println("❌ Điểm không hợp lệ. Nhập lại!");
        }

        while (true) {
            System.out.print("Nhập điểm Toán (0.0–10.0): ");
            if (sc.hasNextDouble()) {
                math = sc.nextDouble();
                sc.nextLine();
                if (math >= 0 && math <= 10) break;
            } else sc.nextLine(); // Clear input
            System.out.println("❌ Điểm không hợp lệ. Nhập lại!");
        }
    }

    double calculateAverageScore() {
        return (math + literature) / 2;
    }

    public void output() {
        System.out.printf("%-20s : %s\n", "👤 Tên", name);
        System.out.printf("%-20s : %.2f\n", "📘 Điểm Toán", math);
        System.out.printf("%-20s : %.2f\n", "📗 Điểm Văn", literature);
        System.out.printf("\u001B[31m%-20s  : %.2f\n", "📊 Điểm Trung Bình \u001B[0m", calculateAverageScore());
    }
}

package module1_core.lesson03.ex_afternoon;

import java.util.Scanner;

public class Ex01Employee {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập thông tin nhân viên: ");
        int employee = 1;
        String listEmployee = "";
        String choose;

        do {
            System.out.println(" --- Thông tin nhân viên thứ " + employee);

            System.out.print("Nhập họ và tên: ");
            String name = sc.nextLine();

            System.out.print("Nhập tuổi: ");
            byte age = Byte.parseByte(sc.nextLine());

            System.out.print("Nhập giới tính (Nam/Nữ): ");
            String gender = sc.nextLine();

            System.out.print("Nhập lương: ");
            double salary = sc.nextDouble();
            sc.nextLine();

            System.out.print("Nhập điểm trung bình (thang 10): ");
            float avgScore = Float.parseFloat(sc.nextLine());


            listEmployee += "Nhân viên " + employee + ": " + name + ", Tuổi: " + age
                    + ", Giới tính: " + gender + ", Lương: " + String.format("%,.0f", salary) + "VND, Điểm TB: " + avgScore + "\n";

            if (employee < 3) {
                System.out.print("\nMuốn nhập tiếp nhân viên không? (Yes/No): ");
                choose = sc.nextLine();
                if (choose.equalsIgnoreCase("Yes")) {
                    employee++;
                } else {
                    break;
                }
            } else {
                break;
            }

        } while (true);

        System.out.println("\n--- DANH SÁCH NHÂN VIÊN ---");
        System.out.println(listEmployee);

    }
}

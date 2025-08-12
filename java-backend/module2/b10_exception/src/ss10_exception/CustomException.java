package ss10_exception;

import ss10_exception.exception.Under18YearsOldException;

import java.util.Scanner;

public class CustomException {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        /// Kiểm tra tuổi hợp lệ (trên 18 tuổi)
        int age;
        try {
            age = inputAgeOver18();
        } catch (Under18YearsOldException e) {
            System.out.println(e.getMessage());
        }

        /// Cần kiểm tra biến age có trường hợp nào không được khởi tạo không!!
        /// Vậy có giải pháp gì để đảm bảo age luôn được khởi tạo???
//        System.out.println("Tuổi đã nhập: " + age);
    }

    public static int inputAgeOver18() throws Under18YearsOldException {
        System.out.print("Nhập vào tuổi: ");
        int age = Integer.parseInt(sc.nextLine());

        if (age < 18) {
            throw new Under18YearsOldException("Bạn chưa đủ tuổi!");
        }

        return age;
    }
}

package module1_core.lesson03;

public class Ex01 {
    public static void main(String[] args) {
        System.out.println("Các số từ 1 - 100 chia hết cho 3:");
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0) {
                System.out.println(i);
            }
        }
    }
}

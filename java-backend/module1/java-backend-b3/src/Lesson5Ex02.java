public class Lesson5Ex02 {
    public static void main(String[] args) {
        System.out.println("Bảng cửu chương: ");
        for (int i = 2; i < 10; i++) {
            for (int j =  1; j <= 10; j++) {
                System.out.println(i + " x " + j + " = " + (i*j));
            }
            System.out.println("------------------------------");
        }
    }
}

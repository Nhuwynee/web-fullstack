package ex_morning;

public class Overload {
    public static double add(double a, double b) {
        return a + b;
    }

    public static double add(double a, double b, double c) {
        return a + b + c;
    }

    public static void main(String[] args) {
        System.out.println(add(2, 3, 4));

    }


/// Từ khóa final
//    public static void main(String[] args) {
//        final int MAX_STUDENT = 30;
//        MAX_STUDENT = 50;
//    }
}

//class Animal {
//    final void breathe() {
//        System.out.println("Động vật có thể thở");
//    }
//}
//
//class Dog extends Animal {
//    // Lỗi: không thể override phương thức breathe()
//    void breathe() {
//        System.out.println("Chó thở gấp hơn");
//    }
//}


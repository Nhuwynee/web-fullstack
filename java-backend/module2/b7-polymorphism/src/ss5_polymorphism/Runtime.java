package ss5_polymorphism;


class Animal {
    void sound() {
        System.out.println("Animal makes a sound");
    }
}


class Bird extends Animal {
    @Override
    void sound() {
        System.out.println("Birdsong");
    }
}


class Cat extends Animal {
//    @Override
//    void sound() {
//        System.out.println("Cat meows");
//    }
}


public class Runtime {
    public static void main(String[] args) {
        /// Đa hình lúc runtime -> phụ thuộc vào đối tượng mà nó tham chiếu tới
        Animal a1 = new Bird(); // Biến a1 có kiểu tham chiếu là Animal, còn đối tượng được tạo ra thực sự là kiểu Bird
        Animal a2 = new Cat(); // Nhờ tính đa hình mà a2 có kiểu tham chiếu là Animal, nhưng kiểu thực tế là Cat

        a1.sound(); // Output: ???
        a2.sound(); // Output: ???


        /// Nếu Cat không có phương thức sound() override thì thế nào???
    }
}
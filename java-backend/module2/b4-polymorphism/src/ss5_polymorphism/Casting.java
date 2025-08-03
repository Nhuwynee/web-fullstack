package ss5_polymorphism;


class Laptop {
    int id;
    String name;

    public Laptop() {
    }

    public Laptop(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class OldLaptop extends Laptop {
    int yearsUsed;

    public OldLaptop() {
    }

    public OldLaptop(int id, String name, int yearsUsed) {
        super(id, name);
        this.yearsUsed = yearsUsed;
    }
}

class NewLaptop extends Laptop {
    int releaseYear;

    public NewLaptop() {
    }

    public NewLaptop(int id, String name, int releaseYear) {
        super(id, name);
        this.releaseYear = releaseYear;
    }
}

public class Casting {
    public static void main(String[] args) {
//        NewLaptop newLaptop1 = new NewLaptop(); // Dòng này không thực hiện việc ép kiểu gì cả
//
//        Laptop laptop1 = newLaptop1; // Upcasting -> Java tự ngầm định ép kiểu con lên cha
//
//        NewLaptop newLaptop2 = (NewLaptop) laptop1; // Downcasting -> dev phải thực hiện ép kiểu thủ công


        /// Upcasting KHÔNG bao giờ lỗi -> vì sao???
        /// newLaptop1 và laptop1 có những thuộc tính nào???
//        System.out.println(newLaptop1.);
//        System.out.println(laptop1.);
//        System.out.println(newLaptop2.releaseYear);
        /// -> Giải thích slide Lưu ý


        /// Downcasting vẫn có thể lỗi -> vì sao???
        /// Đặt break point debug ở đây để hiểu cơ chế runtime
//        OldLaptop oldLaptop = new OldLaptop();
//        Laptop laptop = oldLaptop;
//        NewLaptop newLaptop = (NewLaptop) laptop;
//        System.out.println(newLaptop.releaseYear);


        /// Để tránh lỗi ClassCastException -> sử dụng từ khóa instanceof
//        if (laptop instanceof NewLaptop) {
//            NewLaptop newLaptop = (NewLaptop) laptop;
//            System.out.println(newLaptop.releaseYear);
//        }


        /// Vậy tại sao phải thực hiện ép kiểu để làm gì cho phức tạp???
        Laptop[] laptops = new Laptop[3];
        laptops[0] = new NewLaptop(1, "Dell XPS", 2024);
        laptops[1] = new OldLaptop(2, "HP Pavilion", 5);
        laptops[2] = new NewLaptop(3, "Macbook Air", 2023);

        for (Laptop lap : laptops) {
//            System.out.println(laptops[i].); // Nếu không ép kiểu sang NewLaptop -> không thể sử dụng releaseYear
            System.out.println("Laptop: " + lap.name + " -> " );
            if (lap instanceof NewLaptop) {// Tại sao phải kiểm tra instanceof NewLaptop???
                System.out.println("Năm ra mắt: " + ((NewLaptop) lap).releaseYear);
            } else if (lap instanceof OldLaptop oldLap) {
                System.out.println("Đã sử dụng: " + oldLap.yearsUsed + " năm");
            }
        }
    }
}

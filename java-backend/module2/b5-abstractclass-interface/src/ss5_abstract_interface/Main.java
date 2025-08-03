package ss5_abstract_interface;


public class Main {
    public static void main(String[] args) {
        /// Có cần thiết phải khởi tạo đối tượng của class Employee không???
        /// -> nó không đủ thông tin
//        Employee e = new Employee();


        /// Không cần quan tâm đối tượng đó thực sự thuộc lớp nào
        Employee e = new FTProductionEmployee();
        System.out.println(e.getSalary());


        /// Có thể gọi thuộc tính của interface thông qua tên interface
        System.out.println(TimeKeeping.MAX_WORKING_HOUR);


        /// Làm thế nào để gọi 1 method của interface???
        /// Java 8+ -> có thể sử dụng static method
        /// -> bên interface cần thêm body cho nó
//        TimeKeeping.checkIn();
    }
}


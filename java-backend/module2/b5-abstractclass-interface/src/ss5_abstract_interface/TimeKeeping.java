package ss5_abstract_interface;

import java.time.LocalTime;

/// Interface: 100% trừu tượng
interface TimeKeeping {
    /// Tất cả phương thức trong interface đều là abstract method
    /// -> không có body
    LocalTime checkIn();


    /// Các trường trong interface đều là public, static và final
    /// -> bị mờ -> không cần thiết
    /// Vì là static nên nó thuộc class (ở đây là interface)
    /// -> nên có thể gọi nó thông qua tên interface
    public static final double MAX_WORKING_HOUR = 12;


    /// Có nên để Employee là interface luôn không???
    /// Cần nhớ các field trong interface đều là final
    /// -> vì final không cho phép gán lại nên cần gán giá trị lúc khai báo
//    private String name;
    String name = "Nguyen Van A"; // Không thể gán lại cho name như abstract class


    /// default method có body
    /// -> giúp khi thêm mới method thì các lớp đang implement TimeKeeping (giả sử hàng trăm lớp)
    /// tất cả các lớp không cần phải thực hiện triển khai method mới này
    /// chỉ những lớp cần sử dụng nó mới triển khai ghi đè lại
    default void test() {
        System.out.println("This is a time keeping test");
    }
}

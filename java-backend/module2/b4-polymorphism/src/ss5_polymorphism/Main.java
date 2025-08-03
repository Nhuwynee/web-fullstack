package ss5_polymorphism;

public class Main {
    /// Đặt break point debug ở s1 & s2 để quan sát đối tượng được tạo ra
    /// -> địa chỉ s1 & s2 có giống nhau không???
    public static void main(String[] args) {
        Student s1 = new Student(1, "Nguyễn Văn A", 9.5);
        Student s2 = new Student(1, "Nguyễn Văn A", 9.5);

        /// Override equals() sử dụng instanceof
        GraduateStudent s3 = new GraduateStudent(1, "Nguyễn Văn A", 9.5);

        /// Kiểm tra kết quả với equals() gốc của class Object & cách 1 & cách 2
        System.out.println("Kiểm tra equals: " + s1.equals(s3));
        /// -> Vậy có cách nào để điều chỉnh equals() trả về true theo ý muốn của mình???


        /// Đối với String thì equals() thuộc class String của Java
        "hello".equals("hello");
        /// -> Vậy làm thế nào để gọi chính xác equals() của dev tự viết override, hay equals() của String thuộc Java???


        /// Override toString() của class Object
        System.out.println(s1.toString());

        String str = "Thông tin: " + s1; // Mặc định Java đã ngầm gọi toString() để xuất ra thông tin cho đối tượng
        System.out.println(str);


        /// Object là cha của tất cả các class trong Java
        Object o = new Student();
    }
}

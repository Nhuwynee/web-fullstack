package ss10_exception;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class HandleExceptionByTryCatch {
    /// Sử dụng Logger của java utils để log lỗi theo level
//    private static final Logger logger = Logger.getLogger(HandleException.class.getName());
    public static void main(String[] args) {
        File fileWrong = new File("src/data/study.java");

        // Đối với đường dẫn đúng -> flow như thế nào???
        File file = new File("src/ss10_exception/ExceptionStudy.java");

        try {
            FileReader fileReader = new FileReader(fileWrong);
        } catch (FileNotFoundException e) {
//            logger.log(Level.SEVERE, ">>>>> Không tìm thấy file: " + fileWrong.getPath(), e);
            e.printStackTrace();
//            System.out.println(e.getMessage());
            System.out.println(">>>>> Flow đi vào khối catch FileNotFoundException");
        }

        System.out.println(">>>>> Chương trình xịn xò kết thúc KHÔNG LỖI");
    }
}

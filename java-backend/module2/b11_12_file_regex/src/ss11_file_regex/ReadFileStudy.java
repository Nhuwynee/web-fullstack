package ss11_file_regex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadFileStudy {
    private static final Logger logger = Logger.getLogger(ReadFileStudy.class.getName());

    public static void main(String[] args) {
        File file = new File("E:\\web-fullstack\\java-backend\\module2\\b11_12_file_regex\\src\\ss11_file_regex\\data\\input.txt");
        BufferedReader bufferedReader = null;


        /// Có thể sử dụng try-with-resources để java tự tự động gọi close() cho tất cả tài nguyên khai báo trong (...)
        /// -> không cần viết finally { bufferedReader.close(); } thủ công
        try {//
            bufferedReader = new BufferedReader(new FileReader(file));
            System.out.println(bufferedReader.readLine());
            System.out.println(bufferedReader.readLine());
            System.out.println(bufferedReader.readLine());
            System.out.println(bufferedReader.readLine()); // -> Kết quả???

//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                System.out.println(line);
//            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, ">>>>> Lỗi đọc file", e);
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                logger.log(Level.SEVERE, ">>>>> Lỗi khi đóng BufferedReader", e);
            }
        }
    }
}

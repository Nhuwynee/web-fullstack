package ss11_file_regex;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriteFileStudy {
    private static final Logger logger = Logger.getLogger(WriteFileStudy.class.getName());

    public static void main(String[] args) {
        File file = new File("src/ss11_file_regex/data/output.txt");

        /// Đối với FileWriter, nếu chưa có file output.txt
        /// -> tự động tạo mới file theo đường dẫn đó
        BufferedWriter bufferedWriter = null;

        try {
            /// Ghi đè toàn bộ dữ liệu trong file
            bufferedWriter = new BufferedWriter(new FileWriter(file));

            bufferedWriter.write("Trần Văn A\n");
            bufferedWriter.write("Trần Văn B");
            bufferedWriter.newLine();
            bufferedWriter.write("Trần Văn C");
            bufferedWriter.newLine();
        } catch (IOException e) {
            logger.log(Level.SEVERE, ">>>>> Lỗi ghi file", e);
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                logger.log(Level.SEVERE, ">>>>> Lỗi khi đóng BufferedReader", e);
            }
        }
    }
}

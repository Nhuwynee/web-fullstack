package ss10_exception;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class HandleExceptionByThrow {
    /// Nếu throws exception ở main() thì sao???
    public static void main(String[] args) {
        try {
            readFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(">>>>> Chương trình xịn xò kết thúc KHÔNG LỖI");
    }

    static void readFile() throws FileNotFoundException {
        File fileWrong = new File("src/data/study.java");
        FileReader fileReader = new FileReader(fileWrong);
    }
}

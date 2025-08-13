package do_exercises.phone;

import ss11_file_regex.StudentManagement;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadWriteFilePhone {
    private static final Logger logger = Logger.getLogger(StudentManagement.class.getName());
    private static final String PATH_PHONE_FILE = "E:\\web-fullstack\\java-backend\\module2\\b11_12_file_regex\\src\\do_exercises\\phone\\phone.csv";

    public static List<Phone> readFile(String path) {
        File file = new File(path);
        List<Phone> phones = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            bufferedReader.readLine();

            Phone phone;
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");

                String id = data[0].trim();
                String name = data[1].trim();
                BigDecimal price = new BigDecimal(data[2].trim());

                DateTimeFormatter inputFormatter1 = DateTimeFormatter.ofPattern("d/M/yyyy");
                DateTimeFormatter inputFormatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                String dateStr = data[3].trim();
                LocalDate warrantyPeriod;

                // thử parse với định dạng 1
                try {
                    warrantyPeriod = LocalDate.parse(dateStr, inputFormatter1);
                } catch (DateTimeParseException e) {
                    // nếu fail, parse với định dạng 2
                    warrantyPeriod = LocalDate.parse(dateStr, inputFormatter2);
                }

                OSPhone osPhone = OSPhone.valueOf(data[4].trim());
                String manufacturer = data[5].trim();

                if (id.startsWith("DTM")) {
                    int quantity = Integer.parseInt(data[6].trim());
                    phone = new NewPhone(id, name, price, warrantyPeriod, osPhone, manufacturer, quantity);
                    phones.add(phone);
                }
                else if (id.startsWith("DTC")) {
                    String batteryStatus = data[6].trim();
                    phones.add(new OldPhone(id, name, price, warrantyPeriod, osPhone, manufacturer, batteryStatus));
                }
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, ">>>>> Lỗi khi đọc file", e);
        }
        return phones;
    }

    public static void writeFile(List<Phone> phones) {
        StringBuilder stringBuilder = new StringBuilder("ID,Ten may,Gia,Thoi han bao hanh,Hệ dieu hanh,Nha san xuat,So luong/Tinh trang pin");

        for (Phone p : phones) {
            stringBuilder.append("\n").append(p.toCSV());
        }

        File file = new File(PATH_PHONE_FILE);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            logger.log(Level.SEVERE, ">>>>> Lỗi khi ghi file", e);
        }
    }
}

package view;

import bean.Book;
import bean.Document;
import bean.Magazine;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainDocument {
    private static final Scanner sc = new Scanner(System.in);
    private static final String PATH_DOCUMENT_FILE = "E:\\web-fullstack\\java-backend\\module2\\practice\\src\\view\\document.csv";

    private static List<Document> documents = readFile(PATH_DOCUMENT_FILE);

    // Undo thao tác
    private static Stack<Document> undoStack = new Stack<>();

    // Danh sách tài liệu chờ kiểm duyệt
    private static Queue<Document> pending = new LinkedList<>();

    // Lưu lịch sử
    static LinkedList<String> history = new LinkedList<>();

    // file
    private static final Logger logger = Logger.getLogger(MainDocument.class.getName());

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n===== MENU =====");
            // câu 2
            System.out.println("1. Thêm mới");
            System.out.println("2. Xem danh sách");
            System.out.println("3. Xóa theo ID");
            // câu 3
            System.out.println("4. Tìm kiếm theo tên");
            System.out.println("5. Sắp xếp theo giá");
            // câu 4
            System.out.println("6. Hiển thị thao tác vừa thực hiện(lịch sử)");
            System.out.println("7. Undo xóa");
            System.out.println("8. Danh sách kiểm duyệt (pending)");
            // câu 5
            System.out.println("9. Hiển thị nhà xuất bản");
            System.out.println("10. Thống kê nhà xuất bản");
            // câu 6
            System.out.println("11. Lưu ra file");
            System.out.println("12. Đọc file");
            System.out.println("0. Thoát");

            do {
                System.out.print("Chọn: ");
                try {
                    choice = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Vui lòng nhập số nguyên 0 – 12!");
                    choice = -1;
                    continue;
                }
                if (choice < 0 || choice > 12) {
                    System.out.println("Chỉ chọn từ 0 đến 12, nhập lại!");
                }
            } while (choice < 0 || choice > 12);


            switch (choice) {
                // câu 1
                case 1 -> menuAdd();
                case 2 -> print(documents);
                case 3 -> delete();
                // câu 2
                case 4 -> search();
                case 5 -> {
                    List<Document> sortByPrice = sortByPrice();
                    System.out.println("Danh sách sau khi sắp xếp: ");
                    print(sortByPrice);
                }
                // câu 6
                case 6 -> historyAction();
                case 7 -> undoDelete();
                case 8 -> showDocumentPending();

                // câu 5
                case 9 -> showPublishers();
                case 10 -> countByPublisher();
                case 11 -> {
                    writeFile(documents);
                    System.out.println("Ghi thành công vào file!");
                }
                case 12 -> {
                    readFile(PATH_DOCUMENT_FILE);
                    System.out.println("Đọc file thành công");
                }
                case 0 -> System.out.println("Thoát...");
                default -> System.out.println("Sai lựa chọn!");
            }

        } while (choice != 0);
    }



    // action 1
    private static void menuAdd() {
        int t;
        do {
            t = inputInt("Chọn: 1. Sách | 2. Tạp chí");
        } while (t != 1 && t != 2);

        int pos;
        do {
            pos = inputInt("Thêm vào (1. Đầu | 2. Cuối): ");
        } while (pos != 1 && pos != 2);

        boolean addFirst = (pos == 1);
        if (t == 1) {
            Book book = new Book();
            addDocument(book, addFirst);
        } else {
            Magazine magazine = new Magazine();
            addDocument(magazine, addFirst);
        }

    }

    // action 2
    private static void print(List<Document> documents) {
        if (documents == null || documents.isEmpty()) {
            System.out.println("Không có Document nào!");
        } else {
            documents.forEach(System.out::println);
        }
    }

    private static int inputInt(String message) {
        int value;
        while (true) {
            try {
                System.out.println(message);
                value = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số.");
            } catch (IllegalArgumentException e) {
                System.out.println("Vui lòng nhập > 0");
            }
        }
        return value;
    }

    private static <T extends Document> void addDocument(T document, boolean addFirst) {
        try {
            document.setId(getIdentityId(document.getClass()));
            document.input(sc);
            if (addFirst) {
                documents.add(0, document);
            } else {
                documents.add(document);
            }
            pending.offer(document);
            System.out.println("Thêm thành công!\n");

            // lưu lịch sử
            history.add("Thêm - ID: " + document.getId());

            // viết vào lại file
            writeFile(documents);

        } catch (Exception e) {
            System.out.println("Lỗi thêm: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String getIdentityId(Class<?> clazz) {
        int maxId = 0;
        if (documents != null) {
            for (Document document : documents) {
                if (clazz.isInstance(document)) {
                    String id = document.getId();
                    if (id != null && id.length() > 3) {
                        try {
                            int numberLast = Integer.parseInt(id.substring(1));
                            if (numberLast > maxId) {
                                maxId = numberLast;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("ID không hợp lệ: " + id);
                        }
                    }
                }
            }
        }
        return String.format("%03d", (maxId + 1));
    }

    // action 3
    private static void delete() {
        String id;
        while (true) {
            System.out.print("Nhập id muốn xóa: ");
            id = sc.nextLine();
            if (isValidId(id)) {
                break;
            }
            System.out.println("Id không hợp lệ!");
        }

        boolean found = false;
        Document removedDoc = null;
        for (int i = 0; i < documents.size(); i++) {
            if (documents.get(i).getId().equalsIgnoreCase(id)) {
                removedDoc = documents.remove(i);
                undoStack.push(removedDoc);// add cái vừa xóa xong
                found = true;
                break;
            }
        }

        if (found) {
            history.add("Xóa - Id: " + removedDoc.getId());
            writeFile(documents);
            System.out.println("Đã xóa tài liệu có id: " + id);
        } else {
            System.out.println("Không tìm thấy id: " + id);
        }
    }


    // action 4
    private static void search() {
        System.out.print("Nhập title muốn tìm kiếm: ");
        String name = sc.nextLine().toLowerCase().trim();

        List<Document> results = new ArrayList<>();
        for (Document d : documents) {
            if (d.getTitle().toLowerCase().contains(name)) {
                results.add(d);
            }
        }

        if (!results.isEmpty()) {
            System.out.println("Kết quả tìm kiếm: ");
            results.forEach(System.out::println);
        } else {
            System.out.println("Không tìm thấy!");
        }
    }

    private static boolean isValidId(String id) {
        if (id == null || id.length() != 4) return false;
        String prefix = id.substring(0, 1);
        String numberPart = id.substring(1);
        if (!prefix.equals("B") && !prefix.equals("M")) return false;

        for (char c : numberPart.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private static List<Document> sortByPrice() {
        documents.sort(Comparator.comparing(Document::getPrice));
        return documents;
    }


    // action 6
    private static void historyAction() {
        if (history.isEmpty()) {
            System.out.println("Chưa có thao tác nào được thực hiện");
            return;
        }
        StringBuilder sb = new StringBuilder("");
        if (history.size() == 1) {
            sb.append(history.get(0));
        } else {
            for (int i = 0; i < history.size() - 1; i++) {
                sb.append(history.get(i) + " -> ");
            }
            sb.append(history.get(history.size() - 1));
        }
        System.out.println("Lịch sử thao tác: " + sb);
    }

    // action 7
    public static void undoDelete() {
        if (undoStack.isEmpty()) {
            System.out.println("Không có gì để undo!");
            return;
        }
        Document d = undoStack.pop();
        documents.add(d);
        System.out.println("Undo thành công: " + d.getId());
        history.add("Undo - Id: " + d.getId());
        writeFile(documents);
    }

    // action 8
    private static void showDocumentPending() {
        if (pending.isEmpty()) {
            System.out.println("Không có tài liệu nào chờ kiểm duyệt");
            return;
        }
        for (Document d : pending) {
            System.out.println(d);
        }
    }

    // action 9
    public static void showPublishers() {
        HashSet<String> set = new HashSet<>();
        for (Document d : documents) {
            set.add(d.getPublisher());
        }
        System.out.println(set);
    }

    // action 10
    public static void countByPublisher() {
        HashMap<String, Integer> map = new HashMap<>();
        for (Document d : documents) {
            map.put(d.getPublisher(), map.getOrDefault(d.getPublisher(), 0) + 1);
        }
        System.out.println(map);
    }


    /*
    1. Dùng **HashSet** để lưu danh sách nhà xuất bản (không trùng).
    2. Dùng **HashMap\<String, Integer>** để thống kê số lượng tài liệu theo nhà xuất bản.
     */
// action 11 - 12

    ///  file
    public static void writeFile(List<Document> documents) {
        StringBuilder stringBuilder = new StringBuilder("ID,tilte,publisher,price, issueNumber/ Author,Month/PageCount");

        for (Document p : documents) {
            stringBuilder.append("\n").append(p.toCSV());
        }

        File file = new File(PATH_DOCUMENT_FILE);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            logger.log(Level.SEVERE, ">>>>> Lỗi khi ghi file", e);
        }
    }

    public static List<Document> readFile(String path) {
        File file = new File(path);
        List<Document> documents = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            bufferedReader.readLine();

            Document document;
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");

                String id = data[0].trim();
                String title = data[1].trim();
                String publisher = data[2].trim();
                BigDecimal price = new BigDecimal(data[3].trim());

                if (id.startsWith("B")) {
                    String author = data[4].trim();
                    int pageCount = Integer.parseInt(data[5].trim());
                    document = new Book(id, title, publisher, price, author, pageCount);
                    documents.add(document);
                }
                else if (id.startsWith("M")) {
                    int issueNumber = Integer.parseInt(data[4].trim());
                    int month = Integer.parseInt(data[5].trim());
                    document = new Magazine(id, title, publisher, price, issueNumber, month);
                    documents.add(document);
                }
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, ">>>>> Lỗi khi đọc file", e);
        }
        return documents;
    }
}

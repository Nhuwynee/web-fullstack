package ss11_file_regex.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexCharacterClassDemo {
    public static void main(String[] args) {
        // 1. [abc]: Khớp 1 ký tự là a, b hoặc c
        testRegex("[abc]", new String[]{"a", "b", "c", "d", "ab"});

        // 2. [a-z]: Khớp 1 ký tự thường từ a đến z
        testRegex("[a-z]", new String[]{"a", "m", "z", "A", "1"});

        // 3. [A-Za-z]: Khớp mọi chữ cái in hoa hoặc thường
        testRegex("[A-Za-z]", new String[]{"a", "Z", "M", "1", "-"});

        // 4. [0-9]: Khớp chữ số từ 0 đến 9
        testRegex("[0-9]", new String[]{"0", "5", "9", "a", "10"});

        // 5. [^0-9]: Khớp mọi ký tự KHÔNG phải số
        testRegex("[^0-9]", new String[]{"a", "Z", "@", "5", " "});

        // 6. [-abc]: Dấu - ở đầu nghĩa là ký tự thường (khớp -, a, b hoặc c)
        testRegex("[-abc]", new String[]{"-", "a", "b", "c", "d"});

        // 7. [abc-]: Dấu - ở cuối nghĩa là ký tự thường (khớp a, b, c hoặc -)
        testRegex("[abc-]", new String[]{"a", "b", "c", "-", "z"});
    }

    private static void testRegex(String regex, String[] testStrings) {
        System.out.println("Regex: " + regex);
        /// Pattern là class của Java đại diện cho một biểu thức chính quy đã biên dịch
        Pattern pattern = Pattern.compile(regex); // biên dịch regex thành 1 đối tượng Pattern

        for (String input : testStrings) {
            /// Matcher là class dùng để so khớp (match) chuỗi input với regex pattern
            Matcher matcher = pattern.matcher(input); // tạo ra một Matcher để kiểm tra input dựa trên regex đã biên dịch
            boolean match = matcher.matches(); // kiểm tra chuỗi input có khớp hoàn toàn với mẫu regex (Pattern) hay không
            System.out.printf("  %-5s -> %s%n", input, match ? "Khớp" : "Không khớp");
        }
        System.out.println();
    }
}

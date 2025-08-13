package ss11_file_regex.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexQuantifierDemo {
    public static void main(String[] args) {
        // 1. * : 0 hoặc nhiều lần
        testRegex("a*", new String[]{"", "a", "aa", "aaaa", "b"});

        // 2. + : 1 hoặc nhiều lần
        testRegex("a+", new String[]{"a", "aa", "aaaa", "", "b"});

        // 3. ? : 0 hoặc 1 lần
        testRegex("a?", new String[]{"", "a", "aa", "b"});

        // 4. {n} : chính xác n lần
        testRegex("a{3}", new String[]{"aaa", "aa", "aaaa", "a"});

        // 5. {n,m} : từ n đến m lần
        testRegex("a{2,4}", new String[]{"a", "aa", "aaa", "aaaa", "aaaaa"});

        // 6. {n,} : từ n lần trở lên
        testRegex("a{3,}", new String[]{"a", "aa", "aaa", "aaaa", "aaaaaa"});

        // 7. () gom nhóm + | (lựa chọn)
        testRegex("(ha)+", new String[]{"ha", "haha", "hahaha", "haaa", "h"});

        // 8. Ví dụ gom nhóm với lựa chọn
        testRegex("gr(a|e)y", new String[]{"gray", "grey", "griy", "gry"});
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

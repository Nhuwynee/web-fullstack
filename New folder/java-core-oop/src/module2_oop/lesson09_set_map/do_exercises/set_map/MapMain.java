package do_exercises.set_map;

import java.util.*;

public class MapMain {
    public static void main(String[] args) {
        String text = "Xin chào tôi là Lưu Ngọc Yến Như, tôi đang là sinh viên tại trường Đại học Sư phạm Kỹ thuật.";

        Map<String, Integer> wordCount = findWordCount(text);

        System.out.println("Câu 1: Số lần xuất hiện của từng từ:");
        Set<String> set = wordCount.keySet();
        for (String key : set) {
            System.out.println(key + " - " + wordCount.get(key));
        }

//        wordCount.forEach((word, count) -> System.out.println(word + " - " + count));

        System.out.println("Câu 2:");
        findWord(wordCount);
    }

    private static Map<String, Integer> findWordCount(String text) {
        text = text.replaceAll("[^\\p{L}\\p{Nd}\\s]+", "");
        String[] words = text.split("\\s+");
        Map<String, Integer> map = new HashMap<>();

        for (String word : words) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        }
        return map;
    }

    private static void findWord(Map<String, Integer> map) {
        List<String> onlyWord = new ArrayList<>();
        List<String> repeatedWord = new ArrayList<>();

        Set<String> set = map.keySet();
        for (String key : set) {
            if (map.get(key) == 1) {
                onlyWord.add(key);
            } else {
                repeatedWord.add(key);
            }
        }
        System.out.println("- Những từ chỉ xuất hiện 1 lần: " + String.join(", ", onlyWord));
        System.out.println("- Những từ xuất hiện nhiều lần: " + String.join(", ", repeatedWord));
    }

}

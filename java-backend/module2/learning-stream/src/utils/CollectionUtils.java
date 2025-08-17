package utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CollectionUtils {

    private CollectionUtils() {
    }

    public static <T> void generate(String prefix, int[] elements) {
        System.out.println(prefix + " --> {");
        Arrays.stream(elements).forEach(e -> System.out.println("  + " + e));
        System.out.println("}\n");
    }

    public static <T> void generate(String prefix, T[] elements) {
        generate(prefix, List.of(elements));
    }

    public static <K, V> void generate(String prefix, Map<K, V> map) {
        generate(prefix, map.entrySet());
    }

    public static <T> void generate(String prefix, Collection<T> collection) {
        System.out.println(prefix + " --> {");
        collection.forEach(e -> System.out.println("  + " + e));
        System.out.println("}\n");
    }

}
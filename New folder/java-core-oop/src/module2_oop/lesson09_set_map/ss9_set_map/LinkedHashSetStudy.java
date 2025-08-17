package ss9_set_map;

import java.util.LinkedHashSet;
import java.util.Set;


public class LinkedHashSetStudy {
    public static void main(String[] args) {
        Set<Integer> integerSet = new LinkedHashSet<>();

        integerSet.add(10);
        integerSet.add(20);
        integerSet.add(20);
        integerSet.add(20);
        integerSet.add(30);
        integerSet.add(40);

        System.out.println(integerSet);
    }
}

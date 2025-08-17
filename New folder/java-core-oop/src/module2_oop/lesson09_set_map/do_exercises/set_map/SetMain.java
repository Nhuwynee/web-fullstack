package do_exercises.set_map;

import java.util.*;

public class SetMain {
    public static void main(String[] args) {
        Integer[] arr1 = {1, 3, 4, 5, 6, 1, 11, 2};
        Integer[] arr2 = {1, 3, 4, 5, 6, 1, 13, 18};

        System.out.println("a. Các phần tử duy nhất: " + findSingleElement(arr1));
        System.out.println("b. Tổng các phần tử duy nhất: " + sumUnique(arr1));
        System.out.println("c. Phần tử chung của hai mảng: " + findCommonElement(arr1, arr2));
        System.out.println("d. Tìm min và max: ");
        findMinAndMax(arr1);

    }

    public static List<Integer> findSingleElement(Integer[] arr) {
        Set<Integer> set = new LinkedHashSet<>(Arrays.asList(arr));
        return new ArrayList<>(set);
    }

    public static int sumUnique(Integer[] arr) {
        Set<Integer> set = new HashSet<>(Arrays.asList(arr));
        int sum = 0;
        for (int num : set) {
            sum += num;
        }
        return sum;
    }

    public static Set<Integer> findCommonElement(Integer[] arr1, Integer[] arr2) {
        Set<Integer> set1 = new HashSet<>(Arrays.asList(arr1));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(arr2));

        set1.retainAll(set2);
        return set1;
    }

    public static void findMinAndMax(Integer[] arr) {
        TreeSet<Integer> set = new TreeSet<>(Arrays.asList(arr));

        System.out.println("Min: " + set.first());
        System.out.println("Max: " + set.last());
    }
}

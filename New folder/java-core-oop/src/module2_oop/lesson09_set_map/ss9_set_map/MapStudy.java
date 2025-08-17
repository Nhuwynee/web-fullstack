package ss9_set_map;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapStudy {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();

        map.put(1, "Nguyễn Văn A");
        map.put(2, "Nguyễn Văn B");
        map.put(3, "Nguyễn Văn C");

        System.out.println(map.get(5));


        /// Duyệt qua các phần tử của map???
        Set<Integer> set = map.keySet();
        for (Integer key : set) {
            System.out.println(key + " - " + map.get(key));
        }
    }
}

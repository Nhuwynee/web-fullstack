import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> data = new ArrayList<Integer>();

        for(int i = 1; i<=10; i++){
            data.add(i);
        }

        data.add(12, 4);

        System.out.println(data.toString());
    }
}
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    /* Hàm trong String
    - equals/
    - length()
    - concat(): nối 2 chuỗi thành 1 chuỗi mới
    - charAt(index)
    - replace()/replaceAll()
    - substring()
    - toLowerCase()/ toUpperCase()
    - trim()
    - split(String regex)
    - String.format()
     */
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");
        String nhu = "Nhuw";
        nhu.charAt(7);
        System.out.println(nhu.charAt(7));

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }
}
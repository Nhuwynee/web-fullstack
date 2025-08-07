package ss8_generic_stack_queue;

import java.util.Scanner;
import java.util.Stack;


public class StackStudy {
    public static void main(String[] args) {
        Stack<String> strings = new Stack<>();

        strings.push("hello");
        strings.push("hello2");
        strings.push("hello3");

        System.out.println(strings.peek());
        System.out.println(strings.peek());
        System.out.println(strings.peek());

        while (!strings.isEmpty()) {
            System.out.println(strings.pop());
        }
//        System.out.println(strings.peek()); // -> lỗi runtime EmptyStackException
        // -> cần check isEmpty() khi thao tác trên Stack

        /// Bài tập kinh điển: Quy đổi số thập phân sang hệ nhị phân
        Stack<Integer> integers = new Stack<>();
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập vào số thập phân: ");
        int n = Integer.parseInt(sc.nextLine());
        while (n != 0) {
            integers.push(n % 2);
            n /= 2;
        }

        while (!integers.isEmpty()) {
            System.out.print(integers.pop());
        }
    }

}

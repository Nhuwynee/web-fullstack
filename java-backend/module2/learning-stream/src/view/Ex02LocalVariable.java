package view;

import java.util.List;
import java.util.function.Consumer;

import bean.Apple;
import functional.SumConsumer;

public class Ex02LocalVariable {

    private static Double total;

    public static void main(String[] args) {

        // Viết hàm tính tổng cân nặng của các quả táo trong kho hàng

    }

    // Ex02LocalVariable --> sumOfWeights() --> sum(local variable)
    // SumConsumer       --> accept --> call sum variable xx

    private static Double sumOfWeights(List<Apple> inventory) {
        Double sum = 0d;

        sum = 10d;

        // for (Apple apple: inventory) {
        //	sum = sum + apple.getWeight();
        //}

        // Vì sao trong lambda khi sử dụng biến cục bộ thì biến đó
        // là final hoặc tự chuyển sang final

        // Về bản chất khi biến cục bộ sử dụng trong lambda, thật ra biến cục
        // bộ đó đang được sử dung ở 1 class khác

        // Đúng nguyên tắc ko cho phép, như khi dùng lambda hoặc anonymous class
        // thì Java vẫn cho phép như yêu cầu final

        // 1. lambda
        inventory.forEach(apple -> {
            // thực tế pass biến sum qua hàm accept
            // sum = sum + apple.getWeight();
        });

        // 2. anonymous class
        inventory.forEach(new Consumer<Apple>() {
            @Override
            public void accept(Apple apple) {
                // sum = sum + apple.getWeight();
                total = total + apple.getWeight();
            }
        });

        // 3. implementation class
        inventory.forEach(new SumConsumer(sum));

        sum = 20d;

        return sum;
    }

}
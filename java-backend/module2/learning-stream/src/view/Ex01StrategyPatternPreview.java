package view;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import bean.Apple;
import model.DataModel;

import static utils.CollectionUtils.*;

public class Ex01StrategyPatternPreview {

    public static void main(String[] args) {

        // inventory: kho hàng
        var inventory = DataModel.getApples();

        generate(
                "1st : find all green apples in his inventory",
                filter(inventory, apple -> "green".equals(apple.getColor()))
        );

        generate(
                "2nd: find all red apples in his inventory",
                filter(inventory, apple -> "red".equals(apple.getColor()))
        );

        generate(
                "3rd: find all apples heavier than 50 g",
                filter(inventory, apple -> apple.getWeight() > 50)
        );

        // 4th: find all apples lighter than 200g

        // 5th: find all apples that are green and heavier than 400 g
    }

    // Quan trọng phải biết bài toán có strategy như thế nào
    // Biết được behavior parameterization có mấy tham số, KDL gì và trả về KDL gì
    private static List<Apple> filter(List<Apple> inventory, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (var apple: inventory) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

}
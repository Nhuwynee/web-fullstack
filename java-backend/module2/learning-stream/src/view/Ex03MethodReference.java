package view;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import bean.Apple;
import model.DataModel;

import static utils.CollectionUtils.*;

public class Ex03MethodReference {

    public static void main(String[] args) {

        var inventory = DataModel.getApples();

        // Lambda
        // Function<Apple, Integer> fId = (Apple apple) -> apple.getId();
        // Function<Apple, Integer> fId = Ex03MethodReference::convert;

        // Chuyển sang method reference

        // Cách 1: Static method reference --> có công thức

        // Cách 2: Non-static method reference --> chỉ có 1 số dạng và học thuộc
        // Dạng 1: (T t) -> t.getMethod() ==> T::getMethod
        Function<Apple, Integer> fi = Apple::getId;

        generate(
                "1. Lấy ra id của tất cả các apple đó",
                // transform(inventory, (Apple apple) -> apple.getId())
                // transform(inventory, Ex03MethodReference::convertToId)
                transform(inventory, Apple::getId)
        );

        generate(
                "2. Lấy ra xuất sứ của tất cả các apple đó",
                // transform(inventory, Ex03MethodReference::convertToOrigin)
                transform(inventory, Apple::getOrigin)
        );

        inventory.forEach(System.out::println);
    }

    // Đặt tên hàm bất kỳ
    // Tham số: chính là tham số của lambda
    // body: chính là body của lambda
    // Dùng khi lambda có body dài
    private static Integer convertToId(Apple apple) {
        return apple.getId();
    }

    private static String convertToOrigin(Apple apple) {
        return apple.getOrigin();
    }

    private static <R> List<R> transform(List<Apple> inventory, Function<Apple, R> function) {
        List<R> result = new ArrayList<>();
        for (Apple apple: inventory) {
            result.add(function.apply(apple));
        }
        return result;
    }

}

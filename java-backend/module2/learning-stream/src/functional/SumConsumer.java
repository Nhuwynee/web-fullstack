package functional;

import java.util.function.Consumer;

import bean.Apple;

public class SumConsumer implements Consumer<Apple> {

    private Double sum;

    public SumConsumer(Double sum) {
        this.sum = sum;
    }

    public void accept(Apple apple) {
        sum = sum + apple.getWeight();
    }

}
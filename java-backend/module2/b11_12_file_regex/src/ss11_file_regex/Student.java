package ss11_file_regex;

import java.util.Scanner;

public class Student {
    private String id;
    private String name;
    private double score;

    public void input(boolean isIdUpdate) {
        Scanner sc = new Scanner(System.in);

        if (isIdUpdate) {
            System.out.print("Nhập vào ID: ");
            this.id = sc.nextLine();
        }

        System.out.print("Nhập vào tên: ");
        this.name = sc.nextLine();

        System.out.print("Nhập vào điểm: ");
        this.score = Double.parseDouble(sc.nextLine());
    }


    public void output() {
        System.out.println("ID: " + this.id);
        System.out.println("Tên: " + this.name);
        System.out.println("Điểm: " + this.score);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s", id, name, score);
    }
}

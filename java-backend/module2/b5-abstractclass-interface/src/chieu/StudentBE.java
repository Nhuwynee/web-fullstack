package chieu;

import java.util.Scanner;

public class StudentBE extends Student {
    private String progLanguage;

    public StudentBE() {
    }

    public StudentBE(String id, String fullName, int age, String email, double avgScore, int sessionNumber, String progLanguage) {
        super(id, fullName, age, email, avgScore, sessionNumber);
        this.progLanguage = progLanguage;
    }

    public String getProgLanguage() {
        return progLanguage;
    }

    public void setProgLanguage(String progLanguage) {
        this.progLanguage = progLanguage;
    }

    @Override
    public void setId(String id) {
        super.setId("HVBE-00" + id);
    }

    @Override
    public void input() {
        Scanner sc = new Scanner(System.in);

        super.input();

        while (true) {
            System.out.print("Nhập ngôn ngữ lập trình: ");
            this.progLanguage = sc.nextLine().trim();
            if (this.progLanguage.matches("[a-zA-Z\\s]+")) {
                break;
            } else {
                System.out.println("Ngôn ngữ lập trình không hợp lệ! Không chứa Ký tự đặc biệt.\n");
            }
        }
    }

    @Override
    public String getClassify() {
        if (this.getAvgScore() >= 7.5) {
            return "Giỏi";
        } else if (this.getAvgScore() >= 5) {
            return "Khá";
        } else {
            return "Trung Bình";
        }
    }

    @Override
    public double tuitionFee() {
        return this.getSessionNumber() * 50000 * 0.9;
    }

    @Override
    public String toString() {
        return super.toString()
                + "Ngôn ngữ lập trình: " + this.progLanguage + "\n";
    }
}

package chieu;

import java.util.Scanner;

public class StudentFS extends Student {
    private int joinedProjects;

    public StudentFS() {
    }

    public StudentFS(String id, String fullName, int age, String email, double avgScore, int sessionNumber, int joinedProjects) {
        super(id, fullName, age, email, avgScore, sessionNumber);
        this.joinedProjects = joinedProjects;
    }

    public int getJoinedProjects() {
        return joinedProjects;
    }

    public void setJoinedProjects(int joinedProjects) {
        this.joinedProjects = joinedProjects;
    }

    @Override
    public void setId(String id) {
        super.setId("HVFS-00" + id);
    }

    @Override
    public void input() {
        Scanner sc = new Scanner(System.in);

        super.input();

        while (true) {
            System.out.print("Nhập số dự án tham gia: ");
            if (sc.hasNextInt()) {
                this.joinedProjects = sc.nextInt();
                if (this.joinedProjects < 0) {
                    System.out.println("Số dự án tham gia không hợp lệ! Phải >= 0.\n");
                    continue;
                }
                sc.nextLine();
                break;
            } else {
                System.out.println("Số dự án tham gia không hợp lệ! Nhập số nguyên.\n");
                sc.nextLine();
            }
        }
    }

    @Override
    public String getClassify() {
        if (this.joinedProjects >= 2 && this.getAvgScore() >= 8) {
            return "Giỏi";
        } else if (this.getAvgScore() >= 6.5) {
            return "Khá";
        } else {
            return "Trung bình";
        }
    }

    @Override
    public double tuitionFee() {
        return this.getSessionNumber() * 50000 * 0.85;
    }

    @Override
    public String toString() {
        return super.toString()
                + "Số dự án tham gia: " + this.joinedProjects + "\n";
    }
}

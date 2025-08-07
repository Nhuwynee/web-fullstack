package do_exercise;

import java.util.Scanner;

public class TaskManagerMain {
    static Scanner sc = new Scanner(System.in);
    static TaskManager<Task> tasks = new TaskManager<>();

    public static void main(String[] args) {
        int choose;
        do {
            menu();
            try {
                System.out.print("Nhập lựa chọn: ");
                choose = Integer.parseInt(sc.nextLine());

                switch (choose) {
                    // 1.
                    case 1 -> {
                        tasks.pushToStack(addTask());
                        System.out.println("Thêm thành công!");
                    }
                    case 2 -> System.out.println(tasks.peekStack());
                    case 3 -> System.out.println(tasks.popFromStack());
                    case 4 -> System.out.println(tasks.isStackEmpty() ? "Danh sách rỗng!" : "Danh sách không rỗng!");

                    // 2.
                    case 5 -> {
                        tasks.addToQueue(addTask());
                        System.out.println("Thêm thành công!");
                    }
                    case 6 -> System.out.println(tasks.peekQueue());
                    case 7 -> System.out.println(tasks.pollFromQueue());
                    case 8 -> System.out.println(tasks.isQueueEmpty() ? "Danh sách rỗng!" : "Danh sách không rỗng!");

                    // 3.
                    case 9 -> {
                        Task redo = tasks.redoTask();
                        if (redo != null) {
                            System.out.println("Đã khôi phục: " + redo);
                        }
                    }

                    // 4.
                    case 10 -> processHighestPriority();

                    case 0 -> System.out.println("Thoát chương trình.");
                    default -> System.out.println("Lựa chọn không hợp lệ.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
                choose = -1;
            }
        } while (choose != 0);

    }

    private static void menu() {
        System.out.println("\n====== MENU QUẢN LÝ CÔNG VIỆC ======");
        System.out.println("1. Thêm công việc vào Stack");
        System.out.println("2. Xem công việc đàu tiên trong Stack");
        System.out.println("3. Xem và Xóa công việc khỏi Stack");
        System.out.println("4. Kiểm tra Stack có rỗng không");
        System.out.println("5. Thêm công việc vào Queue");
        System.out.println("6. Xem công việc đầu tiên trong Queue");
        System.out.println("7. Xem và Xóa công việc khỏi Queue");
        System.out.println("8. Kiểm tra Queue có rỗng không");
        System.out.println("9. Khôi phục công việc đã xử lý gần nhất (Redo)");
        System.out.println("10. Tìm và xử lý công việc có độ ưu tiên cao nhất trong Queue");
        System.out.println("0. Thoát chương trình");
    }

    private static Task addTask() {
        System.out.print("Nhập tên task: ");
        String name = sc.nextLine();
        int priority;

        while (true) {
            try {
                System.out.println("Nhập độ ưu tiên: ");
                priority = Integer.parseInt(sc.nextLine());
                if (priority < 0 || priority > 10) {
                    System.out.println("Độ ưu tiên phải từ 0 - 10.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập đúng định dạng.");
            }
        }

        return new Task(name, priority);
    }

     // 4.
    private static void processHighestPriority() {
        if (tasks.tasksQueue.isEmpty()) {
            System.out.println("Queue đang rỗng!");
            return;
        }

        int priority = tasks.peekQueue().getPriority();
        Task task = null;

        for (Task t : tasks.tasksQueue) {
             if (t.getPriority() < priority) {
                 task = t;
                 priority = t.getPriority();
             }
        }

        System.out.println("Đã xử lý task có độ ưu tiên cao nhất: " + task);
    }

}

public class Loop {
    public static void main(String[] args) {
        int n = 5, cao = 6, rong = 6;

        System.out.println("Tam giác tăng:");
        tamGiacTang(n);
        System.out.println("Tam giác giảm:");
        tamGiacGiam(n);
        System.out.println("Tam giác lệch phải:");
        tamGiacLechPhai(n);
        System.out.println("Hộp đặc:");
        hopDac(cao, rong);
        System.out.println("Hộp rỗng:");
        hopRong(cao, rong);
        System.out.println("Z ngược:");
        zNguoc(rong);
        System.out.println("Z :");
        zNormal(rong);
    }

    // Tam giác tăng dần
    private static void tamGiacTang(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++)
                System.out.print(j <= i ? "*" : "");
            System.out.println();
        }
    }

    // Tam giác giảm dần
    private static void tamGiacGiam(int n) {
        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= n; j++)
                System.out.print(j <= i ? "*" : "");
            System.out.println();
        }
    }

    // Tam giác lệch phải
    private static void tamGiacLechPhai(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++)
                System.out.print(j <= n - i ? " " : "*");
            System.out.println();
        }
    }

    // Hộp đặc
    private static void hopDac(int cao, int rong) {
        for (int i = 1; i <= cao; i++) {
            for (int j = 1; j <= rong; j++)
                System.out.print("*");
            System.out.println();
        }
    }

    // Hộp rỗng
    private static void hopRong(int cao, int rong) {
        for (int i = 1; i <= cao; i++) {
            for (int j = 1; j <= rong; j++) {
                System.out.print((i == 1 || i == cao || j == 1 || j == rong) ? "*" : " ");
            }
            System.out.println();
        }
    }

    // Z ngc
    private static void zNguoc(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print((i == 0 || i == n - 1 || j == n - i) ? "*" : " ");
            }
            System.out.println();
        }
    }

    // Z
    private static void zNormal(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print((i == 1 || i == 6 || j == i) ? "*" : " ");
            }
            System.out.println();
        }
    }

}

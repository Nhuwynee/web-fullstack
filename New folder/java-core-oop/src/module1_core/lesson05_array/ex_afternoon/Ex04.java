package ex_afternoon;

import java.util.Scanner;

public class Ex04 {

    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        System.out.print("1. Nhập số lượng sinh viên: ");
        int n = Integer.parseInt(sc.nextLine());

        String[] fullNames = new String[n];
        float[] mathScores = new float[n];
        float[] englishScores = new float[n];
        float[] itScores = new float[n];
        float[] averages = new float[n];

        System.out.println("Nhập thông tin sinh viên: ");

        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin sinh viên thứ " + (i + 1));

            System.out.print("Nhập họ và tên: ");
            fullNames[i] = sc.nextLine();

            System.out.print("Nhập điểm môn toán: ");
            mathScores[i] = nhapDiem(sc, "Toán");

            System.out.print("Nhập điểm môn tiếng anh: ");
            englishScores[i] = nhapDiem(sc, "Tiếng Anh");

            System.out.print("Nhập điểm môn tin: ");
            itScores[i] = nhapDiem(sc, "Tin học");
        }

        // 3.
        System.out.println("3. Điểm trung bình cho từng sinh viên: ");
        tinhDiemTrungBinh(mathScores, englishScores, itScores, averages);

        // 4.
        System.out.println("4. THÔNG TIN SINH VIÊN:");
        inBangDiem(fullNames, mathScores, englishScores, itScores, averages);

        // 5.
        System.out.println("\n5.THÔNG TIN SINH VIÊN CÓ ĐIỂM CAO NHẤT:");
        inBangDiemSvDtbMax(fullNames, mathScores, englishScores, itScores, averages, timSvDtbMax(averages));

        // 6.
        System.out.println("\n6. Danh sách sinh viên sắp xếp theo điểm trung bình giảm dần");
        sapXepSinhVienDtbGiamDan(fullNames, mathScores, englishScores, itScores, averages);
    }

    private static float nhapDiem(Scanner sc, String monHoc) {
        float diem;
        while (true) {
            diem = Float.parseFloat(sc.nextLine());
            if (diem > 0) {
                return diem;
            } else {
                System.out.print("Điểm " + monHoc + " phải lớn hơn 0. Nhập lại: ");
            }
        }
    }

    // 3.
    private static void tinhDiemTrungBinh(float[] mathScores, float[] englishScores, float[] itScore, float[] averagesScores) {
        for(int i = 0 ; i < mathScores.length; i++){
            averagesScores[i] = (mathScores[i] + englishScores[i]+ itScore[i]) / 3;
        }
    }
    // 4.
    private static void inBangDiem (String[] fullNames, float[] mathScores, float[] englishScores, float[] itScore, float[] averagesScores){
        for (int index = 0; index < mathScores.length; index++){
            System.out.println("Thông tin sinh viên " + (index + 1) +
                    ": Họ và tên = " + fullNames[index] +
                    ", Điểm Toán = " + mathScores[index] +
                    ", Điểm Anh = " + englishScores[index] +
                    ", Điểm Tin = " + itScore[index] +
                    ", Điểm Trung Bình = " + averagesScores[index]);
        }
    }
    // 5. Tìm sinh viên có điểm trung bình cao nhất
    public static int[] timSvDtbMax(float[] averages){
        float max = averages[0];
        int count = 0;
        for (int i = 0; i < averages.length; i++){
            if (averages[i] > max){
                max = averages[i];
                count = 1;
            } else if (averages[i] == max){
                count++;
            }
        }
        int[] listIdSV = new int[count];
        int index = 0;
        for (int i = 0; i < averages.length; i++){
            if (averages[i] == max){
                listIdSV[index++] = i;
            }
        }
        return listIdSV;
    }

    private static void inBangDiemSvDtbMax (String[] fullNames, float[] mathScores, float[] englishScores, float[] itScore, float[] averagesScores, int [] listIdSV){
        for (int i = 0; i < listIdSV.length; i++) {
            int index = listIdSV[i];
            System.out.println("Thông tin sinh viên " + (index + 1) +
                    ": Họ và tên = " + fullNames[index] +
                    ", Điểm Toán = " + mathScores[index] +
                    ", Điểm Anh = " + englishScores[index] +
                    ", Điểm Tin = " + itScore[index] +
                    ", Điểm Trung Bình = " + averagesScores[index]);
        }
    }

    // sort sinh viên theo điểm trung bình
    private static void sapXepSinhVienDtbGiamDan(String[] fullNames, float[] mathScores, float[] englishScores, float[] itScore, float[] averagesScores){
        for(int i =0; i < averagesScores.length; i++) {
            for(int j = 0; j < mathScores.length - 1; j++) {
                if(averagesScores[j] < averagesScores[j+1]) {

                    // sort fullName
                    String tempFullName = fullNames[j];
                    fullNames[j] = fullNames[j+1];
                    fullNames[j+1] = tempFullName;

                    // sort mathScore
                    float tempMath = mathScores[j];
                    mathScores[j] = mathScores[j+1];
                    mathScores[j+1] = tempMath;

                    // sort englishScore
                    float tempEnglish = englishScores[j];
                    englishScores[j] = englishScores[j+1];
                    englishScores[j+1] = tempEnglish;

                    // sort itScore
                    float tempIt  = itScore[j];
                    itScore[j] = itScore[j+1];
                    itScore[j+1] = tempIt;

                    // sort averages
                    float tempAvg = averagesScores[j];
                    averagesScores[j] = averagesScores[j+1];
                    averagesScores[j+1] = tempAvg;
                }
            }
        }
        inBangDiem(fullNames, mathScores, englishScores, itScore, averagesScores);
    }
}

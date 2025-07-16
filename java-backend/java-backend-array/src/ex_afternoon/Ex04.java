package ex_afternoon;

import java.util.Scanner;

public class Ex04 {

    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        System.out.println("1. Nhập số lượng sinh viên");
        int n = Integer.parseInt(sc.nextLine());
        String[] fullNames = new String[n];
        float[] mathScores = new float[n];
        float[] englishScores = new float[n];
        float[] itScores = new float[n];
        float[] averages = new float[n];
        System.out.println("Nhập thông tin sinh viên:");
        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin sinh viên thứ " + (i + 1));

            System.out.print("Nhập họ và tên:");
            fullNames[i] = sc.nextLine();

            System.out.print("Nhập điểm môn toán: ");
            mathScores[i] = Float.parseFloat(sc.nextLine());

            System.out.print("Nhập điểm môn tiếng anh: ");
            englishScores[i] = Float.parseFloat(sc.nextLine());

            System.out.print("Nhập điểm môn tin: ");
            itScores[i] = Float.parseFloat(sc.nextLine());
        }

        System.out.println("3. Nhấn 1 để tính điểm trung bình cho từng sinh viên");
        int check =  sc.nextInt();
        if(check ==1){
            TinhDiemTrungBinh(mathScores, englishScores, itScores, averages);
        }
        // 4.
        System.out.println("4. THÔNG TIN SINH VIÊN:");
        InBangDiem(fullNames, mathScores, englishScores, itScores, averages);
        // 5.
        System.out.println("5.THÔNG TIN SINH VIÊN CÓ ĐIỂM CAO NHẤT:");
        InBangDiemSvDtbMax(fullNames,mathScores,englishScores,itScores,averages,TimSvDtbMax(averages));
        // 6.
        System.out.println("Danh sách sinh viên sắp xếp theo điểm trung bình giảm dần");
        sapXepSinhVienDtbGiamDan(fullNames,mathScores,englishScores,itScores,averages);
    }
    // 3.
    private static void TinhDiemTrungBinh(float[] mathScores, float[] englishScores, float[] itScore, float[] averagesScores) {
        for(int i = 0 ; i < mathScores.length; i++){
            averagesScores[i] = (mathScores[i] + englishScores[i]+ itScore[i])/3;
        }
    }
    // 4.
    private static void InBangDiem (String[] fullNames, float[] mathScores, float[] englishScores, float[] itScore, float[] averagesScores){
        System.out.println("*=====THÔNG TIN BẢNG ĐIỂM =====*");
        for(int index = 0 ;index<mathScores.length;index++){
            System.out.println("Thông tin sinh viên " + (index + 1) +
                    ": Họ và tên = " + fullNames[index] +
                    ", Điểm Toán = " + mathScores[index] +
                    ", Điểm Anh = " + englishScores[index] +
                    ", Điểm Tin = " + itScore[index] +
                    ", Điểm Trung Bình = " + averagesScores[index]);
        }
    }
    // 5. Tìm sinh viên có điểm trung bình cao nhất

    public static int[] TimSvDtbMax(float[] averages){
        float max = averages[0];
        int count = 0;
        for (int i = 0; i < averages.length; i++){
            if (averages[i]> max){
                max = averages[i];
                count = 1;
            } else if(averages[i] == max){
                count++;
            }
        }
        int [] listIdSV = new int[count];
        int index = 0;
        for (int i = 0 ;i < averages.length; i++){
            if(averages[i] == max){
                listIdSV[index++] = i;
            }
        }
        return listIdSV;
    }
    private static void InBangDiemSvDtbMax (String[] fullNames, float[] mathScores, float[] englishScores, float[] itScore, float[] averagesScores, int [] listIdSV){
        System.out.println("*=====THÔNG TIN BẢNG ĐIỂM =====*");
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

    // sort sinh viên theo điểmt rung bình
    private static void sapXepSinhVienDtbGiamDan(String[] fullNames, float[] mathScores, float[] englishScores, float[] itScore, float[] averagesScores){
        for(int i =0; i < averagesScores.length; i++){
            for(int j = 0; j < mathScores.length - 1; j++){
                if(averagesScores[j]<averagesScores[j+1]){
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
        InBangDiem(fullNames, mathScores, englishScores, itScore, averagesScores);
    }

}

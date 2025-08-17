package ex_afternoon;

import java.util.Arrays;

public class Ex01 {
    public static void main(String[] args) {
        int[] arr = {-1, 2, 3, -4, 0, 6, -7, 8, 9};
//        int[] arr = {2, 4, 6, 4, 2};

        System.out.println("a. \n- Mảng ban đầu: " + Arrays.toString(arr));
        System.out.println("- Mảng sau khi tách số nguyên tố: " + Arrays.toString(tachMangSoNgTo(arr)));

        System.out.println("b. ");
        tach2Mang(arr);

        System.out.println("c. Mảng sau khi sắp xếp giảm dần: " + Arrays.toString(sapXepMangGiamDan(arr)));
        System.out.println("d. Mảng sau khi sắp xếp dương đầu giảm, âm giữa tăng, 0 cuối: " + Arrays.toString(sapXep3Loai(arr)));
        System.out.println("e. Mảng sau khi đảo ngược: " + Arrays.toString(daoNguocMang(arr)));
        System.out.println(kiemTraMangDoiXung(arr) ? "f. Là mảng đối xứng" : "f. Ko phải mảng đối xứng");
        System.out.println(demCapDoiXung(arr) == -1 ?
                "g. Mảng ko đối xứng nên ko có cặp nào đối xứng."
                : ("g. Số cặp đối xứng trong mảng: " + demCapDoiXung(arr)));

    }

    // a.
    private static int[] tachMangSoNgTo (int[] arr) {
        // đếm số lượng số nguyên tố
        int primeCount = 0;
        for (int num : arr) {
            if (kiemTraSoNguyenTo(num)) primeCount++;
        }

        // tạo mảng mới với kích thước tăng thêm primeCount
        int[] newArr = new int[primeCount];
        int newIndex = 0;

        for (int i : arr) {
            if (kiemTraSoNguyenTo(i)) {
                newArr[newIndex++] = i;
            }
        }
        return newArr;
    }

    private static boolean kiemTraSoNguyenTo (int n) {
        int temp = 0;
        if (n == 1 || n <= 0) return false;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                temp += 1;
            }
        }
        return temp == 0;
    }

    // b.
    private static void tach2Mang (int[] arr) {
        int count = 0;
        for (int i : arr) {
            if (i > 0) count++;
        }

        int[] arrDuong = new int[count];
        int indexDuong = 0;
        int[] arrConLai = new int[arr.length - count];
        int indexConLai = 0;

        for (int i : arr) {
            if (i > 0) arrDuong[indexDuong++] = i;
            else arrConLai[indexConLai++] = i;
        }
        System.out.println("- Mảng số nguyên dương: " + Arrays.toString(arrDuong));
        System.out.println(arrConLai.length != 0 ? ("- Mảng còn lại là: " + Arrays.toString(arrConLai)) : "- Mảng còn lại rỗng.");
    }

    private static int[] sapXepMangGiamDan(int[] arr) {
        int[] arrNew = Arrays.copyOfRange(arr, 0, arr.length);
        for (int i = 0; i < arrNew.length - 1; i++) {
            for (int j = i + 1; j < arrNew.length; j++) {
                if (arrNew[i] < arrNew[j]) {
                    swap(arrNew, i, j);
                }
            }
        }
        return arrNew;
    }


    private static int[] sapXep3Loai(int[] arr) {
        int countDuong = 0, countAm = 0, countZero = 0;

        for (int i : arr) {
            if (i > 0) countDuong++;
            else if (i < 0) countAm++;
            else countZero++;
        }

        int[] duong = new int[countDuong];
        int[] am = new int[countAm];
        int idxDuong = 0, idxAm = 0;

        for (int i : arr) {
            if (i > 0) duong[idxDuong++] = i;
            else if (i < 0) am[idxAm++] = i;
        }

        for (int i = 0; i < duong.length - 1; i++) {
            for (int j = i + 1; j < duong.length; j++) {
                if (duong[i] < duong[j]) {
                    swap(duong, i, j);
                }
            }
        }

        for (int i = 0; i < am.length - 1; i++) {
            for (int j = i + 1; j < am.length; j++) {
                if (am[i] > am[j]) {
                    swap(am, i, j);
                }
            }
        }

        int[] result = new int[arr.length];
        int index = 0;

        for (int i : duong) result[index++] = i;
        for (int i : am) result[index++] = i;
        for (int i = 0; i < countZero; i++) result[index++] = 0;

        return result;
    }

    private static int[] daoNguocMang(int[] arr) {
        int[] arrNew = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arrNew[i] = arr[arr.length - 1 - i];
        }
        return arrNew;
    }

    private static boolean kiemTraMangDoiXung(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            if (arr[i] != arr[arr.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    private static int demCapDoiXung(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length / 2; i++) {
            if (arr[i] != arr[arr.length - 1 - i]) {
                return -1;
            } else count++;
        }
        return count;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

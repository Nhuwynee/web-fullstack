package do_exercise;

public class MyArrayList {
    private int capacity;
    private int[] arr;
    private final int[] emptyArray = {};
    private int size;

    public int size() {
        return size;
    }

    public MyArrayList() {
        arr = emptyArray;
    }

    public MyArrayList(int capacity) {
        this.capacity = capacity;
        this.arr = new int[this.capacity];
    }

    public void checkArrayList() {
        if (arr == emptyArray) {
            this.capacity = 10;
            this.arr = new int[this.capacity];
        }

        if (size == capacity) {
            this.capacity *= 1.5;
            if (size == capacity) {// Tránh trường hợp capacity = 0 hoặc 1
                capacity++;
            }

            // Tạo một mảng mới có kích thước là capacity
            int[] brr = new int[this.capacity];

            // Copy phần tử
            for (int i = 0; i < size; i++) {
                brr[i] = arr[i];
            }

            // Cho arr tham chiếu đến vùng nhớ mới
            arr = brr;
        }
    }
    // Thêm phần tử tại vị trí cuối cùng
    public void add(int element) {
        checkArrayList();
        arr[size] = element;
        size++;
    }

    public void add(int index, int element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index ngoài phạm vi.");
        }

        checkArrayList();
        for (int i = size; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index] = element;
        size++;
    }

    public void set(int index, int element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index ngoài phạm vi.");
        }
        arr[index] = element;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index ngoài phạm vi.");
        }
        return arr[index];
    }

    public int indexOf(int element) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == element) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(int element) {
        for (int i = size - 1; i >= 0; i--) {
            if (arr[i] == element) {
                return i;
            }
        }
        return -1;
    }

    public void remove(int index) {
        for (int i = index; i < size; i++) {
            arr[i] = arr[i + 1];
        }
        size--;
    }

    public void removeElement(int element) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == element) {
                for (int j = i; j < size - 1; j++) {
                    arr[j] = arr[j + 1];
                }
                size--;
                i--;
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < size; i++) {
            stringBuilder.append(arr[i]).append("\t");
        }
        return stringBuilder.toString();
    }
}

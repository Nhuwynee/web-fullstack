import java.util.Arrays;

public class MyArrayList {
    private int size;
    private int[] data;
    public MyArrayList() {
        data = new int[20];
        size = 0;
    }

    // kiểm tra số phần tử hiện tại(size) lớn hơn số phần tử trong mảng ko, nếu lớn hơn thì x2 mảng lên
    private void ensureCapacity() {
        if (size >= data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
    }

//    #### 1. add(int element):
//    Thêm phần tử element vào cuối.

    private void add (int element){
        ensureCapacity();
        data[size++] = element;
    }

//    ### 2. toString():
//    Trả về thông tin đối tượng
    @Override
    public String toString() {
        if(data.length==0) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(data[i]).append(", ");
        }
        sb.append(data[size - 1]).append("]");
        return sb.toString();
    }

//#### 3. add(int index, int element):
//    Thêm phần tử vào vị trí index
    private void add(int index, int element){
        if(index < 0 || index > size) throw new IndexOutOfBoundsException();
        ensureCapacity();
        for(int i = size; i > index; i--) {
            data[i] = data[i-1];
        }
        data[index] = element;
        size++;
    }

//#### 4. set(int index, int element):
//    Update phần tử tại vị trí index
    private void set(int index, int element){
        if(index < 0 || index > size) throw new IndexOutOfBoundsException();
        data[index] = element;
    }

//#### 5. get(int index):
//    Lấy phần tử tại index bất kỳ
    private int get(int index) {
        if(index < 0 || index > size) throw new IndexOutOfBoundsException();
        return data[index];
    }

//#### 6. indexOf(int element):
//    Lấy vị trí index phần tử đầu tiên tìm thấy
    private int indexOf(int element) {
        for(int i = 0; i < data.length; i++){
            if(data[i]==element)
                return i;
        }
        return -1;
    }

//#### 7. lastIndexOf(int element):
//    Lấy vị trí index phần tử cuối cùng tìm thấy
    private int lastIndexOf(int element) {
        for(int i = data.length - 1; i >= 0; i--){
            if(data[i]==element)
                return i;
        }
        return -1;
    }
//#### 8. remove(int index):
//    Xóa phần tử tại vị trí index
    private void remove(int index) {
        if(index < 0 || index > data.length) throw new IndexOutOfBoundsException();
        for(int i = index; i < data.length - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
    }

//#### 9. removeElement(int element):
//    Xóa tất cả phần tử element
    private void removeElement(int element ){
        for(int i = 0; i < data.length - 1; i++) {
            if(data[i] == element){
                data[i] = data[i + 1];
                size --;
            }
        }
    }
}

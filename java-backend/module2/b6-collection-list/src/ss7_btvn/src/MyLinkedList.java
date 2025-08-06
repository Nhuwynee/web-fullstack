package ss7_btvn.src;
public class MyLinkedList {
    private static class Node {
        private int data;
        Node next;
        Node (int data) {
            this.data = data;
        }
    }

    private Node head, tail;
    private int size;

//    #### 1. addFirst(int element)
//    Thêm phần tử element vào đầu
    public void addFirst(int element) {
        Node newNode = new Node(element);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

//    #### 2. toString():
//    Trả về thông tin đối tượng
    @Override
    public String toString() {
        if(head==null) return "";
        StringBuilder sb = new StringBuilder("");
        Node current = head;
        while(current != null){
            sb.append(current.data + "\t");
            current = current.next;
        }
        return sb.toString();
    }

//#### 3. addLast(int element)
//    Thêm phần tử element vào cuối
    private void addLast(int element) {
        if(head==null){
            addFirst(element);
        }else {
            Node newNode = new Node(element);
            tail.next = newNode;
            tail = newNode;
            size++;
        }
    }

//#### 4. add(int index, int element)
//    Thêm phần tử element vào vị trí index bất kỳ
    private void add(int index, int element){
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }else if(index == 0){
            addFirst(element);
        }else if(index == size){
            addLast(element);
        }else{
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            Node newNode = new Node(element);
            newNode.next = current.next;
            current.next = newNode;
            size ++;
        }
    }

//#### 5. removeFirst()
//    Xóa phần tử đầu
    private Integer removeFirst() {
        if(head == null){
            return null;
        }
        if (size == 1) {
            int value = head.data;
            head = null;
            tail = null;
            return value;
        }
        Node temp = head;
        head = temp.next;
        size--;
        return temp.data;
    }

//#### 6. removeLast()
//    Xóa phần tử cuối
    private Integer removeLast() {
        if(head == null){
            return null;
        }
        if(size == 1){
            int value = head.data;
            head = null;
            tail = null;
            return value;
        }
        Node temp = head;
        while(temp.next != tail){
            temp = temp.next;
        }
        int value = tail.data;
        tail = temp;
        tail.next = null;
        size --;
        return value;
    }

//#### 7. remove(int index)
//    Xóa phần tử ở vị trí index bất kỳ
    private Integer remove(int index) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else if(index == 0){
            removeFirst();
        } else if(index == size - 1){
            removeLast();
        } else{
            Node temp = head;
            for (int i = 0;i < index; i++){
                temp = temp.next;
            }
            Node current = temp.next;
            int value = current.data;
            temp.next = current.next;
            current = null;
            size--;
            return value;
        }
        return null;
    }

//#### 8. getFirst()
//    Lấy phần tử đầu
    private Integer getFirst() {
        if (head == null) return null;
        return head.data;
    }

//#### 9. getLast()
//    Lấy phần tử cuối
    private Integer getLast() {
        if (head == null) return null;
        return tail.data;
    }

//#### 10. get(int index)
//    Lấy phần tử tại index bất kỳ
    private int get(int index){
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node currentNode = head;
        for (int i = 0;i < index; i++){
            currentNode = currentNode.next;
        }
       return currentNode.data;
    }

//#### 11. set(int index, int element)
//    Update phần tử tại vị trí index
    private void set(int index, int element) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node currentNode = head;
        for (int i = 0;i < index; i++){
            currentNode = currentNode.next;
        }
        currentNode.data = element;
    }

//#### 12. indexOf(int element)
//    Lấy vị trí index phần tử đầu tiên tìm thấy
    private int indexOf(int element) {
        Node temp = head;
        int index = 0;

        while(temp != null){
          if(temp.data == element){
             return index;
          }
          temp = temp.next;
          index++;
        }
        return -1;
    }

//#### 13. lastIndexOf(int element)
//    Lấy vị trí index phần tử cuối cùng tìm thấy
    private int lastIndexOf(int element) {
        Node temp = head;
        int index = 0;
        int lastIndex = -1;
        while(temp != null){
            if(temp.data == element){
               lastIndex = index;
            }
            temp = temp.next;
            index++;
        }
        return lastIndex;
    }
}


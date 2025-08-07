package do_exercise;

public class MyLinkedList {

    private static class Node {
        private int value;
        private Node next;

        private Node(int value) {
            this.value = value;
        }
    }

    private Node head;
    private Node tail;
    private int size;

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

    public void addLast(int element) {
        if (head == null) {
            addFirst(element);
        } else {
            Node newNode = new Node(element);
            tail.next = newNode;
            tail = newNode;
            size++;
        }
    }

    public void add(int index, int element) throws Exception {
        if (index < 0 || index > size) {
            throw new Exception("Lỗi index ngoài phạm vi!");
        } else if (index == 0) {
            addFirst(element);
        } else if (index == size) {
            addLast(element);
        } else {
            Node temp = head;

            for (int i = 1; i < index; i++) {
                temp = temp.next;
            }

            Node newNode = new Node(element);
            newNode.next = temp.next;
            temp.next = newNode;
            size++;
        }
    }


    public void removeFirst() throws Exception {
        checkLinkedListNull();

        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
        }
        size--;
        System.out.println("Xóa đàu thành công!");
    }

    public void removeLast() throws Exception {
        checkLinkedListNull();

        if (size == 1) {
            head = null;
            tail = null;
            size--;
            return;
        }

        Node temp = head;
        while (temp.next != tail) {
            temp = temp.next;
        }

        tail = temp;
        tail.next = null;
        size--;
    }

    public void remove(int index) throws Exception {
        checkLinkedListNull();
        checkIndex(index);

        if (size == 1) {
            head = null;
            tail = null;
            size--;
            return;
        }
        if (index == 0) {
            removeFirst();
            return;
        }
        if (index == size - 1) {
            removeLast();
            return;
        }

        Node temp = head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }

        Node deleted = temp.next;
        temp.next = deleted.next;
        deleted.next = null;
        size--;
    }

    public Integer getFirst() {
        if (head == null) {
            return null;
        }
        return head.value;
    }

    public Integer getLast() {
        if (head == null) {
            return null;
        }
        return tail.value;
    }

    public Integer get(int index) throws Exception {
        checkIndex(index);

        if (head == null) {
            return null;
        }

        if (index == 0) {
            return getFirst();
        } else if (index == size - 1) {
            return getLast();
        } else {
            Node temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            return temp.value;
        }
    }

    public void set(int index, int element) throws Exception {
        checkLinkedListNull();
        checkIndex(index);

        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        temp.value = element;
    }

    public int indexOf(int element) throws Exception {
        checkLinkedListNull();

        Node temp = head;
        for (int i = 0; i < size; i++) {
            if (temp.value == element) {
                return i;
            }
            temp = temp.next;
        }
        return -1;
    }

    public int lastIndexOf(int element) throws Exception {
        checkLinkedListNull();

        int result = -1;
        Node temp = head;
        for (int i = 0; i < size; i++) {
            if (temp.value == element) {
                result = i;
            }
            temp = temp.next;
        }
        return result;
    }

    private void checkLinkedListNull() throws Exception {
        if (head == null) {
            throw new Exception("Không có phần từ nào.");
        }
    }

    private void checkIndex(int index) throws Exception {
        if (index < 0 || index >= size) {
            throw new Exception("Lỗi index ngoài phạm vi!");
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node temp = head;
        for (int i = 0; i < size; i++) {
            stringBuilder.append(temp.value).append("\t");
            temp = temp.next;
        }
        return stringBuilder.toString();
    }
}

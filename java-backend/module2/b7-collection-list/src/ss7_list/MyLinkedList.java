package ss7_list;

public class MyLinkedList {

    private static class Node {
        private final int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public void addFirst(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public void addLast(int value) {
        if (head == null) {
            addFirst(value);
        } else {
            Node newNode = new Node(value);
            tail.next = newNode;
            tail = newNode;
            size++;
        }
    }

    public void add(int index, int value) {
        if (index < 0 || index > size) {
            System.out.println("Lỗi: Ngoài phạm vi!");
        } else if (index == 0) {
            addFirst(value);
        } else if (index == size) {
            addLast(value);
        } else {
            Node temp = head;

            for (int i = 1; i < index; i++) {
                temp = temp.next;
            }

            Node newNode = new Node(value);
            newNode.next = temp.next;
            temp.next = newNode;
            size++;
        }
    }

    public Integer removeFirst() {
        if (head == null) {
            return null;
        }

        if (size == 1) {
            head = null;
            tail = null;
        }

        Node temp = head;
        head = head.next;
        size--;
        return temp.value;
    }

    public Integer removeLast() {
        if (head == null) {
            return null;
        }

        if (size == 1) {
            head = null;
            tail = null;
        }

        Node temp = head;
        while (temp.next != tail) {
            temp = temp.next;
        }

        int value = tail.value;
        tail = temp;
        tail.next = null;

        size--;
        return value;
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

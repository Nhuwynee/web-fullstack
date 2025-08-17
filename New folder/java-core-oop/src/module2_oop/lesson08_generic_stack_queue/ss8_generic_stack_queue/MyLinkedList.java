package ss8_generic_stack_queue;

public class MyLinkedList<E> {

    private static class Node<E> {
        private E value;
        private Node<E> next;

        private Node(E value) {
            this.value = value;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public void addLast(E element) {
        if (head == null) {
            addFirst(element);
        } else {
            Node<E> newNode = new Node<>(element);
            tail.next = newNode;
            tail = newNode;
            size++;
        }
    }

    public void add(int index, E element) throws Exception {
        if (index < 0 || index > size) {
            throw new Exception("Lỗi index ngoài phạm vi!");
        } else if (index == 0) {
            addFirst(element);
        } else if (index == size) {
            addLast(element);
        } else {
            Node<E> temp = head;

            for (int i = 1; i < index; i++) {
                temp = temp.next;
            }

            Node<E> newNode = new Node<>(element);
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

        Node<E> temp = head;
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

        Node<E> temp = head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }

        Node<E> deleted = temp.next;
        temp.next = deleted.next;
        deleted.next = null;
        size--;
    }

    public E getFirst() {
        if (head == null) {
            return null;
        }
        return head.value;
    }

    public E getLast() {
        if (head == null) {
            return null;
        }
        return tail.value;
    }

    public E get(int index) throws Exception {
        checkIndex(index);

        if (head == null) {
            return null;
        }

        if (index == 0) {
            return getFirst();
        } else if (index == size - 1) {
            return getLast();
        } else {
            Node<E> temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            return temp.value;
        }
    }

    public void set(int index, E element) throws Exception {
        checkLinkedListNull();
        checkIndex(index);

        Node<E> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        temp.value = element;
    }

    public int indexOf(E element) throws Exception {
        checkLinkedListNull();

        Node<E> temp = head;
        for (int i = 0; i < size; i++) {
            if (temp.value == element) {
                return i;
            }
            temp = temp.next;
        }
        return -1;
    }

    public int lastIndexOf(E element) throws Exception {
        checkLinkedListNull();

        int result = -1;
        Node<E> temp = head;
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
        Node<E> temp = head;
        for (int i = 0; i < size; i++) {
            stringBuilder.append(temp.value).append("\t");
            temp = temp.next;
        }
        return stringBuilder.toString();
    }
}


package lesson2.task1LinkedList;

public class MyLinkedList {

    public Node head;

    MyLinkedList(int data) {
        head = new Node(data);
    }

    public void insert(int data) {
        Node new_node = new Node(data);
        if (head.next == null) {
            head.next = new_node;
        } else {
            Node lastNode = head;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            lastNode.next = new_node;
        }
    }

    public int getLength() {
        int count = 0;
        Node currNode = head;
        while (currNode != null) {
            count++;
            currNode = currNode.next;
        }
        return count;
    }

    public String getItemAt(int index) {
        int listLength = getLength();
        if (index >= listLength)
            return "No such index. Try again.";
        else {
            int count = 0;
            Node currNode = head;
            while (count < index) {
                count++;
                currNode = currNode.next;
            }
            return Integer.toString(currNode.data);
        }
    }

    public String remove(int index) {
        Node prevNode = head;
        Node currNode = head.next;
        if (index >= getLength())
            return "No such index. Try again.";
        else {
            if (index == 0) {
                head = head.next;
                return "Delete " + currNode.data;
            }
            int count = 1;
            while (count < index) {
                count++;
                prevNode = currNode;
                currNode = currNode.next;
            }
            prevNode.next = currNode.next;
            return "Delete " + currNode.data;
        }
    }

}



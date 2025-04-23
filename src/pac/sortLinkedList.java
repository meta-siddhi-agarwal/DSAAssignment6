class sortLinkedList {

    class Node {
        int data;
        Node next;
    
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    Node head;

    void addNode(int data) {
        if(head == null) {
            head = new Node(data);
            return;
        }

        Node curr = head;
        while(curr.next!=null) {
            curr = curr.next;
        }

        curr.next = new Node(data);
    }

    void displayList(Node head) {
        Node temp = head;
        while(temp!=null) {
            System.out.print(temp.data+" --> ");
            temp = temp.next;
        }
        System.out.println();
    }

    Node partitionList(Node start,Node end) {

        if(start == null || end == null || start == end) {
            return start;
        }
        Node prev = start;
        Node curr = start;
        int pivot = end.data;

        while(curr!=end) {
            if(curr.data<pivot) {
                
                int temp = prev.data;
                prev.data = curr.data;
                curr.data = temp;

                prev = prev.next;
            }
            curr = curr.next;
        }

        int temp = prev.data;
        prev.data = pivot;
        end.data = temp;

        return prev;
    }

    void sortList(Node start,Node end) {
        if(start == null || start == end || start == end.next) {
            return;
        }

        Node prev = partitionList(start, end);
        if(prev!=null && prev!=start){
            Node temp = start;
            while(temp.next!=prev) {
                temp = temp.next;
            }
            sortList(start, temp);
        }
        

        if(prev!=null && prev.next!=null) {
            sortList(prev.next, end);
        }
        
    }

    public static void main(String[] args) {
        sortLinkedList obj = new sortLinkedList();

        obj.addNode(5);
        obj.addNode(2);
        obj.addNode(6);
        obj.addNode(4);
        obj.addNode(1);
        obj.addNode(3);

        Node headNode = obj.head;

        while(headNode.next!=null) {
            headNode = headNode.next;
        }

        System.out.println("Linked list before sorting --- ");
        obj.displayList(obj.head);

        obj.sortList(obj.head, headNode);

        System.out.println("After sorting -- ");
        obj.displayList(obj.head);

    }

}


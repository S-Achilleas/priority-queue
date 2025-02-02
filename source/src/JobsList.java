public class JobsList<T> {
private Node head = null;
private Node tail = null;
    private static class Node<T>{
        private T job;
        private Node next;

        public Node(T job){
            this.job = job;
            this.next = null;
        }
    }
    public void addJob(T job){
        Node newNode = new Node(job);
        if(head == null){
            head = newNode;
            tail = newNode;
        }else{
            tail.next = newNode;
            tail = newNode;
        }
    }
}

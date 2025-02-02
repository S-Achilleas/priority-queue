public class JobsList {
private Node head = null;
private Node tail = null;
    private static class Node{
        private Job job;
        private Node next;

        public Node(Job job){
            this.job = job;
            this.next = null;
        }
    }
    public void addJob(Job job){
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

public class Job {
    private final int id;
    private final int time;

    public Job(int id, int time) {
        this.id = id;
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "Job " + id + " (" + time + ")";
    }
}
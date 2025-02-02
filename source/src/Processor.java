import java.util.Comparator;

public class Processor implements Comparable<Processor> {
    private int id;
    private static int nextid = 1;
    private final JobsList<Job> processedJobs;
    private int totalTime;

    public Processor() {
        this.id = nextid++;
        this.processedJobs = new JobsList<>();
        this.totalTime = 0;
    }

    public int getTotalProcessingTime() {
        return totalTime;
    }

    public void addJob(Job job) {
        processedJobs.add(job);
        totalTime += job.getTime();
    }

    public JobsList<Job> getProcessedJobs() {
        return processedJobs;
    }

    public int getId() {
        return id;
    }


    @Override
    public int compareTo(Processor other) {
        int timeCompare = Integer.compare(this.totalTime, other.totalTime);
        if (timeCompare != 0) {
            return -timeCompare;
        } else {
            return Integer.compare(this.id, other.id);
        }
    }
}

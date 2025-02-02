import java.io.*;
import java.util.Comparator;

public class Greedy {
    public static void main(String[] args) {
        String filename = args[0];
        PQmax<Processor> pq = Comparisons.getProcessors(filename);
        Processor p = null;

            Job[] joblist = Comparisons.getJobs(filename);
            int num_of_jobs = joblist.length;
            Sort.quicksort(joblist, 0, joblist.length - 1);

            for (int i = 0; i < num_of_jobs; i++){
                p = pq.getmax();
                p.addJob(joblist[i]);
                pq.insert(p);
            }

            while (!pq.isEmpty()) {
                p = pq.getmax();
                if (pq.size() <= 50){
                    System.out.println("Processor " + p.getId() + " with total processing time: " + p.getTotalProcessingTime() + " and jobs: " + p.getProcessedJobs().print());
                }
            }
            System.out.println("Makespan: " + p.getTotalProcessingTime());
    }
}

import java.io.*;
import java.util.Comparator;

public class Greedy {
    public static void main(String[] args) {
        String filename = args[0];
        MaxPQ<Processor> pq = new MaxPQ<Processor>(Comparator.naturalOrder());

        try{
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            Processor p = null;
            int num_of_proc = Integer.parseInt(reader.readLine());
            int num_of_jobs = Integer.parseInt(reader.readLine());
            int validation = 0;
            String line = reader.readLine();

            for (int i = 0; i < num_of_proc; i++){
                p = new Processor();
                pq.insert(p);
            }

            while (line != null){
                String[] job = line.split(" ");
                Job j = new Job(Integer.parseInt(job[0]), Integer.parseInt(job[1]));
                p = pq.getmax();
                p.addJob(j);
                pq.insert(p);
                validation++;
                line = reader.readLine();
            }

            if (validation != num_of_jobs){
                System.err.println("Number of jobs does not match!");
                return;
            }

            while (!pq.isEmpty()) {
                p = pq.getmax();
                if (pq.size() <= 50){
                    System.out.println("Processor " + p.getId() + " with total processing time: " + p.getTotalProcessingTime() + " and jobs: " + p.getProcessedJobs().print());
                }
            }
            System.out.println("Makespan: " + p.getTotalProcessingTime());


        }catch (FileNotFoundException e){
            System.err.println("File not found!");
        }catch (IOException e) {
            System.err.println("Error reading file!");
        }
    }
}
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.io.*;
import java.util.Comparator;

public class Comparisons {
    public static void generateFiles() {
        int[] jobs = {100, 250, 500};
        int num_of_files = 10;
        Random rand = new Random();

        for (int N : jobs) {
            int processors = (int) Math.floor(Math.sqrt(N));
            for (int currentFile = 1; currentFile <= num_of_files; currentFile++) {
                String fileName = "test" + N + "_" + currentFile + ".txt";
                try (FileWriter writer = new FileWriter(fileName)) {
                    writer.write(processors + "\n");
                    writer.write(N + "\n");

                    for (int id = 1; id <= N; id++) {
                        int processingTime = rand.nextInt(100) + 1;
                        writer.write(id + " " + processingTime + "\n");
                    }

                } catch (IOException e) {
                    System.err.println(fileName + ": " + e.getMessage());
                }
            }
        }
    }


    public static int Greedy(String fn,boolean descending){
        String filename = fn;
        MaxPQ<Processor> pq = Comparisons.getProcessors(filename);
        Processor p = null;

        Job[] joblist = Comparisons.getJobs(filename);
        int num_of_jobs = joblist.length;


        if (descending){
            Sort.quicksort(joblist, 0, joblist.length - 1);
        }


        for (int i = 0; i < num_of_jobs; i++){
            p = pq.getmax();
            p.addJob(joblist[i]);
            pq.insert(p);
        }

        while (!pq.isEmpty()) {
            p = pq.getmax();
            //if (pq.size() <= 50){
               // System.out.println("Processor " + p.getId() + " with total processing time: " + p.getTotalProcessingTime() + " and jobs: " + p.getProcessedJobs().print());
           //}
        }
        //System.out.println("Makespan: " + p.getTotalProcessingTime());
        return p.getTotalProcessingTime();
    }

    public static MaxPQ<Processor> getProcessors(String fn) {
        String filename = fn;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            Processor p = null;
            int num_of_proc = Integer.parseInt(reader.readLine());
            MaxPQ<Processor> pq = new MaxPQ<Processor>(Comparator.naturalOrder());

            for (int i = 0; i < num_of_proc; i++) {
                p = new Processor();
                pq.insert(p);
            }

            return pq;

        }catch (FileNotFoundException e){
            System.err.println("File not found!");
        }catch (IOException e) {
            System.err.println("Error reading file!");
        }
        return null;
    }


    public static Job[] getJobs(String fn){
        String filename = fn;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            Processor p = null;
            int num_of_proc = Integer.parseInt(reader.readLine());
            int num_of_jobs = Integer.parseInt(reader.readLine());
            int validation = 0;

            Job[] jobs = new Job[num_of_jobs];
            String line = reader.readLine();
            int i =0;

            while (line != null){
                String[] job = line.split(" ");
                Job j = new Job(Integer.parseInt(job[0]), Integer.parseInt(job[1]));
                jobs[i] = j;
                validation++;
                line = reader.readLine();
                i++;
            }

            if (validation != num_of_jobs){
                System.err.println("Number of jobs does not match!");
                Job[] empty = new Job[num_of_jobs];
                return empty;
            }else{
                return jobs;
            }

        }catch (FileNotFoundException e){
            System.err.println("File not found!");
        }catch (IOException e) {
            System.err.println("Error reading file!");
        }
        return null;
    }

    public static void main(String[] args) {
        //generateFiles(); //generates .txt files for input
        int[] jobsCounter = {100, 250, 500};
        int numFilesPerJob = 10;

        double[] avgGreedy = new double[jobsCounter.length];
        double[] avgGreedyDesc = new double[jobsCounter.length];

        for (int i = 0; i < jobsCounter.length; i++) {
            int N = jobsCounter[i];
            int totalGreedy = 0;
            int totalGreedyDesc = 0;

            for (int fileNum = 1; fileNum <= numFilesPerJob; fileNum++) {
                String fileName = "Data/test" + N + "_" + fileNum + ".txt";

                int makespan1 = Greedy(fileName, false);
                totalGreedy += makespan1;

                int makespan2 = Greedy(fileName, true);
                totalGreedyDesc += makespan2;
            }

            avgGreedy[i] = (double) totalGreedy / numFilesPerJob;
            avgGreedyDesc[i] = (double) totalGreedyDesc / numFilesPerJob;
        }

        System.out.println("=== Results ===");
        for (int i = 0; i < jobsCounter.length; i++) {
            System.out.printf("N = %d:\n", jobsCounter[i]);
            System.out.printf("  Greedy Avg Makespan: %.2f\n", avgGreedy[i]);
            System.out.printf("  Greedy-Decreasing Avg Makespan: %.2f\n\n", avgGreedyDesc[i]);
        }
    }

}
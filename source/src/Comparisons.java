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

                    System.out.println(fileName);

                } catch (IOException e) {
                    System.err.println(fileName + ": " + e.getMessage());
                }
            }
        }
    }


    public static void Greedy(String fn){
        String filename = args[0];
        PQmax<Processor> pq = Comparisons.getProcessors(filename);
        Processor p = null;

        Job[] joblist = Comparisons.getJobs(filename);
        int num_of_jobs = joblist.length;
        //Sort.quicksort(joblist, 0, joblist.length - 1);

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

    public static PQmax<Processor> getProcessors(String fn) {
        String filename = fn;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            Processor p = null;
            int num_of_proc = Integer.parseInt(reader.readLine());
            PQmax<Processor> pq = new PQmax<Processor>(Comparator.naturalOrder());

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

}
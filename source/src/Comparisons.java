import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Comparisons {
    public static void generateFiles() {
        int[] jobCounts = {100, 250, 500};
        int filesPerN = 10;
        Random rand = new Random();

        for (int N : jobCounts) {
            int processors = (int) Math.floor(Math.sqrt(N));
            for (int currentFile = 1; currentFile <= filesPerN; currentFile++) {
                String fileName = "input_N" + N + "_" + currentFile + ".txt";
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
}
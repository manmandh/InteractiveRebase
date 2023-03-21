package StringMulti;
import java.io.*;
import java.util.*;
public class MultiThreadingArrange {
    public static void main(String[] args) {
        String inputFilename = "input.txt";
        String outputFilename = "output.txt";
        Scanner scanner = new Scanner(System.in);;
        int NumThr = Integer.parseInt(scanner.nextLine());
        try {
            List<Integer> inputList = readInputFile(inputFilename);
            List<List<Integer>> SubLists = splitIntoSubLists(inputList, NumThr);
            //SubList : trích xuất các mảng phần tử để trả về 
            List<Thread> threads = startSortingThreads(SubLists);
            joinThreads(threads);
            List<Integer> sortedList = mergeSortedLists(SubLists);
            writeOutputFile(outputFilename, sortedList);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static List<Integer> readInputFile(String filename) throws IOException {
        List<Integer> inputList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split("\\s+");
            for (String token : tokens) {
                inputList.add(Integer.parseInt(token));
            }
        }
        reader.close();
        return inputList;
    }
    private static List<List<Integer>> splitIntoSubLists(List<Integer> inputList, int numSubLists) {
        int SubListSize = (int) Math.ceil((double) inputList.size() / numSubLists);
        List<List<Integer>> SubLists = new ArrayList<>();
        for (int i = 0; i < inputList.size(); i += SubListSize) {
            int endIdx = Math.min(i + SubListSize, inputList.size());
            SubLists.add(inputList.subList(i, endIdx));
        }
        return SubLists;
    }

    private static List<Thread> startSortingThreads(List<List<Integer>> SubLists) {
        List<Thread> threads = new ArrayList<>();
        for (List<Integer> sublist : SubLists) {
            Thread t = new Thread(() -> Collections.sort(sublist));
            threads.add(t);
            t.start();
        }
        return threads;
    }
    private static void joinThreads(List<Thread> threads) throws InterruptedException {
        for (Thread t : threads) {
            t.join();
        }
    }
    private static List<Integer> mergeSortedLists(List<List<Integer>> SubLists) {
        List<Integer> result = new ArrayList<>();
        while (SubLists.stream().anyMatch(sublist -> !sublist.isEmpty())) {
            int minVal = Integer.MAX_VALUE;
            int minIdx = -1;
            for (int i = 0; i < SubLists.size(); i++) {
                List<Integer> sublist = SubLists.get(i);
                if (!sublist.isEmpty() && sublist.get(0) < minVal) {
                    minVal = sublist.get(0);
                    minIdx = i;
                }
            }
            result.add(minVal);
            SubLists.get(minIdx).remove(0);
        }
        return result;
    }

    private static void writeOutputFile(String filename, List<Integer> outputList) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (int i = 0;i < outputList.size();i++) {
            writer.write(Integer.toString(outputList.get(i)));
            if (i < outputList.size() - 1) {
                writer.newLine();
            }
            writer.close();
        }
    }
}

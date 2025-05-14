import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;  // Bad practice but just import everything for the purposes of PE

//NOTE: Change class name 
public class Day01 {
  public static void main(String[] args) throws IOException {
    FileReader in = new FileReader("./Inputs/Day01.txt");
    BufferedReader br = new BufferedReader(in);
    PrintWriter pw = new PrintWriter(System.out);

    // Psuedo-Code

    // NOTE: Copy and Uncomment the one(s) that are required
    // String input = br.readLine();
    // String[] input = br.readLine().split(" ");
    // int input = Integer.parseInt(br.readLine());
    // int N = Integer.parseInt(input[0]);

    // Part 1
    ArrayList<Integer> list1 = new ArrayList<>();
    ArrayList<Integer> list2 = new ArrayList<>();

    String line;
    while ((line = br.readLine()) != null) {
      String[] input = line.split("   ");
      list1.add(Integer.parseInt(input[0]));
      list2.add(Integer.parseInt(input[1]));
    }

    Collections.sort(list1);
    Collections.sort(list2);

    int i = 0;
    Integer distance = 0;
    while (i < list1.size()) {
      distance += Math.abs(list1.get(i) - list2.get(i));
      i++;
    }

    pw.println("Part 1 Answer: " + distance);

    // Part2
    
    HashMap<Integer, Integer> occurrenceMap = new HashMap<>();

    i = 0;
    while (i < list2.size()) {
      int cur = list2.get(i);
      int curCount = occurrenceMap.getOrDefault(cur, 0) + 1;
      occurrenceMap.put(cur, curCount);
      i++;
    }

    i = 0;
    int similarity = 0;
    while (i < list1.size()) {
      int cur = list1.get(i);
      similarity += cur * occurrenceMap.getOrDefault(cur, 0);
      i++;
    }

    pw.println("Part 2 Answer: " + similarity);
    
    br.close();
    pw.close();
  }
}


// NOTE: Uncomment and replace 'Something' and 'field', as well as adding other fields, as required
// class Something implements Comparable<Something> {
//   // NOTE: To perform `compareTo`, we have to use the non-primitive datatype
//   Integer field;
//
//   public Something(int field) {
//     this.field = field;
//   }
//
//   @Override
//   public int compareTo(Something other) {
//     return this.field.compareTo(other.field);
//   }
// }

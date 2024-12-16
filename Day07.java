import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.util.*;  // Bad practice but just import everything for the purposes of PE
import java.util.stream.*;

//NOTE: Change class name 
public class Day07 {
  public static void main(String[] args) throws IOException {
    // Note: Change input file name
    FileReader in = new FileReader("./Inputs/Day07.txt");
    BufferedReader br = new BufferedReader(in);
    PrintWriter pw = new PrintWriter(System.out);

    HashMap<Long, List<Long>> inputMap = new HashMap<>();

    String line;
    while ((line = br.readLine()) != null) {
      // NOTE: Copy and Uncomment the one(s) that are required
      // int input = Integer.parseInt(line);
      String[] input = line.split(": ");
      long target = Long.parseLong(input[0]);
      List<Long> intList = Stream.of(input[1].split(" "))
        .map(Long::valueOf)
        .collect(Collectors.collectingAndThen(
          Collectors.toList(), l -> {
            Collections.reverse(l); 
            return l;
          }
        ));
      inputMap.put(target, intList);
    }
    //
    // Part 1
    //
    long part1 = 0;

    for (long target : inputMap.keySet()) {
      int latest = -1; // 1 for multiply, 0 for add
      long curTarget = target;
      List<Long> reversedVarList = inputMap.get(target);

      for (long cur : reversedVarList) {
        // pw.println(curTarget);

        if (canMultiply(curTarget, cur)) {
          // pw.println("Dividing current target");
          curTarget = curTarget / cur;
          latest = 1;
        }
        else {
          // pw.println("Subtracting current target");
          curTarget = curTarget - cur;
          latest = 0;
        }
      }

      if (curTarget == latest) {
        part1 += target;
      }
    }

    pw.println("Part 1 Answer: " + part1);
    //
    // Part 2
    //
    // pw.println("Part 2 Answer: " + part2);

    br.close();
    pw.close();
  }

  public static Boolean canMultiply(long curTarget, long val) {
    if (curTarget % val == 0L) {
      return true;
    }

    return false;
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

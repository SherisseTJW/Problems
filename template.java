import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;  // Bad practice but just import everything for the purposes of PE

//NOTE: Change class name 
public class Template {
  public static void main(String[] args) throws IOException {
    // Note: Change input file name
    FileReader in = new FileReader("./Inputs/file.txt");
    BufferedReader br = new BufferedReader(in);
    PrintWriter pw = new PrintWriter(System.out);

    // String line;
    // while ((line = br.readLine()) != null) {
    //  // NOTE: Copy and Uncomment the one(s) that are required
    //  // int input = Integer.parseInt(line);
    //  // String[] input = line.split(" ");
    //  // int N = Integer.parseInt(input[0]);
    // }
    //
    // Part 1
    //
    // pw.println("Part 1 Answer: " + part1);
    //
    // Part 2
    //
    // pw.println("Part 2 Answer: " + part2);

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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;  // Bad practice but just import everything for the purposes of PE

//TODO: Change class name 
public class CountHiddenSequences {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);

    int[] input = new int[]{1, -3, 4};
    int lower = 1;
    int upper = 6;

    int result = numberOfArrays(input, lower, upper);

    pw.println(result);

    br.close();
    pw.close();
  }

  public static int numberOfArrays(int[] differences, int lower, int upper) {
    int minVal = 0;
    int maxVal = 0;
    int sum = 0;

    for (int i = 0; i < differences.length; i++) {
      sum += differences[i]; 

      if (sum > maxVal) {
        maxVal = sum;
      }
      else if (sum < minVal) {
        minVal = sum;
      }

      if (maxVal - minVal > upper - lower) {
        return 0;
      }
    }

    return Math.max(0, (upper - lower) - (maxVal - minVal) + 1);
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

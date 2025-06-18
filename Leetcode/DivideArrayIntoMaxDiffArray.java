import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*; // Bad practice but just import everything for the purposes of PE

public class DivideArrayIntoMaxDiffArray {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);

    int[] input = new int[] { 2, 4, 2, 2, 5, 2 };
    int k = 2;

    int[][] solution = question(input, k);

    for (int[] arr : solution) {
      for (int elem : arr) {
        pw.println(elem);
      }
    }

    br.close();
    pw.close();
  }

  public static int[][] question(int[] nums, int k) {
    int numArrays = nums.length / 3;
    int[][] solution = new int[numArrays][3];

    Arrays.sort(nums);

    int count = 0;
    for (int i = 0; i < nums.length; i = i + 3) {
      int fst = nums[i];
      int snd = nums[i + 1];
      int trd = nums[i + 2];

      // As long as we have one pair which the diff > k, we immediately fail
      if (snd - fst > k || trd - snd > k || trd - fst > k) {
        return new int[numArrays][3];
      }

      int[] cur = new int[] { fst, snd, trd };
      solution[count] = cur;
      count = count + 1;
    }

    return solution;
  }
}

// NOTE: Uncomment and replace 'Something' and 'field', as well as adding other
// fields, as required
// class Something implements Comparable<Something> {
// // NOTE: To perform `compareTo`, we have to use the non-primitive datatype
// Integer field;
//
// public Something(int field) {
// this.field = field;
// }
//
// @Override
// public int compareTo(Something other) {
// return this.field.compareTo(other.field);
// }
// }

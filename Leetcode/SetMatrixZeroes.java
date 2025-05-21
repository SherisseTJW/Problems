import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*; // Bad practice but just import everything for the purposes of PE

//TODO: Change class name 
public class SetMatrixZeroes {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);

    // int[][] input = new int[][] {
    // { 0, 1, 2, 0 },
    // { 3, 4, 5, 2 },
    // { 1, 3, 1, 5 }
    // };

    // Test Case 120
    // int[][] input = new int[][] {
    // { 2147483647, 2, 9 },
    // { 2, 6, 7 },
    // { 1, 8, 8 },
    // { 5, 0, 1 },
    // { 9, 6, 0 }
    // };

    // Test Case 148
    int[][] input = new int[][] {
        { 3, 5, 5, 6, 9, 1, 4, 5, 0, 5 },
        { 2, 7, 9, 5, 9, 5, 4, 9, 6, 8 },
        { 6, 0, 7, 8, 1, 0, 1, 6, 8, 1 },
        { 7, 2, 6, 5, 8, 5, 6, 5, 0, 6 },
        { 2, 3, 3, 1, 0, 4, 6, 5, 3, 5 },
        { 5, 9, 7, 3, 8, 8, 5, 1, 4, 3 },
        { 2, 4, 7, 9, 9, 8, 4, 7, 3, 7 },
        { 3, 5, 2, 8, 8, 2, 2, 4, 9, 8 }
    };

    question(input);

    for (int i = 0; i < input.length; i++) {
      for (int j = 0; j < input[i].length; j++) {
        System.out.print(input[i][j] + " ");
      }
      System.out.println();
    }
    br.close();
    pw.close();
  }

  public static void question(int[][] nums) {
    boolean fstRowZero = false;
    boolean fstColZero = false;

    for (int i = 0; i < nums[0].length; i++) {
      if (nums[0][i] == 0) {
        fstRowZero = true;
        break;
      }
    }

    for (int i = 0; i < nums.length; i++) {
      if (nums[i][0] == 0) {
        fstColZero = true;
        break;
      }
    }

    for (int i = 0; i < nums.length; i++) {
      boolean hasZero = false;
      for (int j = 0; j < nums[i].length; j++) {
        int cur = nums[i][j];

        if (cur == 0) {
          nums[0][j] = 0;
          hasZero = true;
        }
      }

      if (hasZero) {
        nums[i][0] = 0;
      }
    }

    if (fstRowZero || fstColZero) {
      nums[0][0] = 0;
    }

    // Iterate through the first row
    for (int i = 1; i < nums[0].length; i++) {

      if (nums[0][i] == 0) {
        for (int j = 0; j < nums.length; j++) {
          nums[j][i] = 0;
        }
      }

      if (fstRowZero) {
        nums[0][i] = 0;
      }
    }

    // Iterate through the first column
    for (int i = 1; i < nums.length; i++) {
      if (nums[i][0] == 0) {

        for (int j = 0; j < nums[i].length; j++) {
          nums[i][j] = 0;
        }
      }

      if (fstColZero) {
        nums[i][0] = 0;
      }
    }
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

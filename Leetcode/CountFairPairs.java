import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;  // Bad practice but just import everything for the purposes of PE

//NOTE: Change class name 
public class CountFairPairs {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);

    int[] nums = new int[]{1,7,9,2,5};
    int lower = 11;
    int upper = 11;

    long result = countFairPairs(nums, lower, upper);

    pw.println(result);

    br.close();
    pw.close();
  }

  public static long countFairPairs(int[] nums, int lower, int upper) {
    Arrays.sort(nums);
    long numCount = 0L;

    for (int i = 0; i < nums.length; i++) {
      int upperbound = binarySearchRight(nums, i + 1, lower, upper);
      int lowerbound = binarySearchLeft(nums, i + 1, lower, upper);

      if (lowerbound == -1 || upperbound == -1) {
        continue;
      }

      if (upperbound >= lowerbound) {
        System.out.println("Incrementing count by " + (upperbound - lowerbound + 1));
        numCount += upperbound - lowerbound + 1;
      }
    }

    return numCount;
  }

  public static int binarySearchLeft(int[] nums, int current, int lower, int upper) {
    int high = nums.length - 1;
    int low = current;

    int ans = -1;

    while (low <= high) {
      int mid = low + ((high - low) / 2);

      int sum = nums[current - 1] + nums[mid];

      // If sum smaller than lower, bring it up
      if (sum < lower) {
        low = mid + 1;
      }
      else {
        // If sum greater than lower, bring it down
        ans = mid;
        high = mid - 1;
      }
    }

    return ans;
  }

  public static int binarySearchRight(int[] nums, int current, int lower, int upper) {
    int high = nums.length - 1;
    int low = current;

    int ans = -1;

    while (low <= high) {
      int mid = low + ((high - low) / 2);

      int sum = nums[current - 1] + nums[mid];

        if (sum <= upper) {
          ans = mid; // Index of the current element such that lower <= i + element <= upper
          low = mid + 1;
        }
        
        else {
          // If sum too high, bring it down
          high = mid - 1;
        }
    }

    return ans;
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

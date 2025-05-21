import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*; // Bad practice but just import everything for the purposes of PE

//TODO: Change class name 
public class ZeroArrayTrans1 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);

    int[] nums = new int[] { 4, 3, 2, 1 };
    int[][] queries = new int[][] {
        { 1, 3 },
        { 0, 2 }
    };

    pw.println(question(nums, queries));

    br.close();
    pw.close();
  }

  public static boolean question(int[] nums, int[][] queries) {
    int[] diffArr = new int[nums.length + 2];

    for (int[] query : queries) {
      diffArr[query[0]]++;
      diffArr[query[1] + 1]--;
    }

    for (int i = 1; i < diffArr.length; i++) {
      diffArr[i] += diffArr[i - 1];
    }

    for (int i = 0; i < nums.length; i++) {
      // System.out.println("Current num: " + nums[i] + " with diffArr: " +
      // diffArr[i]);
      if (nums[i] > diffArr[i]) {
        return false;
      }
    }

    return true;
  }
}

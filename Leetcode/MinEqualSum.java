import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;  // Bad practice but just import everything for the purposes of PE

public class MinEqualSum {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);

    int[] num1 = new int[]{20,0,18,11,0,0,0,0,0,0,17,28,0,11,10,0,0,15,29};
    int[] num2 = new int[]{16,9,25,16,1,9,20,28,8,0,1,0,1,27};

    pw.println(minEqualSum(num1, num2));

    br.close();
    pw.close();
  }

  public static long minEqualSum(int[] num1, int[] num2) {
    long sum1 = 0;
    long zeroCount1 = 0;
    for (int i = 0; i < num1.length; i++) {
      int cur = num1[i];
      if (cur == 0) {
        zeroCount1++;
      }

      sum1 += cur;
    }

    long sum2 = 0;
    long zeroCount2 = 0;
    for (int i = 0; i < num2.length; i++) {
      int cur = num2[i];
      if (cur == 0) {
        zeroCount2++;
      }
      sum2 += num2[i];
    }

    // If one side more than the other, we have 4 scenarios
    // Side A has > 1 zero while Side B has no zeros and side A has a smaller sum --> return Side B sum
    // Side B has > 1 zero while Side A has no zeros and side B has a smaller sum --> return Side A sum
    // Both side A and B have > 1 zero --> return max(Side A + zero count, Side B + zero count)
    // Both side A and B have 0 zero --> return -1

    if (sum1 > sum2) {
      // If num2 is smaller but we have no zeros to bring up the sum, return -1
      if (zeroCount2 == 0) {
        return -1;
      }

      // If num2 has zeros, we have two scenarios
      // num1 has no zeros and num2 has more zeros than the difference between the original sum --> return -1 since we cannot pad num1 w/o zeros
      // if num1 has zeros --> return max(num1 + zero count, num2 + zero count)
      if (zeroCount1 == 0 && zeroCount2 > (sum1 - sum2)) {
        return -1;
      }

      return Math.max(sum1 + zeroCount1, sum2 + zeroCount2);
    }
    else if (sum2 > sum1) {
      if (zeroCount1 == 0) {
        return -1;
      }

      if (zeroCount2 == 0 && zeroCount1 > (sum2 - sum1)) {
        return -1;
      }

      return Math.max(sum1 + zeroCount1, sum2 + zeroCount2);
    }
    else {
      // If already equal, check if one side has zeros and the other doesnt. If so, return -1 since we MUST pad
      if ((zeroCount1 > 0 && zeroCount2 == 0) || (zeroCount2 > 0 && zeroCount1 == 0)) {
        return -1;
      }
      // if both has, add the max number of zeros and return the sum
      if (zeroCount1 > zeroCount2) {
        return sum1 + zeroCount1;
      }
      else {
        return sum2 + zeroCount2;
      }
    }
  }
}


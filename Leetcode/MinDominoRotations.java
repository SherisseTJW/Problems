import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;  // Bad practice but just import everything for the purposes of PE

//TODO: Change class name 
public class MinDominoRotations {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);

    // int[] tops = new int[]{2,1,2,4,2,2};
    // int[] bottoms = new int[]{5,2,6,2,3,2};

    int[] tops = new int[]{1,2,1,1,1,2,2,2};
    int[] bottoms = new int[]{2,1,2,2,2,2,2,2};

    int result = minDominoRotations(tops, bottoms);
    pw.println(result);

    br.close();
    pw.close();
  }

  public static int getTopCount(int[] tops, int[] bottoms, int initial) {
    int count = 0;
    boolean flag = false;

    for (int i = 0; i < tops.length - 1; i++) {
      int nxtTop = tops[i + 1];
      int nxtBtm = bottoms[i + 1];

      if (nxtTop == initial) {
        continue;
      }
      else if (nxtBtm == initial) {
        count++;
      }
      else {
        flag = true;
        break;
      }
    }

    if (flag) {
      return Integer.MAX_VALUE;
    }

    return count;
  }

  public static int getBtmCount(int[] tops, int[] bottoms, int initial) {
    int count = 0;
    boolean flag = false;

    for (int i = 0; i < bottoms.length - 1; i++) {
      int nxtTop = tops[i + 1];
      int nxtBtm = bottoms[i + 1];

      if (nxtBtm == initial) {
        continue;
      }
      else if (nxtTop == initial) {
        count++;
      }
      else {
        flag = true;
        break;
      }
    }

    if (flag) {
      return Integer.MAX_VALUE;
    }
    return count;
  }


  public static int minDominoRotations(int[] tops, int[] bottoms) {
    int initialTopCount = getTopCount(tops, bottoms, tops[0]);
    int rotatedTopCount = getTopCount(tops, bottoms, bottoms[0]) + 1;
    int initialBtmCount = getBtmCount(tops, bottoms, bottoms[0]);
    int rotatedBtmCount = getBtmCount(tops, bottoms, tops[0]) + 1;

    if (initialTopCount == Integer.MAX_VALUE && rotatedTopCount == Integer.MAX_VALUE && initialBtmCount == Integer.MAX_VALUE && rotatedBtmCount == Integer.MAX_VALUE) {
      return -1;
    }

    if (rotatedTopCount != Integer.MAX_VALUE) {
      rotatedTopCount++;
    }
    if (rotatedBtmCount != Integer.MAX_VALUE) {
      rotatedBtmCount++;
    }

    int optimalTop = Math.min(initialTopCount, rotatedTopCount);
    int optimalBtm = Math.min(initialBtmCount, rotatedBtmCount);

    return Math.min(optimalTop, optimalBtm);
  }
}


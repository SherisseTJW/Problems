import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;  // Bad practice but just import everything for the purposes of PE

//TODO: Change class name 
public class CountNumBalancedPermutations {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);

    // int[] input = new int[]{elem};

    br.close();
    pw.close();
  }

  public static int question() {
    int ans = 0;

    return ans;
  }

  public static int getNum(String cur, HashMap<String, Integer> countMap) {
    if (countMap.containsKey(cur)) {
      return countMap.get(cur);
    }

    return 0;
  }
}


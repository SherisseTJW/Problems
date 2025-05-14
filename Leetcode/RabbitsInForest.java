import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;  // Bad practice but just import everything for the purposes of PE

//TODO: Change class name 
public class RabbitsInForest {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);

    int[] input = new int[]{2, 2, 2, 2};

    int result = numRabbits(input);
    pw.println(result);

    br.close();
    pw.close();
  }

  public static int numRabbits(int[] answers) {
    int ans = 0;
    HashMap<Integer, Integer> rabbitCountMap = new HashMap<>();

    for (int i = 0; i < answers.length; i++) {
      int cur = answers[i];

      rabbitCountMap.putIfAbsent(cur, 0);
      int curCount = rabbitCountMap.get(cur) + 1;
      rabbitCountMap.put(cur, curCount);
    }

    for (Integer key : rabbitCountMap.keySet()) {
      int count = rabbitCountMap.get(key);

      if (key == 0) {
        ans += count;
      }
      else if (key >= count) {
        ans += key;
        ans += 1;
      }
      else {
        int base = key + 1;
        int mul = (count / (key + 1)) + ((count % (key + 1)) == 0 ? 0 : 1);

        ans += base * mul;
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

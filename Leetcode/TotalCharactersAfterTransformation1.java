import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;  // Bad practice but just import everything for the purposes of PE

//TODO: Change class name 
public class TotalCharactersAfterTransformation1 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);

    String test1_s = "abcyy";
    int test1_t = 2;

    String test2_s = "azbk";
    int test2_t = 1;

    String test464_s = "rjjnypjrjnpqos";
    int test464_t = 20;

    pw.println(faster(test464_s, test464_t));

    br.close();
    pw.close();
  }

  public static int faster(String s, int t) {
    HashMap<Character, Integer> freqMap = new HashMap<>();

    for (int i = 0; i < s.length(); i++) {
      char cur = s.charAt(i);
      int curCount = freqMap.getOrDefault(cur, 0) + 1;
      freqMap.put(cur, curCount);
    }

    while (t-- > 0) {
      int zCount = freqMap.getOrDefault('z', 0);

      for (char c = 'y'; c >= 'a'; c--) {
        int curCount = freqMap.getOrDefault(c, 0);
        char nxt = (char) (c + 1);
        freqMap.put(nxt, curCount);
      }

      int bCount = freqMap.getOrDefault('b', 0);
      
      freqMap.put('a', zCount);
      freqMap.put('b', bCount + zCount);
    }

    long totalLength = 0;
    for (int count : freqMap.values()) {
      totalLength += count;
    }

    return (int) (totalLength % (int) (Math.pow(10, 9) + 7));
  }

  public static int question(String s, int t) {
    String curString = s;

    while (t-- > 0) {
      StringBuilder sb = new StringBuilder();

      for (int i = 0; i < curString.length(); i++) {
        char cur = curString.charAt(i);

        if (cur == 'z') {
          sb.append("ab");
        }
        else {
          sb.append(++cur);
        }
      }

      curString = sb.toString();
    }

    return (curString.length() % (int) (Math.pow(10, 9) + 7));
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

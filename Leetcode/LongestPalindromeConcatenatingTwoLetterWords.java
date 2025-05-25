import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*; // Bad practice but just import everything for the purposes of PE

//TODO: Change class name 
public class LongestPalindromeConcatenatingTwoLetterWords {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);

    String[] words = new String[] { "dd", "aa", "bb", "dd", "aa", "dd", "bb", "dd", "aa", "cc", "bb", "cc", "dd",
        "cc" };
    pw.println(question(words));

    br.close();
    pw.close();
  }

  public static int question(String[] words) {
    int ans = 0;

    HashMap<String, Integer> wordCountMap = new HashMap<>();
    for (String word : words) {
      int count = wordCountMap.getOrDefault(word, 0) + 1;
      wordCountMap.put(word, count);
    }

    boolean flag = false;
    for (String word : words) {
      if (word.charAt(0) == word.charAt(1)) {
        // System.out.println("Current word: " + word);

        int count = wordCountMap.get(word);
        if (count <= 0) {
          continue;
        }

        if (count >= 2) {
          int numTimes = count / 2;
          ans += 4 * numTimes;

          // System.out.println("Incrementing count by " + (2 * numTimes));
        }

        if (count % 2 != 0) {
          flag = true;
        }

        wordCountMap.put(word, 0);

        continue;
      }

      String reversed = new StringBuilder(word).reverse().toString();

      // System.out.println("Reversed: " + reversed);
      if (wordCountMap.containsKey(reversed) && wordCountMap.get(reversed) > 0) {
        ans += 2;
        int count = wordCountMap.get(reversed) - 1;
        wordCountMap.put(reversed, count);
      }
    }

    if (flag) {
      ans += 2;
    }

    return ans;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;  // Bad practice but just import everything for the purposes of PE

//NOTE: Change class name 
public class CountAndSay {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);

    // String line;
    // while ((line = br.readLine()) != null) {
    //   // NOTE: Copy and Uncomment the one(s) that are required
    //   // int input = Integer.parseInt(line);
    //   // String[] input = line.split(" ");
    //   // int N = Integer.parseInt(input[0]);
    // }

    int n = Integer.parseInt(br.readLine());

    String cur = "1";

    // NOTE: I don't think I used this in the end lolz
    Map<String, String> rleMap= new HashMap<>();
    rleMap.put(cur, "11");

    for (int i = 2; i <= n; i++) {
      String tmp = encode(rleMap, cur);
      rleMap.put(cur, tmp);

      cur = tmp;
    }

    pw.println(cur);


    br.close();
    pw.close();
  }

  public static String encode(Map<String, String> rleMap, String curString) {
    char curChar;
    int charCount = 0;

    StringBuilder next = new StringBuilder();

    for (int i = 0; i < curString.length(); i++) {
      curChar = curString.charAt(i);
      charCount++;

      while (true) {
        if ((i + 1) >= curString.length()) {
          break;
        }

        if (curString.charAt(i + 1) == curChar) {
          i++;
          charCount++;
        }
        else {
          break;
        }
      }

      next.append(charCount).append(curChar);
      charCount = 0;
    }

    return next.toString();
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

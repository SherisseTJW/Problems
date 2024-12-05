import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;  // Bad practice but just import everything for the purposes of PE

//NOTE: Change class name 
public class Day02 {
  public static void main(String[] args) throws IOException {
    FileReader in = new FileReader("./Inputs/Day02.txt");
    BufferedReader br = new BufferedReader(in);
    PrintWriter pw = new PrintWriter(System.out);

    ArrayList<String> inputArr = new ArrayList<>();

    String line;
    while ((line = br.readLine()) != null) {
      // NOTE: Copy and Uncomment the one(s) that are required
      // int input = Integer.parseInt(br.readLine());
      // int N = Integer.parseInt(input[0]);
      inputArr.add(line);
    }

    // Part 1

    int i = 0;
    int numSafe = 0;
    while (i < inputArr.size()) {
      String[] curLine = inputArr.get(i).split(" ");

      if (Day02.isIncreasing(curLine) ^ Day02.isDecreasing(curLine)) {
        numSafe++;
      }

      i++;
    }

    pw.println("Part 1 Answer: " + numSafe);
    //
    // Part 2

    i = 0;
    numSafe = 0;
    while (i < inputArr.size()) {
      String[] curLine = inputArr.get(i).split(" ");

      List<Integer> intInput = new ArrayList<>();
      for (int j = 0; j < curLine.length; j++) {
        intInput.add(Integer.parseInt(curLine[j]));
      }

      if (isOk(intInput)) {
        numSafe++;
      }

      i++;
    }

    pw.println("Part 2 Answer: " + numSafe);

    br.close();
    pw.close();
  }

  public static Boolean isOk(List<Integer> curLine) {
    // If the curLine is already safe
    if (Day02.isIncreasing(curLine) ^ Day02.isDecreasing(curLine)) {
      return true;
    }

    int k = 0;
    while (k < curLine.size() - 1) {
      List<Integer> tmpLine = new ArrayList<>(curLine);
      tmpLine.remove(k);
      // System.out.println(tmpLine);

      if (Day02.isIncreasing(tmpLine) ^ Day02.isDecreasing(tmpLine)) {
        // As long as removing any allows it to be true, can immediately return 
        return true;
      }

      k++;
    }

    return false;
  }

  public static Boolean isIncreasing(List<Integer> curLine) {
    int i = 0;

    while (i < curLine.size() - 1) {
      int cur = curLine.get(i);
      int nxt = curLine.get(i + 1);
      // int nxt = Integer.parseInt(curLine[i + 1]);
      int diff = nxt - cur;

      if (diff <= 0 || diff > 3) {
        return false;
      }

      i++;
    }

    return true;
  }

  public static Boolean isDecreasing(List<Integer> curLine) {
    int i = 0;

    while (i < curLine.size() - 1) {
      int cur = curLine.get(i);
      int nxt = curLine.get(i + 1);
      int diff = cur - nxt;

      if (diff <= 0 || diff > 3) {
        return false;
      }

      i++;
    }

    return true;
  }

  public static Boolean isIncreasing(String[] curLine) {
    int i = 0;

    while (i < curLine.length - 1) {
      int cur = Integer.parseInt(curLine[i]);
      int nxt = Integer.parseInt(curLine[i + 1]);
      int diff = nxt - cur;

      if (diff <= 0 || diff > 3) {
        return false;
      }

      i++;
    }

    return true;
  }

  public static Boolean isDecreasing(String[] curLine) {
    int i = 0;

    while (i < curLine.length - 1) {
      int cur = Integer.parseInt(curLine[i]);
      int nxt = Integer.parseInt(curLine[i + 1]);
      int diff = cur - nxt;

      if (diff <= 0 || diff > 3) {
        return false;
      }

      i++;
    }

    return true;
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

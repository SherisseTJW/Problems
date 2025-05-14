import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
// import java.util.*;  // Bad practice but just import everything for the purposes of PE

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.MatchResult;
import java.util.stream.Stream;

//NOTE: Change class name 
public class Day03 {
  public static Boolean enabled = true;

  public static void main(String[] args) throws IOException {
    FileReader in = new FileReader("./Inputs/Day03.txt");
    BufferedReader br = new BufferedReader(in);
    PrintWriter pw = new PrintWriter(System.out);

    String line;
    String fullInput = "";
    while ((line = br.readLine()) != null) {
     // NOTE: Copy and Uncomment the one(s) that are required
     // int input = Integer.parseInt(line);
     // String[] input = line.split(" ");
     // int N = Integer.parseInt(input[0]);
      fullInput += line;
    }
    //
    // Part 1
    //

    Pattern mulPattern = Pattern.compile("mul\\([0-9]+,[0-9]+\\)");
    Matcher mulMatcher = mulPattern.matcher(fullInput);
    Stream<MatchResult> matched = mulMatcher.results();

    int part1 = matched.mapToInt(x -> getMulValue(x.group())).sum();

    pw.println("Part 1 Answer: " + part1);
    //
    // Part 2
    //
    mulPattern = Pattern.compile("mul\\([0-9]+,[0-9]+\\)|do\\(\\)|don't\\(\\)");
    mulMatcher = mulPattern.matcher(fullInput);
    matched = mulMatcher.results();

    int part2 = matched.mapToInt(x -> getMulValue(x.group())).sum();

    pw.println("Part 2 Answer: " + part2);

    br.close();
    pw.close();
  }

  public static int getMulValue(String matched) {
    // System.out.println("Current matched value is: " + matched);
    if (matched.equals("don't()")) {
      // System.out.println("Disabling");
      Day03.enabled = false;
      return 0;
    }

    else if (matched.equals("do()")) {
      // System.out.println("Enabling");
      Day03.enabled = true;
      return 0;
    }

    else if (Day03.enabled) {
      // System.out.println("Enabled and getting value");
      String[] tmp = matched.split("\\(");
      String[] split = tmp[1].split(",");
      
      int fst = Integer.parseInt(split[0]);
      int snd = Integer.parseInt(split[1].split("\\)")[0]);

      // System.out.println("Full match: " +  matched);
      // System.out.println("First Value: " + fst + ", Second Value: " + snd);

      return fst * snd;
    }

    // Currently disabled
    return 0;
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

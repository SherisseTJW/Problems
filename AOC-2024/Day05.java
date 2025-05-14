
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;  // Bad practice but just import everything for the purposes of PE

//NOTE: Change class name 
public class Day05 {
  public static void main(String[] args) throws IOException {
    // Note: Change input file name
    FileReader in = new FileReader("./Inputs/Day05.txt");
    BufferedReader br = new BufferedReader(in);
    PrintWriter pw = new PrintWriter(System.out);

    HashMap<Integer, ArrayList<Integer>> rules = new HashMap<>();
    ArrayList<List<String>> pages = new ArrayList<>();

    String line;

    // Get rules
    while (!(line = br.readLine()).equals("")) {
     // NOTE: Copy and Uncomment the one(s) that are required
     // int input = Integer.parseInt(line);
     // String[] input = line.split(" ");
     // int N = Integer.parseInt(input[0]);

      String[] cur = line.split("\\|");

      int key = Integer.parseInt(cur[0]);
      
      ArrayList<Integer> curRules = rules.getOrDefault(key, new ArrayList<>());
      curRules.add(Integer.parseInt(cur[1]));

      rules.put(key, curRules);
    }

    // Get page orders
    while ((line = br.readLine()) != null) {
     // NOTE: Copy and Uncomment the one(s) that are required
     // int input = Integer.parseInt(line);
     // String[] input = line.split(" ");
     // int N = Integer.parseInt(input[0]);

      pages.add(Arrays.asList(line.split(",")));
    }

    //
    // Part 1
    //
    int part1 = 0;

    // For Part 2
    ArrayList<List<String>> incorrectPages = new ArrayList<>();

    for (int i = 0; i < pages.size(); i++) {
      List<String> curPage = pages.get(i);

      if (isValid(curPage, rules)) {
        int midIdx = (int) Math.ceil(curPage.size() / 2);
        part1 += Integer.parseInt(curPage.get(midIdx));
      }
      else {
        incorrectPages.add(curPage);
      }
    }

    pw.println("Part 1 Answer: " + part1);
    //
    // Part 2
    //
    int part2 = 0;

    for (int i = 0; i < incorrectPages.size(); i++) {
      List<String> curPage = incorrectPages.get(i);
      // pw.println(curPage + " is incorrect! Attempting to fix");

      fixPage(curPage, rules);

      // pw.println("Fixed page for is " + curPage);

      int midIdx = (int) Math.ceil(curPage.size() / 2);
      part2 += Integer.parseInt(curPage.get(midIdx));
    }

    pw.println("Part 2 Answer: " + part2);

    br.close();
    pw.close();
  }

  public static Boolean isValid(List<String> curPage, HashMap<Integer, ArrayList<Integer>> rules) {
    Integer key = Integer.parseInt(curPage.get(0));
    if (!rules.containsKey(key)) {
      return false;
    }

    ArrayList<Integer> validNext = rules.get(key);

    for (int i = 1; i < curPage.size(); i++) {
      Integer nxt = Integer.parseInt(curPage.get(i));

      if (!validNext.contains(nxt)) {
        return false;
      }

      validNext = rules.getOrDefault(nxt, new ArrayList<>());
    }

    return true;
  }

  public static void fixPage(List<String> curPage, HashMap<Integer, ArrayList<Integer>> rules) {

    System.out.println("Attempting to fix page: " + curPage);

    for (int i = 0; i < curPage.size(); i++) {
      for (int j = 0; j < curPage.size() - 1; j++) {
        String cur = curPage.get(j);
        String nxt = curPage.get(j+1);

        ArrayList<Integer> validNext = rules.getOrDefault(Integer.parseInt(cur), new ArrayList<>());

        if (!validNext.contains(Integer.parseInt(nxt))) {
          curPage.set(j, nxt);
          curPage.set(j+1, cur);
        }
      }
    }

    System.out.println("Fixed page is: " + curPage);
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

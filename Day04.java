import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;  // Bad practice but just import everything for the purposes of PE

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.MatchResult;
import java.util.stream.Stream;

//NOTE: Change class name 
public class Day04 {
  public static void main(String[] args) throws IOException {
    // Note: Change input file name
    FileReader in = new FileReader("./Inputs/Day04.txt");
    BufferedReader br = new BufferedReader(in);
    PrintWriter pw = new PrintWriter(System.out);

    List<List<String>> grid = new ArrayList<>();

    String line;
    while ((line = br.readLine()) != null) {
    //  // NOTE: Copy and Uncomment the one(s) that are required
    //  // int input = Integer.parseInt(line);
    //  // String[] input = line.split(" ");
    //  // int N = Integer.parseInt(input[0]);
      grid.add(Arrays.asList(line.split("(?!^)")));
    }
    //
    // Part 1
    //


    Pattern forwards = Pattern.compile("XMAS");
    Pattern backwards = Pattern.compile("SAMX");

    
    int count = 0;
    int height = grid.size();
    int width = grid.get(0).size(); // All lines have the same width

    for (int i = 0; i < grid.size(); i++) {
      for (int j = 0; j < width; j++) {

        if (grid.get(i).get(j).equals("X")) {
          count += Day04.countXMAS(grid, i, j, height, width);
        }

      }

      String curLine = String.join("", grid.get(i));
      Matcher forwardsMatch = forwards.matcher(curLine);
      Matcher backwardsMatch = backwards.matcher(curLine);

      // long numForwards = forwardsMatch.results().count();
      // long numBackwards = backwardsMatch.results().count();

      count += forwardsMatch.results().count();
      count += backwardsMatch.results().count();

      // System.out.println("Forwards Count for X at line: " + i + " is: " + numForwards);
      // System.out.println("Backwards Count for X at line: " + i + " is: " + numBackwards);
    }
    
    pw.println("Part 1 Answer: " + count);
    //
    // Part 2
    //
    // Ignore all corner row and columns
    count = 0;
    for (int i = 1; i < height - 1; i++) {
      for (int j = 1; j < width - 1; j++) {
        if (grid.get(i).get(j).equals("A") && Day04.isMAS_X(grid, i, j)) {
          count++;
        }
      }
    }
    pw.println("Part 2 Answer: " + count);

    br.close();
    pw.close();
  }

  public static Boolean isMAS_X(List<List<String>> grid, int j, int i) {
    int curCount = 0;

    String leftUpDiag = grid.get(j-1).get(i-1);
    String rightUpDiag = grid.get(j-1).get(i+1);
    String leftDownDiag = grid.get(j+1).get(i-1);
    String rightDownDiag = grid.get(j+1).get(i+1);

    if (leftUpDiag.equals("M") && rightDownDiag.equals("S")) {
      curCount++;
    }

    if (leftUpDiag.equals("S") && rightDownDiag.equals("M")) {
      curCount++;
    }

    if (leftDownDiag.equals("M") && rightUpDiag.equals("S")) {
      curCount++;
    }

    if (leftDownDiag.equals("S") && rightUpDiag.equals("M")) {
      curCount++;
    }

    return curCount == 2;
  }

  public static int countXMAS(List<List<String>> grid, int j, int i, int height, int width) {
    System.out.println("Checking Diagonal / Vertical Count for X at (" + j + ", " + i + ")");

    int curCount = 0;

    // Upwards is good
    if (j >= 3) {

      if (grid.get(j-1).get(i).equals("M") && grid.get(j-2).get(i).equals("A") && grid.get(j-3).get(i).equals("S")) {
        curCount++;
      }

      // Left diagonal
      if (i >= 3) {

        if (grid.get(j-1).get(i-1).equals("M") && grid.get(j-2).get(i-2).equals("A") && grid.get(j-3).get(i-3).equals("S")) {
          curCount++;
        }

      }

      // right diagonal
      if (i < width - 3) {

        if (grid.get(j-1).get(i+1).equals("M") && grid.get(j-2).get(i+2).equals("A") && grid.get(j-3).get(i+3).equals("S")) {
          curCount++;
        }

      }

    }

    // Downwards is good
    if (j < height - 3) {

      if (grid.get(j+1).get(i).equals("M") && grid.get(j+2).get(i).equals("A") && grid.get(j+3).get(i).equals("S")) {
        curCount++;
      }

      // Left diagonal
      if (i >= 3) {

        if (grid.get(j+1).get(i-1).equals("M") && grid.get(j+2).get(i-2).equals("A") && grid.get(j+3).get(i-3).equals("S")) {
          curCount++;
        }

      }

      // right diagonal
      if (i < width - 3) {

        if (grid.get(j+1).get(i+1).equals("M") && grid.get(j+2).get(i+2).equals("A") && grid.get(j+3).get(i+3).equals("S")) {
          curCount++;
        }

      }
    }

    System.out.println("Diagonal / Vertical Count for X at (" + j + ", " + i + ") is: " + curCount);

    return curCount;
  }
}


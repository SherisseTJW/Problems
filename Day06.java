import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;  // Bad practice but just import everything for the purposes of PE

//NOTE: Change class name 
public class Day06 {
  public static void main(String[] args) throws IOException {
    // Note: Change input file name
    FileReader in = new FileReader("./Inputs/Day06.txt");
    BufferedReader br = new BufferedReader(in);
    PrintWriter pw = new PrintWriter(System.out);

    ArrayList<List<String>> map = new ArrayList<>();
    Guard guard = new Guard(-1, -1, "");


    String line;
    int y = 0;
    while ((line = br.readLine()) != null) {
     // NOTE: Copy and Uncomment the one(s) that are required
     // int input = Integer.parseInt(line);
     // String[] input = line.split(" ");
     // int N = Integer.parseInt(input[0]);
      List<String> tmp = Arrays.asList(line.split("(?!^)"));
      map.add(tmp);

      int guardIdx = tmp.indexOf("^"); // I checked the input starts with ^
      if (guardIdx >= 0) {
        guard = new Guard(guardIdx, y, "^");
      }

      y++;
    }

    // pw.println(map);
    // pw.println(guardPos);

    //
    // Part 1
    //
    int part1 = 0;
    int height = map.size();
    int width = map.get(0).size();

    Boolean[][] visited = new Boolean[height][width];
    
    for (int i = 0; i < height; i++) {
      Arrays.fill(visited[i], Boolean.FALSE);
    }

    // pw.println(guard);

    while (true) {
      guard = guard.step(map);

      if (!visited[guard.y][guard.x]) {
        visited[guard.y][guard.x] = true;
        part1++;
      }

    // pw.println(guard);

      if (leavingMap(guard, height, width)) {
        break;
      }
    }


    pw.println("Part 1 Answer: " + part1);
    //
    // Part 2
    //
    // pw.println("Part 2 Answer: " + part2);

    br.close();
    pw.close();
  }

  // public static Boolean visited(Guard cur, ArrayList<List<Boolean>> visitedMap) {
  //   return visitedMap.get(cur.y).get(cur.x));
  // }

  public static Boolean leavingMap(Guard cur, int height, int width) {
    if (cur.direction.equals("^") && cur.y <= 0) {
      return true;
    }

    if (cur.direction.equals(">") && cur.x >= width - 1) {
      return true;
    }

    if (cur.direction.equals("v") && cur.y >= height - 1) {
      return true;
    }

    if (cur.direction.equals("<") && cur.x <= 0) {
      return true;
    }

    return false;
  }
}

class Guard {
  int x;
  int y;
  String direction;

  public Guard(int x, int y, String direction) {
    this.x = x;
    this.y = y;
    this.direction = direction;
  }

  public Guard step(ArrayList<List<String>> map) {
    if (this.direction.equals("^")) {
      int newY = this.y - 1;

      if (!map.get(newY).get(this.x).equals("#")) {
        return new Guard(this.x, newY, this.direction);
      }

      int newX = this.x + 1;
      String newDirection = ">";

      return new Guard(newX, this.y, newDirection);
    }

    else if (this.direction.equals(">")) {
      int newX = this.x + 1;

      if (!map.get(this.y).get(newX).equals("#")) {
        return new Guard(newX, this.y, this.direction);
      }

      int newY = this.y + 1;
      String newDirection = "v";
      return new Guard(this.x, newY, newDirection);
    }

    else if (this.direction.equals("v")) {
      int newY = this.y + 1;

      if (!map.get(newY).get(this.x).equals("#")) {
        return new Guard(this.x, newY, this.direction);
      }

      int newX = this.x - 1;
      String newDirection = "<";
      return new Guard(newX, this.y, newDirection);
    }

    if (this.direction.equals("<")) {
      int newX = this.x - 1;

      if (!map.get(this.y).get(newX).equals("#")) {
        return new Guard(newX, this.y, this.direction);
      }

      int newY = this.y - 1;
      String newDirection = "^";
      return new Guard(this.x, newY, newDirection);
    }

    return new Guard(this.x, this.y, this.direction);
  }

  @Override
  public String toString() {
    return "Guard is at (" + this.x + ", " + this.y + "), facing " + this.direction;
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

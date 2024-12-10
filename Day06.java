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
    Guard originalPos = new Guard(-1, -1, "");

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
        originalPos = new Guard(guardIdx, y, "^");
      }

      y++;
    }


    // pw.println(map);
    // pw.println(guardPos);

    //
    // Part 1
    //
    int height = map.size();
    int width = map.get(0).size();

    Boolean[][] visited = new Boolean[height][width];
    
    for (int i = 0; i < height; i++) {
      Arrays.fill(visited[i], Boolean.FALSE);
    }

    ArrayList<Coordinates> originalPath = new ArrayList<>();

    // pw.println(guard);

    Guard guard = new Guard(originalPos.x, originalPos.y, "^");

    while (true) {
      guard = guard.step(map);

      if (!visited[guard.y][guard.x]) {
        visited[guard.y][guard.x] = true;

        originalPath.add(new Coordinates(guard.x, guard.y));
      }

    // pw.println(guard);

      if (leavingMap(guard, height, width)) {
        break;
      }
    }

    pw.println("Part 1 Answer: " + originalPath.size());
    //
    // Part 2
    //
    int part2 = 0;

    for (int i = 0; i < originalPath.size(); i++) {
      HashMap<Coordinates, ArrayList<String>> visitedDirectionMap = new HashMap<>(); // Store the coordinates, then the direction that we hit that coordinate with previously

      Coordinates cur = originalPath.get(i);
      ArrayList<List<String>> tmpMap = new ArrayList<>(map);
      tmpMap.get(cur.y).set(cur.x, "#");

      Guard simGuard = new Guard(originalPos.x, originalPos.y, "^");

      pw.println("Checking Coordinates: " + cur);


      // Check for current obstacle placement until cycle or left map
      while (true) {
        simGuard = simGuard.step(tmpMap);

        Coordinates curCoords = new Coordinates(simGuard.x, simGuard.y);

        // Cycle detection
        // If we hit the same coordinates with the same direction, we are in a cycle
        if (visitedDirectionMap.containsKey(curCoords)) {
          ArrayList<String> directions = visitedDirectionMap.get(curCoords);

          if (directions.contains(simGuard.direction)) {
            pw.println("Possible position found at: " + cur);
            part2++;
            break;
          }
          else {
            directions.add(simGuard.direction);
            visitedDirectionMap.put(curCoords, directions);

          }
        }
        else {
          ArrayList<String> init = new ArrayList<>();
          init.add(simGuard.direction);
          visitedDirectionMap.put(curCoords, init);

        }

        if (leavingMap(simGuard, height, width)) {
          break;
        }
      }
    }

    pw.println("Part 2 Answer: " + part2);

    br.close();
    pw.close();
  }

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

class Coordinates {
  int x;
  int y;

  public Coordinates(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof Coordinates) {
      Coordinates other = (Coordinates) object;
      if (other.x == this.x && other.y == this.y) {
        return true;
      }
    }

    return false;
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
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

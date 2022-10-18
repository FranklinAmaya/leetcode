import java.util.*;

//https://leetcode.com/problems/rotting-oranges/
public class RottingOranges {
  public int orangesRotting(int[][] grid) {
    int fresh = 0;
    Queue<int[]> rotten = new LinkedList<>();

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 1) {
          fresh++;
        } else if (grid[i][j] == 2) {
          rotten.offer(new int[] { i, j });
        }
      }
    }

    int minutes = 0;
    int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    while (!rotten.isEmpty()) {
      int size = rotten.size();

      for (int i = 0; i < size; i++) {
        int[] curr = rotten.poll();

        for (int[] direction : directions) {
          int x = curr[0] + direction[0];
          int y = curr[1] + direction[1];

          if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] != 1) {
            continue;
          }

          grid[x][y] = 2;
          fresh--;
          rotten.offer(new int[] { x, y });
        }
      }

      minutes++;
    }

    return fresh == 0 ? Math.max(minutes - 1, 0) : -1;
  }
}

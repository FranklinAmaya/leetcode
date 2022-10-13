import java.util.*;

//https://leetcode.com/problems/shortest-path-in-binary-matrix/
public class ShortestPathMatrix {
  private static final int[][] DIRECTIONS = {
      { -1, -1 },
      { 0, -1 },
      { 1, -1 },
      { 1, 0 },
      { 1, 1 },
      { 0, 1 },
      { -1, 1 },
      { -1, 0 },
  };

  public int shortestPathBinaryMatrix(int[][] grid) {
    if (grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) {
      return -1;
    }

    // storing coordinates in here
    boolean[][] visited = new boolean[grid.length][grid[0].length];
    Queue<int[]> q = new LinkedList<>();
    q.offer(new int[] { 0, 0, 1 });
    visited[0][0] = true;

    while (!q.isEmpty()) {
      int[] curr = q.poll();
      int x = curr[0];
      int y = curr[1];
      int d = curr[2];

      // check if we're at the end of the array
      if (x == grid.length - 1 && y == grid[0].length - 1 && grid[x][y] == 0) {
        return d;
      }

      // traverse the neighbors
      for (int[] direction : DIRECTIONS) {
        int posX = x + direction[0];
        int posY = y + direction[1];
        int updatedDistance = d + 1;

        // make sure you're inbounds
        if (posX < 0 || posX >= grid.length || posY < 0 || posY >= grid[0].length || grid[posX][posY] != 0) {
          continue;
        }

        // add neighbors on the q
        if (!visited[posX][posY]) {
          q.offer(new int[] { posX, posY, updatedDistance });
          visited[posX][posY] = true;
        }
      }
    }

    // never encountered end of the array
    return -1;
  }
}

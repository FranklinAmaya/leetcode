class LCS {
  public int longestCommonSubsequence(String text1, String text2) {
    int T1 = text1.length(), T2 = text2.length();
    int[][] dp = new int[T1 + 1][T2 + 1];

    for (int row = T1 - 1; row >= 0; row--) {
      for (int col = T2 - 1; col >= 0; col--) {
        if (text1.charAt(row) == text2.charAt(col)) {
          dp[row][col] = 1 + dp[row + 1][col + 1];
        } else {
          dp[row][col] = Math.max(dp[row + 1][col], dp[row][col + 1]);
        }
      }
    }

    return dp[0][0];
  }
}
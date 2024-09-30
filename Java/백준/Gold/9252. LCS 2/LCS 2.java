import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String a = in.readLine();
        String b = in.readLine();

        int lenA = a.length();
        int lenB = b.length();
        int[][] dp = new int[lenA+1][lenB+1];
        StringBuilder lcs = new StringBuilder();

        for (int i = 1; i <= lenA; i++) {
            for (int j = 1; j <= lenB; j++) {
                if (a.charAt(i-1) == b.charAt(j-1)) dp[i][j] = dp[i-1][j-1]+1;
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        System.out.println(dp[lenA][lenB]);

        while (lenA > 0 && lenB > 0) {
            if (dp[lenA][lenB] == dp[lenA-1][lenB]) lenA--;
            else if (dp[lenA][lenB] == dp[lenA][lenB-1]) lenB--;
            else {
                lcs.append(a.charAt(lenA-1));
                lenA--;
                lenB--;
            }
        }
        System.out.println(lcs.reverse());
    }
}
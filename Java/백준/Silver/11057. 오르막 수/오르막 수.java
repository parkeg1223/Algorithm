import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());

        int[][] dp = new int[N+1][10];
        Arrays.fill(dp[1], 1);
        for (int i = 2; i <= N; i++) {
            int curr = 0;
            for (int j = 0; j <= 9; j++) {
                curr += dp[i-1][j] % 10007;
                dp[i][j] = curr % 10007;
            }
        }

        int sum = 0;
        for (int i = 0; i <= 9; i++) {
            sum += dp[N][i];
        }
        System.out.println(sum % 10007);
    }
}
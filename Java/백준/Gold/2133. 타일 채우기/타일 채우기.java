import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] dp = new int[N+1];
        dp[0] = 1;
        if (N >= 2) dp[2] = 3;
        for (int i = 4; i <= N; i++) {
            if (i % 2 != 0) continue;
            dp[i] = dp[i-2] * 4 - dp[i-4];
        }
        System.out.println(dp[N]);
    }
}
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        boolean[] dp = new boolean[N+1];
        dp[1] = true;
        if (N >= 2) dp[2] = false;
        if (N >= 3) dp[3] = true;
        if (N >= 4) dp[4] = true;
        for (int i = 5; i <= N; i++) {
            dp[i] = !(dp[i-1] && dp[i-3] && dp[i-4]);
        }
        System.out.println(dp[N] ? "SK" : "CY");
    }
}
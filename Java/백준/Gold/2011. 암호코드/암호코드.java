import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = in.readLine();
        int[] dp = new int[s.length() + 1];

        dp[0] = 1;
        dp[1] = 1;
        char prev = s.charAt(0), curr;
        if (prev == '0') {
            System.out.println(0);
            return;
        }
        for (int i = 2; i <= s.length(); i++) {
            curr = s.charAt(i-1);
            if (curr == '0') {
                if (prev == '0' || prev - '0' > 2) {
                    System.out.println(0);
                    return;
                } else {
                    dp[i] = dp[i-2];
                }
            } else if (prev >= '3') {
                dp[i] = dp[i-1];
            } else if (prev == '1') {
                dp[i] = (dp[i-1] + dp[i-2]) % 1000000;
            } else if (prev == '2') {
                if (curr - '0' > 6) {
                    dp[i] = dp[i-1];
                } else {
                    dp[i] = (dp[i-1] + dp[i-2]) % 1000000;
                }
            } else {
                dp[i] = dp[i-1];
            }
            prev = curr;
        }

        System.out.println(dp[s.length()]);
    }
}
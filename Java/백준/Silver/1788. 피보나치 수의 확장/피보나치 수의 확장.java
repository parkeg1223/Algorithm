import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int size = N < 0 ? N*(-1) : N;

        int[] dp = new int[size+1];
        if (N != 0) dp[1] = 1;
        for (int i = 2; i <= size; i++) {
            dp[i] = (dp[i-2] + dp[i-1]) % 1_000_000_000;
        }

        if (N < 0 && size % 2 == 0) System.out.println(-1);
        else if (N == 0) System.out.println(0);
        else System.out.println(1);
        System.out.println(dp[size]);
    }
}
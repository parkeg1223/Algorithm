import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] dp, curr;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(in.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(in.readLine());
            st = new StringTokenizer(in.readLine());
            curr = new int[N];
            for (int i = 0; i < N; i++) {
                curr[i] = Integer.parseInt(st.nextToken());
            }
            M = Integer.parseInt(in.readLine());
            dp = new int[M+1];
            dp[0] = 1;

            for (int i = 0; i < N; i++) {
                for (int j = curr[i]; j <= M; j++) {
                    dp[j] += dp[j-curr[i]];
                }
            }
            sb.append(dp[M]).append("\n");
        }
        System.out.print(sb);
    }
}
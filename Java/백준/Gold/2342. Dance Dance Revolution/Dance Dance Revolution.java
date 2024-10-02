import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        List<Integer> step = new ArrayList<>();

        int N;
        step.add(0);
        while ((N = Integer.parseInt(st.nextToken())) != 0) step.add(N);
        N = step.size()-1;
        int[][][] dp = new int[N+1][5][5];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= 4; j++) {
                Arrays.fill(dp[i][j], 1_000_000);
            }
        }
        dp[0][0][0] = 0;
        if (N >= 1) dp[1][0][step.get(1)] = dp[1][step.get(1)][0] = 2;
        for (int i = 2; i <= N; i++) {
            int val = step.get(i);
            for (int j = 0; j <= 4; j++) {
                for (int k = 0; k <= 4; k++) {
                    if (j == val || k == val) {
                        dp[i][j][k] = Math.min(dp[i][j][k], dp[i-1][j][k] + 1);
                    } else {
                        // j 발을 옮길지, k 발을 옮길지
                        dp[i][j][val] = Math.min(dp[i][j][val], dp[i-1][j][k] + getCost(k, val));
                        dp[i][val][k] = Math.min(dp[i][val][k], dp[i-1][j][k] + getCost(j, val));
                    }
                }
            }
        }

        int minStep = Integer.MAX_VALUE;
        int finalStep = step.get(N);
        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
                if (i == j) continue;
                if (i == finalStep || j == finalStep) {
                    minStep = Math.min(minStep, dp[N][i][j]);
                }
            }
        }

        System.out.println(minStep >= 1_000_000 ? 0 : minStep);
    }

    public static int getCost(int prev, int next) {
        if (prev == 0) return 2;
        else if ((next - prev + 4) % 4 == 2) return 4;
        else return 3;
    }
}
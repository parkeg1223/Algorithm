import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(in.readLine());

        while (T-- > 0) {
            int n = Integer.parseInt(in.readLine());

            int[][] dp = new int[2][n+1];
            int[][] stickers = new int[2][n+1];
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(in.readLine());
                for (int j = 1; j <= n; j++) {
                    stickers[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp[0][1] = stickers[0][1];
            dp[1][1] = stickers[1][1];
            if (n >= 2) {
                dp[0][2] = stickers[1][1] + stickers[0][2];
                dp[1][2] = stickers[0][1] + stickers[1][2];
            }

            for (int i = 3; i <= n; i++) {
                // i 열에서 1행 선택 시 최대 점수 = i 열 1행 스티커 + (i-1 열에서 2행 스티커, i-2 열에서 2행 스티커) 선택했을 때의 최댓값 중 최대
                // i 열에서 2행 선택 시 최대 점수 = i 열 2행 스티커 + (i-1 열에서 1행 스티커, i-2 열에서 1행 스티커) 선택했을 때의 최댓값 중 최대
                dp[0][i] = stickers[0][i] + Math.max(dp[1][i-1], dp[1][i-2]);
                dp[1][i] = stickers[1][i] + Math.max(dp[0][i-1], dp[0][i-2]);
            }

            sb.append(Math.max(dp[0][n], dp[1][n])).append("\n");
        }
        System.out.print(sb);
    }
}
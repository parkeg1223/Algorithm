import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());

        int[] wine = new int[n+1];
        int[][] ans = new int[n+1][3];
        for (int i = 1; i <= n; i++) {
            wine[i] = Integer.parseInt(in.readLine());
        }

        ans[1][2] = 0;
        ans[1][1] = wine[1];
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 1; j++) {
                ans[i][j] = Math.max(ans[i-1][j], ans[i-1][j+1] + wine[i]);
            }
            ans[i][2] = Math.max(Math.max(ans[i-1][0], ans[i-1][1]), ans[i-1][2]);
        }
        System.out.println(Math.max(Math.max(ans[n][0], ans[n][1]), ans[n][2]));
    }
}
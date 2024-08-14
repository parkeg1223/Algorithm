import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[] num;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        num = new int[M];
        dfs(1, 0);
        System.out.print(sb);
    }

    public static void dfs(int n, int k) {
        if (k == M) {
            for (int nu: num) {
                sb.append(nu).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = n; i <= N; i++) {
            num[k] = i;
            dfs(i+1, k+1);
        }
    }
}
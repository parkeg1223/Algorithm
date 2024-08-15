import java.util.*;
import java.io.*;

public class Main {

    static int K;
    static int[] num, arr;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(in.readLine());
            K = Integer.parseInt(st.nextToken());
            if (K == 0) {
                System.out.print(sb);
                return;
            }
            num = new int[K];
            arr = new int[6];
//            visited = new boolean[k];

            for (int i = 0; i < K; i++) {
                num[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0,0);
            sb.append("\n");
        }
    }

    public static void dfs(int n, int k) {
        if (k == 6) {
            for (int nu: arr) {
                sb.append(nu).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = n; i < K; i++) {
            arr[k] = num[i];
            dfs(i+1, k+1);
        }
    }
}
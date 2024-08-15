import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[] num, arr;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        num = new int[M];
        arr = new int[N];
        visited = new boolean[N];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        dfs(0);
        System.out.print(sb);
    }

    public static void dfs(int k) {
        if (k == M) {
            for (int nu: num) {
                sb.append(nu).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            num[k] = arr[i];
            dfs(k+1);
        }
    }
}
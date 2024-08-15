import java.util.*;
import java.io.*;

public class Main {

    static int N, M, idx;
    static int[] num, arr, visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        num = new int[M];
        arr = new int[N];
        visited = new int[10001];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if (visited[n] == 0) {
                arr[idx++] = n;
            }
            visited[n]++;
        }

        arr = Arrays.copyOf(arr, idx);
        Arrays.sort(arr);
        dfs(0, 0);
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

        for (int i = n; i < idx; i++) {
            if (visited[arr[i]] > 0) {
                visited[arr[i]]--;
                num[k] = arr[i];
                dfs(i, k+1);
                visited[arr[i]]++;
            }
        }
    }
}
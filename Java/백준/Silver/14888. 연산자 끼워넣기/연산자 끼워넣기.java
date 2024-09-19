import java.io.*;
import java.util.*;

public class Main {
    static int N, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    static int[] nArr, visited, op;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        nArr = new int[N];
        visited = new int[4];
        op = new int[N-1];

        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            nArr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < 4; i++) {
            visited[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0);

        System.out.println(max + "\n" + min);
    }

    public static void dfs(int step) {
        if (step == N-1) {
            int answer = nArr[0];
            for (int i = 0; i < N-1; i++) {
                if (op[i] == 0) answer += nArr[i+1];
                else if (op[i] == 1) answer -= nArr[i+1];
                else if (op[i] == 2) answer *= nArr[i+1];
                else if (op[i] == 3) answer /= nArr[i+1];
            }
            max = Math.max(max, answer);
            min = Math.min(min, answer);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (visited[i] > 0) {
                visited[i]--;
                op[step] = i;
                dfs(step+1);
                visited[i]++;
            }
        }
    }
}
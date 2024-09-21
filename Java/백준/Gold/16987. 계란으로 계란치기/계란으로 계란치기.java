import java.io.*;
import java.util.*;

public class Main {
    static int N, answer;
    static int[] dur, wei;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        dur = new int[N];
        wei = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            dur[i] = Integer.parseInt(st.nextToken());
            wei[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);
        System.out.println(answer);
    }

    public static void dfs(int col, int broken) {
        if (col == N) {
            answer = Math.max(answer, broken);
            return;
        }

        boolean isBroken = false;
        if (dur[col] <= 0) {
            dfs(col+1, broken);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (i == col || dur[i] <= 0) continue;
            int nEgg = broken;
            isBroken = true;

            dur[col] -= wei[i];
            if (dur[col] <= 0) nEgg++;
            dur[i] -= wei[col];
            if (dur[i] <= 0) nEgg++;

            dfs(col+1, nEgg);

            dur[col] += wei[i];
            dur[i] += wei[col];
        }

        if (!isBroken) {
            answer = Math.max(answer, broken);
        }
    }
}
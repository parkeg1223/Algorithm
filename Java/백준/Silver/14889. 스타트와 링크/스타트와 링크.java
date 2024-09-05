import java.io.*;
import java.util.*;

public class Main {

    static int N, minDiff = Integer.MAX_VALUE;
    static int[][] ability;
    static boolean[] selected;

    public static void main(String[] args) throws IOException {
        input();
        dfs(0, 0);
        System.out.println(minDiff);
    }

    public static void dfs(int start, int step) {
        if (step == N/2) {
            List<Integer> s = new ArrayList<>();
            List<Integer> l = new ArrayList<>();
            int sAb = 0, lAb = 0;
            for (int i = 0; i < N; i++) {
                if (selected[i]) s.add(i);
                else l.add(i);
            }

            for (int i = 0; i < N/2; i++) {
                for (int j = 0; j < N/2; j++) {
                    if (i == j) continue;
                    sAb += ability[s.get(i)][s.get(j)];
                    lAb += ability[l.get(i)][l.get(j)];
                }
            }
            minDiff = Math.min(minDiff, Math.abs(sAb - lAb));
        }

        for (int i = start; i < N; i++) {
            selected[i] = true;
            dfs(i+1, step+1);
            selected[i] = false;
        }
    }

    public static void input() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        ability = new int[N][N];
        selected = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                ability[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
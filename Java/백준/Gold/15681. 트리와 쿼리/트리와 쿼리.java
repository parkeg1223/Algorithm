import java.util.*;
import java.io.*;

public class Main {

    static int N, R, Q;
    static int[] parent, nSubtree;
    static List<Integer>[] adj;
    static List<Integer>[] child;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();;
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N+1];
        child = new ArrayList[N+1];
        parent = new int[N+1];
        nSubtree = new int[N+1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
            child[i] = new ArrayList<>();
        }

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(in.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        makeTree(R, -1);
        countSubtreeNodes(R);
        for (int i = 0; i < Q; i++) {
            int u = Integer.parseInt(in.readLine());
            sb.append(nSubtree[u]).append("\n");
        }
        System.out.print(sb);
    }

    public static void makeTree(int curr, int p) {
        for (int v: adj[curr]) {
            if (v != p) {
                child[curr].add(v);
                parent[v] = curr;
                makeTree(v, curr);
            }
        }
    }

    public static void countSubtreeNodes(int curr) {
        nSubtree[curr] = 1;
        for (int v: child[curr]) {
            countSubtreeNodes(v);
            nSubtree[curr] += nSubtree[v];
        }
    }
}
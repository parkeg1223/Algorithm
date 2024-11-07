import java.io.*;
import java.util.*;

public class Main {

    static int N, root, deleted, nLeaf;
    static boolean[] visited;
    static int[] parent;
    static List<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());

        adj = new ArrayList[N];
        visited = new boolean[N];
        parent = new int[N];

        for (int i = 0; i < N; i++){
            adj[i] = new ArrayList<>();
        }

        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            int p = Integer.parseInt(st.nextToken());
            parent[i] = p;
            if (p != -1) adj[p].add(i);
            else root = i;
        }

        deleted = Integer.parseInt(in.readLine());

        visited[deleted] = true;
        dfs(deleted);
        if (deleted != root) adj[parent[deleted]].remove(Integer.valueOf(deleted));

        for (int i = 0; i < N; i++) {
            if (!visited[i] && adj[i].isEmpty()) nLeaf++;
        }

        System.out.println(nLeaf);
    }

    public static void dfs(int n) {
        visited[n] = true;
        for (int v: adj[n]) {
            if (!visited[v]) {
                dfs(v);
            }
        }
    }
}
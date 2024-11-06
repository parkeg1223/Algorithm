import java.io.*;
import java.util.*;

public class Main {

    static int N, move, nVisited;
    static boolean[] visited;
    static Node[] adj;

    public static class Node {
        int left, right;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        nVisited = N;
        adj = new Node[N+1];
        visited = new boolean[N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj[a] = new Node(b, c);
        }

        dfs(1);
        System.out.print(move);
    }

    public static void dfs(int n) {
        if (adj[n].left != -1 && !visited[adj[n].left]) {
            move++;
            dfs(adj[n].left);
            move++;
        }
        visited[n] = true;
        nVisited--;
        if (adj[n].right != -1 && !visited[adj[n].right]) {
            move++;
            dfs(adj[n].right);
            if (nVisited != 0) move++;
        }
    }
}
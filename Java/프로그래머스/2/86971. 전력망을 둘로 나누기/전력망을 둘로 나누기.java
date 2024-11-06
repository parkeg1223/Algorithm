import java.util.*;

class Solution {
    
    List<Integer>[] adj;
    boolean[] visited;
    int excluded = 0, nNode = 0;
    
    public int solution(int n, int[][] wires) {
        int answer = n;
        adj = new ArrayList[n+1];
        visited = new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < n-1; i++) {
            adj[wires[i][0]].add(wires[i][1]);
            adj[wires[i][1]].add(wires[i][0]);
        }
        
        for (int i = 0; i < n-1; i++) {
            adj[wires[i][0]].remove(Integer.valueOf(wires[i][1]));
            adj[wires[i][1]].remove(Integer.valueOf(wires[i][0]));
            dfs(1);
            answer = Math.min(answer, (int)Math.abs(nNode - (n-nNode)));
            nNode = 0;
            adj[wires[i][0]].add(wires[i][1]);
            adj[wires[i][1]].add(wires[i][0]);
            Arrays.fill(visited, false);
        }
        
        return answer;
    }
    
    public void dfs(int curr) {
        nNode++;
        visited[curr] = true;
        
        for (int v: adj[curr]) {
            if (!visited[v]) {
                dfs(v);
            }
        }          
    }
}
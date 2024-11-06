import java.util.*;

class Solution {
    boolean[] visited;
    int[][] d;
    int answer = 0;
    
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        d = new int[dungeons.length][2];
        
        for (int i = 0; i < dungeons.length; i++) {
            d[i] = Arrays.copyOf(dungeons[i], 2);
        }
        
        dfs(k, 0);
        
        return answer == 0 ? -1 : answer;
    }
    
    public void dfs(int k, int step) {
        for (int i = 0; i < d.length; i++) {
            if (!visited[i] && d[i][0] <= k) {
                visited[i] = true;
                answer = Math.max(answer, step+1);
                dfs(k-d[i][1], step+1);
                visited[i] = false;
            }
        }
    }
}
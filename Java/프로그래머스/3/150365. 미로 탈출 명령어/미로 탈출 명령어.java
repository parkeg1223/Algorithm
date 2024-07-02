import java.util.*;

class Solution {
    
    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, -1, 1, 0};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        String direction = "dlru";
        int currX = x, currY = y;
        int dist = Math.abs(r-x) + Math.abs(c-y);
        if (dist > k || dist % 2 != k % 2) return "impossible";
        
        while (k-- > 0) {
            for (int i = 0; i < 4; i++) {
                int nx = currX + dx[i];
                int ny = currY + dy[i];
                if (nx <= 0 || nx > n || ny <= 0 || ny > m) continue;
                
                dist = Math.abs(r-nx) + Math.abs(c-ny);
                if (dist > k) continue;
                
                currX = nx;
                currY = ny;
                answer += direction.charAt(i);
                break;
            }
        }
        
        return answer;
    }
}
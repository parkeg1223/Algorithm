import java.util.*;

class Solution {
    
    public boolean[][] area = new boolean[52][52];
    public int[][] field = new int[52][52];
    public int[] dx = {-1, 0, 1, 0};
    public int[] dy = {0, 1, 0, -1};
    
    public boolean inRange(int x, int y) {
        return x >= 0 && x <= 51 && y >= 0 && y <= 51;
    }
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        makeField(rectangle);
        
        boolean[][] visited = new boolean[51][51];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {characterX, characterY, 0});
        visited[characterX][characterY] = true;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            // System.out.println(Arrays.toString(cur) + " " + field[cur[0]][cur[1]]);
            if (cur[0] == itemX && cur[1] == itemY) {
                return cur[2];
            }
            
            for (int i = 0; i < 4; i++) {
                if ((field[cur[0]][cur[1]] & (1<<i)) == 0) continue;
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(new int[] {nx, ny, cur[2] + 1});
                }
            }
        }
        
        return -1;
    }
    
    public void makeField(int[][] r) {
        for (int k = 0; k < r.length; k++) {
            for (int i = r[k][0]; i < r[k][2]; i++) {
                for (int j = r[k][1]; j < r[k][3]; j++) {
                    area[i][j] = true;
                }
            }
        }
        
        for (int i = 1; i <= 51; i++) {
            for (int j = 1; j <= 51; j++) {
                if (area[i-1][j-1] ^ area[i-1][j]) field[i][j] |= 1;
                if (area[i-1][j] ^ area[i][j]) field[i][j] |= 2;
                if (area[i][j] ^ area[i][j-1]) field[i][j] |= 4;
                if (area[i][j-1] ^ area[i-1][j-1]) field[i][j] |= 8;
            }
        }
    }
}
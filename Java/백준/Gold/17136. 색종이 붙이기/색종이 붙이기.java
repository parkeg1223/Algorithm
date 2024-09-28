import java.io.*;
import java.util.*;

public class Main {

    static boolean[][] field = new boolean[10][10];
    static int[] cp = new int[6];
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n1 = 0;
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < 10; j++) {
                field[i][j] = Integer.parseInt(st.nextToken()) == 1;
                if (field[i][j]) n1++;
            }
        }

        Arrays.fill(cp, 5);
        dfs(0, 0, 0, n1);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    public static void dfs(int x, int y, int nCp, int n1) {
        // 종료 조건
        if (n1 == 0) {
            answer = Math.min(answer, nCp);
            return;
        }

        if (x == 10) return;

        if (!field[x][y]) {
            if (y < 9) dfs(x, y+1, nCp, n1);
            else dfs(x+1, 0, nCp, n1);
        } else {
            // 덮어야 하는 경우
            // 사용할 수 있는 가장 큰 색종이 넓이 구하기
            int maxCp = getMaxCp(x, y);
            for (int i = maxCp; i >= 1; i--) {
                if (cp[i] == 0) continue;
                cp[i]--;
                // 덮은 맵 만들기
                putSticker(x, y, i);
                if (y+i <= 9) dfs(x, y+i, nCp+1, n1-i*i);
                else dfs(x+1, 0, nCp+1, n1-i*i);
                putSticker(x, y, i);
                cp[i]++;
            }
        }
    }

    public static int getMaxCp(int x, int y) {
        int height = 1, length = 0, maxLen = 5;
        for (int i = x; i < Math.min(10, x+5); i++) {
            for (int j = y; j < Math.min(10, y+5); j++) {
                if (!field[i][j]) break;
                length++;
            }
            if (maxLen > length) maxLen = length;
            if (height > maxLen) break;
            height++;
            length = 0;
        }
        return height-1;
    }

    public static void putSticker(int x, int y, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[x+i][y+j] ^= true;
            }
        }
    }
}
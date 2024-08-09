import java.util.*;
import java.io.*;

public class Main {

    static int N, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        int[][] field = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(field, 0);

        System.out.println(answer);
    }

    private static void dfs(int[][] field, int step) {
        if (step == 5) {
            answer = Math.max(answer, calcMaxBlock(field));
            return;
        }

        dfs(swipe(field, 0), step+1);
        dfs(swipe(field, 1), step+1);
        dfs(swipe(field, 2), step+1);
        dfs(swipe(field, 3), step+1);
    }

    private static int[][] swipe(int[][] field, int dir) {
        if (dir == 0) field = rotate(pushBlocks(rotate(field, 90)), 270);
        else if (dir == 1) field = pushBlocks(field);
        else if (dir == 2) field = rotate(pushBlocks(rotate(field, 180)), 180);
        else if (dir == 3) field = rotate(pushBlocks(rotate(field, 270)), 90);

        return field;
    }

    private static int[][] pushBlocks(int[][] field) {
        int[][] newField = new int[N][N];

        // 우측으로 밀기
        for (int i = 0; i < N; i++) {
            int currVal = 0, currIdx = N-1;
            for (int j = N-1; j >= 0; j--) {
                if (field[i][j] != 0) {
                    if (currVal == 0) {
                        currVal = field[i][j];
                        newField[i][currIdx--] = field[i][j];
                    } else if (currVal != field[i][j]){
                        currVal = field[i][j];
                        newField[i][currIdx--] = field[i][j];
                    } else {
                        currVal = 0;
                        newField[i][currIdx+1] *= 2;
                    }
                }
            }
        }
        return newField;
    }

    private static int[][] rotate(int[][] field, int angle) {
        int[][] newField = new int[N][N];

        if (angle == 90) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    newField[i][j] = field[N-1-j][i];
                }
            }
        } else if (angle == 180) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    newField[i][j] = field[N-1-i][N-1-j];
                }
            }
        } else if (angle == 270) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    newField[i][j] = field[j][N-1-i];
                }
            }
        }

        return newField;
    }

    private static int calcMaxBlock(int[][] field) {
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (field[i][j] != 0 && field[i][j] > max) max = field[i][j];
            }
        }
        return max;
    }
}
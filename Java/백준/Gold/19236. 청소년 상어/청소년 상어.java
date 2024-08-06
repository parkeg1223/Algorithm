import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < 4 && y >= 0 && y < 4;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] field = new int[4][4];
        int[][] fish = new int[17][2];
        int[] dir = new int[17];
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < 4; j++) {
                int n = Integer.parseInt(st.nextToken());
                field[i][j] = n;
                fish[n][0] = i;
                fish[n][1] = j;
                dir[n] = Integer.parseInt(st.nextToken()) - 1;
            }
        }

        int score = field[0][0];
        dir[0] = dir[field[0][0]];
        dir[field[0][0]] = -1;
        field[0][0] = 0;

        System.out.println(simulate(field, fish, dir, score));
    }

    private static int simulate(int[][] field, int[][] fish, int[] dir, int score) {
        // 1. 물고기 이동
        for (int i = 1; i <= 16; i++) {
            int nx, ny;
            for (int j = 0; j < 8; j++) {
                if (dir[i] == -1) continue;
                nx = fish[i][0] + dx[(dir[i] + j) % 8];
                ny = fish[i][1] + dy[(dir[i] + j) % 8];
                if (inRange(nx, ny) && field[nx][ny] != 0) {
                    int swappedFish = field[nx][ny];
                    int tx = fish[i][0];
                    int ty = fish[i][1];
                    dir[i] = (dir[i] + j) % 8;

                    if (swappedFish != -1) {
                        field[tx][ty] = swappedFish;
                        fish[i][0] = fish[swappedFish][0];
                        fish[i][1] = fish[swappedFish][1];

                        field[fish[swappedFish][0]][fish[swappedFish][1]] = i;
                        fish[swappedFish][0] = tx;
                        fish[swappedFish][1] = ty;
                    } else {
                        field[nx][ny] = i;
                        fish[i][0] = nx;
                        fish[i][1] = ny;
                        field[tx][ty] = -1;
                    }
                    break;
                }
            }

        }

        // 2. 상어 이동
        int nx = fish[0][0] + dx[dir[0]];
        int ny = fish[0][1] + dy[dir[0]];
        int answer = score;
        while (inRange(nx, ny)) {

            if (field[nx][ny] == -1) {
                nx += dx[dir[0]];
                ny += dy[dir[0]];
                continue;
            }

            int eatenFish = field[nx][ny];
            int[][] newField = new int[4][4];
            int[][] newFish = new int[17][2];
            int[] newDir;

            for (int i = 0; i < 4; i++) {
                newField[i] = Arrays.copyOf(field[i], field[i].length);
            }

            for (int i = 0; i < 17; i++) {
                newFish[i] = Arrays.copyOf(fish[i], fish[i].length);
            }

            newDir = Arrays.copyOf(dir, dir.length);

            newField[fish[0][0]][fish[0][1]] = -1;
            newFish[0][0] = nx;
            newFish[0][1] = ny;
            newDir[0] = dir[eatenFish];
            newField[nx][ny] = 0;

            newDir[eatenFish] = -1;
            answer = Math.max(answer, simulate(newField, newFish, newDir, score + eatenFish));
            nx += dx[dir[0]];
            ny += dy[dir[0]];
        }

        return answer;
    }
}
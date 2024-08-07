import java.util.*;
import java.io.*;

public class Main {

    static int N, M, k, nShark;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] field;
    static int[][] shark;
    static int[] dir;
    static int[][][] priority;
    static Smell[][] sField;

    public static class Smell {
        int n, ttl;

        Smell(int n, int ttl) {
            this.n = n;
            this.ttl = ttl;
        }
    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    public static void main(String[] args) throws IOException {
        input();

        // 1번 상어만 남을 때까지 simulate()
        int time = 0;
        while (nShark > 1) {
            if (time++ >= 1000) {
                System.out.println(-1);
                return;
            };
            simulate();
        }

        System.out.println(time);
    }


    private static void simulate() {

        // 격자 임시 정보 저장
        PriorityQueue<Integer>[][] tField = new PriorityQueue[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tField[i][j] = new PriorityQueue<>();
            }
        }

        // 상어 이동
        for (int i = 1; i <= M; i++) {
            // 쫓겨난 상어 skip
            if (dir[i] == -1) continue;

            // 다음 이동 방향 결정
            // 주변 4방에 냄새 없는 칸 존재하는지 확인
            int currDir = dir[i];
            boolean isEmptyNearby = false;
            for (int j = 0; j < 4; j++) {
                int nx = shark[i][0] + dx[priority[i][currDir][j]];
                int ny = shark[i][1] + dy[priority[i][currDir][j]];
                if (inRange(nx, ny) && sField[nx][ny] == null) {
                    isEmptyNearby = true;
                    field[shark[i][0]][shark[i][1]] = 0;
                    dir[i] = priority[i][currDir][j];
                    tField[nx][ny].add(i);
                    break;
                }
            }

            // 주변에 빈 칸이 없는 경우
            if (!isEmptyNearby) {
                for (int j = 0; j < 4; j++) {
                    int nx = shark[i][0] + dx[priority[i][currDir][j]];
                    int ny = shark[i][1] + dy[priority[i][currDir][j]];
                    if (inRange(nx, ny) && sField[nx][ny] != null && sField[nx][ny].n == i) {
                        field[shark[i][0]][shark[i][1]] = 0;
                        dir[i] = priority[i][currDir][j];
                        tField[nx][ny].add(i);
                        break;
                    }
                }
            }
        }

        // 냄새 지우기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (sField[i][j] != null) {
                    if (--sField[i][j].ttl == 0) sField[i][j] = null;
                }
            }
        }

        // 이동 완료 시 현재 field/sField 갱신
        // 한 칸에 상어가 한 마리 이상인 경우 번호가 가장 작은 상어를 제외하고 쫓아내기
        // nShark 관리
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tField[i][j].isEmpty()) field[i][j] = 0;
                else {
                    int alive = tField[i][j].poll();
                    field[i][j] = alive;
                    shark[alive][0] = i;
                    shark[alive][1] = j;
                    sField[i][j] = new Smell(field[i][j], k);
                    nShark -= tField[i][j].size();
                    while (!tField[i][j].isEmpty()) {
                        int out = tField[i][j].poll();
                        dir[out] = -1;
                    }
                }
            }
        }
    }

    private static void input() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        nShark = M;
        field = new int[N][N];
        sField = new Smell[N][N];
        shark = new int[M+1][2];
        dir = new int[M+1];
        priority = new int[M+1][4][4];

        // 격자 입력받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
                if (field[i][j] != 0) {
                    shark[field[i][j]][0] = i;
                    shark[field[i][j]][1] = j;
                    sField[i][j] = new Smell(field[i][j], k);
                }
            }
        }

        // 방향 입력받기
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < M; i++) {
            dir[i+1] = Integer.parseInt(st.nextToken()) - 1;
        }

        // 방향 우선순위 입력받기
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(in.readLine());
                for (int k = 0; k < 4; k++) {
                    priority[i+1][j][k] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }
    }
}
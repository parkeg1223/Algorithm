import java.util.*;
import java.io.*;

public class Main {

    static int N, K;
    static int[][] field;
    static List<Player> players;
    static ArrayDeque<Integer>[][] board;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static class Player {
        int x, y, dir;

        Player(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    public static void main(String[] args) throws IOException {
        input();

        int time = 0;
        while (time++ < 1000) {
            if (simulate()) break;
        }

        System.out.println(time > 1000 ? -1 : time);
    }

    public static boolean simulate() {
        for (int i = 0; i < K; i++) {
            ArrayDeque<Integer> q = new ArrayDeque<>();
            Player p = players.get(i);
            int nx = p.x + dx[p.dir];
            int ny = p.y + dy[p.dir];
            if (!inRange(nx, ny) || (inRange(nx, ny) && field[nx][ny] == 2)) {
                // 장외 / 파랑
                p.dir = (p.dir + 2) % 4;
                nx = p.x + dx[p.dir];
                ny = p.y + dy[p.dir];
                if ((inRange(nx, ny)) && field[nx][ny] != 2) {
                    while (!board[p.x][p.y].isEmpty()) {
                        int top = board[p.x][p.y].poll();
                        q.offer(top);
                        players.get(top).x = nx;
                        players.get(top).y = ny;
                        if (top == i) break;
                    }
                    if (field[nx][ny] == 0) {
                        while (!q.isEmpty()) {
                            board[nx][ny].offerFirst(q.pollLast());
                        }
                    } else if (field[nx][ny] == 1) {
                        while (!q.isEmpty()) {
                            board[nx][ny].offerFirst(q.poll());
                        }
                    }
                    p.x = nx; p.y = ny;
                }
            } else if (field[nx][ny] == 1){
                // 빨강
                while (!board[p.x][p.y].isEmpty()) {
                    int top = board[p.x][p.y].poll();
                    q.offer(top);
                    players.get(top).x = nx;
                    players.get(top).y = ny;
                    if (top == i) break;
                }
                while (!q.isEmpty()) {
                    board[nx][ny].offerFirst(q.poll());
                }
                p.x = nx; p.y = ny;
            } else {
                // 빈 칸
                while (!board[p.x][p.y].isEmpty()) {
                    int top = board[p.x][p.y].poll();
                    q.offer(top);
                    players.get(top).x = nx;
                    players.get(top).y = ny;
                    if (top == i) break;
                }
                while (!q.isEmpty()) {
                    board[nx][ny].offerFirst(q.pollLast());
                }
                p.x = nx; p.y = ny;
            }

            if (isEnd()) return true;
        }

        // 4개 이상 쌓인 경우 종료
        return false;
    }

    public static boolean isEnd() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j].size() >= 4) return true;
            }
        }
        return false;
    }

    public static void input() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        field = new int[N][N];
        board = new ArrayDeque[N][N];
        players = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
                board[i][j] = new ArrayDeque<>();
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            if (dir == 2) dir = 3;
            else if (dir == 3) dir = 0;
            else if (dir == 4) dir = 2;
            board[x][y].add(i);
            players.add(new Player(x, y, dir));
        }
    }
}
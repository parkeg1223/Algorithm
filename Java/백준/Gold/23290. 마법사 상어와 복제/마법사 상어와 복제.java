import java.io.*;
import java.util.*;

public class Main {
    static List<Integer>[][] field = new ArrayList[4][4];
    static List<int[]> fInfo = new ArrayList<>();
    static List<int[]> saved = new ArrayList<>();
    static int[][] sInfo = new int[4][4];
    static int[][] visited = new int[4][4];
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] sdx = {-1, 0, 1, 0};
    static int[] sdy = {0, -1, 0, 1};
    static int[] shark = new int[2];
    static int M, S, maxEatenFish;
    static String maxRoute;

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < 4 && y >= 0 && y < 4;
    }

    public static void main(String[] args) throws IOException {
        input();
        while (S-- > 0) {
            save();
            moveFish();
            moveShark();
            duplicate();
        }
        System.out.println(getTotalFishes());
    }

    public static void save() {
        saved.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int size = field[i][j].size();
                for (int k = 0; k < size; k++) {
                    saved.add(fInfo.get(field[i][j].get(k)));
                }
            }
        }
    }

    public static void moveFish() {
        List<Integer>[][] newField = new ArrayList[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newField[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int size = field[i][j].size();
                boolean possible = false;
                for (int k = 0; k < size; k++) {
                    int n = field[i][j].get(k);
                    int[] fish = fInfo.get(n);
                    int x = fish[0];
                    int y = fish[1];
                    int d = fish[2];
                    for (int l = 8; l > 0; l--) {
                        int dir = (d+l)%8;
                        int nx = x + dx[dir];
                        int ny = y + dy[dir];
                        if (!inRange(nx, ny) || (nx == shark[0] && ny == shark[1])
                                || (sInfo[nx][ny] > 0 && sInfo[nx][ny] <= S+2)) continue;
                        possible = true;
                        newField[nx][ny].add(n);
                        fInfo.set(n, new int[] {nx, ny, dir});
                        break;
                    }
                    if (!possible) {
                        for (int l = 0; l < size; l++) {
                            newField[i][j].add(field[i][j].get(l));
                        }
                        break;
                    }
                }
            }
        }

        field = newField;
    }

    public static void moveShark() {
        maxEatenFish = 0;
        maxRoute = "999";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                visited[i][j] = field[i][j].size();
            }
        }

        explore(shark[0], shark[1], 0, "");

        int sx = shark[0], sy = shark[1];
        for (int i = 0; i < 3; i++) {
            int dir = maxRoute.charAt(i) - '0';
            sx += sdx[dir];
            sy += sdy[dir];
            if (field[sx][sy].isEmpty()) continue;
            sInfo[sx][sy] = S;
            field[sx][sy].clear();
        }
        shark[0] = sx; shark[1] = sy;
    }

    public static void explore(int x, int y, int eatenFish, String route) {
        if (route.length() == 3) {
            if (maxEatenFish < eatenFish) {
                maxEatenFish = eatenFish;
                maxRoute = route;
            } else if (maxEatenFish == eatenFish) maxRoute = maxRoute.compareTo(route) > 0 ? route : maxRoute;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + sdx[i];
            int ny = y + sdy[i];
            if (inRange(nx, ny)) {
                int temp = visited[nx][ny];
                visited[nx][ny] = 0;
                explore(nx, ny, eatenFish + temp, route + i);
                visited[nx][ny] = temp;
            }
        }
    }

    public static void duplicate() {
        for (int[] savedFish: saved) {
            field[savedFish[0]][savedFish[1]].add(M++);
            fInfo.add(savedFish);
        }
    }

    public static int getTotalFishes() {
        int nFish = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                nFish += field[i][j].size();
            }
        }
        return nFish;
    }

    public static void input() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                field[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int fx = Integer.parseInt(st.nextToken()) - 1;
            int fy = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;

            field[fx][fy].add(i);
            fInfo.add(new int[] {fx, fy, d});
        }

        st = new StringTokenizer(in.readLine());
        shark[0] = Integer.parseInt(st.nextToken()) - 1;
        shark[1] = Integer.parseInt(st.nextToken()) - 1;
    }
}

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] bead = new int[4];
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] field, gInfo;
    static List<int[]> commands = new ArrayList<>();
    static List<int[]> groups = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        input();
        group();
        for (int[] command : commands) {
            blizzard(command);
            rearrange();
        }
        System.out.println(bead[1] + 2 * bead[2] + 3 * bead[3]);
    }

    private static void printGroup() {
        System.out.println(groups.size());
        for (int[] group: groups) {
            System.out.print(Arrays.toString(group) + ", ");
        }
        System.out.println();
    }

    public static void group() {
        int x = N/2, y = N/2-1, d = 1, dir = 2, val = field[x][y], amount = 1, group = 1;
        if (field[x][y] != 0) gInfo[x][y] = 1;
        while (true) {
            for (int i = 0; i < d; i++) {
                x += dx[dir];
                y += dy[dir];
                if (y == -1 || field[x][y] == 0)  {
                    if (val != 0) groups.add(new int[] {amount, val});
                    return;
                }
                if (field[x][y] != val) {
                    if (val != 0) groups.add(new int[] {amount, val});
                    gInfo[x][y] = ++group;
                    val = field[x][y];
                    amount = 1;
                } else {
                    amount++;
                    gInfo[x][y] = group;
                }
            }

            d++;
            dir = (dir + 3) % 4;

            for (int i = 0; i < d; i++) {
                x += dx[dir];
                y += dy[dir];
                if (y == -1 || field[x][y] == 0) {
                    if (val != 0) groups.add(new int[] {amount, val});
                    return;
                }
                if (field[x][y] != val) {
                    if (val != 0) groups.add(new int[] {amount, val});
                    gInfo[x][y] = ++group;
                    val = field[x][y];
                    amount = 1;
                } else {
                    amount++;
                    gInfo[x][y] = group;
                }
            }
            dir = (dir + 3) % 4;
        }
    }

    public static void blizzard(int[] command) {
        // command = {방향, 거리};
        for (int i = 1; i <= command[1]; i++) {
            int x = N/2 + i*dx[command[0]];
            int y = N/2 + i*dy[command[0]];

            int group = gInfo[x][y];
            if (group == 0) return;
            int[] curr = groups.get(group-1);
            groups.set(group-1, new int[] {curr[0]-1, curr[1]});
        }
    }

    public static void rearrange() {
        boolean exploded;
        do {
            exploded = false;
            int gSize = groups.size();
            for (int i = gSize-1; i >= 0; i--) {
                int[] group = groups.get(i);
                if (group[0] == 0) {
                    groups.remove(i);
                    continue;
                }

                int nBead = group[0];
                for (int j = i-1; j >= 0; j--) {
                    int[] temp = groups.get(j);
                    if (temp[0] != 0) {
                        if (group[1] == temp[1]) {
                            nBead += temp[0];
                            groups.set(j, new int[] {0, temp[1]});
                        } else break;
                    }
                }

                if (nBead >= 4) {
                    exploded = true;
                    bead[group[1]] += nBead;
                    groups.remove(i);
                } else {
                    groups.set(i, new int[] {nBead, group[1]});
                }
            }
        } while (exploded);

        for (int i = 0; i < N; i++) {
            Arrays.fill(gInfo[i], 0);
        }
        if (groups.isEmpty()) return;

        // newGroup에는 그룹내 구슬 개수 + 번호 정보
        List<int[]> newGroups = new ArrayList<>();
        // temp에는 그룹 정보(gInfo를 순서대로)
        List<Integer> temp = new ArrayList<>();


        int group = 1, amount = 1, val = groups.get(0)[0], n;
        for (int i = 1; i < Math.min(2*groups.size(), N*N-1); i++) {
            n = groups.get(i/2)[i%2];
            if (n == val) {
                amount++;
            } else {
                for (int j = 0; j < amount; j++) {
                    temp.add(group);
                }
                newGroups.add(new int[] {amount, val});
                group++;
                val = n;
                amount = 1;
            }
        }

        if (group != 1) {
            for (int i = 0; i < amount; i++) {
                temp.add(group);
            }
            newGroups.add(new int[] {amount, val});
        }

        groups = newGroups;

        int x = N/2, y = N/2-1, d = 1, dir = 2, idx = 0;
        gInfo[x][y] = temp.get(idx++);
        while (true) {
            for (int i = 0; i < d; i++) {
                x += dx[dir];
                y += dy[dir];
                gInfo[x][y] = temp.get(idx++);
                if (idx == temp.size()) return;
            }

            d++;
            dir = (dir + 3) % 4;

            for (int i = 0; i < d; i++) {
                x += dx[dir];
                y += dy[dir];
                gInfo[x][y] = temp.get(idx++);
                if (x == 0 && y == 0 || idx == temp.size()) return;
            }
            dir = (dir + 3) % 4;
        }
    }

    public static void input() throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        field = new int[N][N];
        gInfo = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            if (d == 1) d = 0;
            else if (d == 4) d = 1;
            commands.add(new int[] {d, s});
        }
    }
}
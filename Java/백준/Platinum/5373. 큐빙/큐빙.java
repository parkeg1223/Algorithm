import java.util.*;
import java.io.*;

public class Main {

    /**
     * 가능한 경우의 수 = 6 (U, D, F, B, L, R) * 2(-, +) = 12가지
     * -와 +는 결과적으로 색깔이 변하는 위치가 같음
     * 1. U/D를 돌린다: (U/D) + F, B, L, R이 변함
     * 2. F/B를 돌린다: (F/B) + U, D, L, R이 변함
     * 3. L/R을 돌린다: (L/R) + U, D, F, B가 변함
     *
     *
     *
     */
    static int N;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[] color = {'w', 'y', 'r', 'o', 'g', 'b'};
    static char[][][] field = new char[6][3][3];
    static char[] side = new char[256];

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(in.readLine());
        side['U'] = 0;
        side['D'] = 1;
        side['F'] = 2;
        side['B'] = 3;
        side['L'] = 4;
        side['R'] = 5;

        for (int tc = 1; tc <= T; tc++) {
            initialize();
            int n = Integer.parseInt(in.readLine());
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                String command = st.nextToken();
                rotate(side[command.charAt(0)], command.charAt(1));
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    sb.append(field[0][i][j]);
                }
                sb.append("\n");
            }
        }

        System.out.print(sb);
    }

    private static void initialize() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                Arrays.fill(field[i][j], color[i]);
            }
        }
    }

    private static void rotate(int side, char dir) {
        char[] temp = new char[3];
        if (side == 0) {
            if (dir == '+') {
                for (int i = 0; i < 3; i++) {
                    temp[i] = field[2][0][i];
                    field[2][0][i] = field[5][0][i];
                    field[5][0][i] = field[3][0][i];
                    field[3][0][i] = field[4][0][i];
                    field[4][0][i] = temp[i];
                }
            } else if (dir == '-') {
                for (int i = 0; i < 3; i++) {
                    temp[i] = field[2][0][i];
                    field[2][0][i] = field[4][0][i];
                    field[4][0][i] = field[3][0][i];
                    field[3][0][i] = field[5][0][i];
                    field[5][0][i] = temp[i];
                }
            }
        } else if (side == 1) {
            if (dir == '+') {
                for (int i = 0; i < 3; i++) {
                    temp[i] = field[2][2][i];
                    field[2][2][i] = field[4][2][i];
                    field[4][2][i] = field[3][2][i];
                    field[3][2][i] = field[5][2][i];
                    field[5][2][i] = temp[i];
                }
            } else if (dir == '-') {
                for (int i = 0; i < 3; i++) {
                    temp[i] = field[2][2][i];
                    field[2][2][i] = field[5][2][i];
                    field[5][2][i] = field[3][2][i];
                    field[3][2][i] = field[4][2][i];
                    field[4][2][i] = temp[i];
                }
            }
        } else if (side == 2) {
            if (dir == '+') {
                for (int i = 0; i < 3; i++) {
                    temp[i] = field[0][2][i];
                    field[0][2][i] = field[4][2-i][2];
                    field[4][2-i][2] = field[1][2][i];
                    field[1][2][i] = field[5][i][0];
                    field[5][i][0] = temp[i];
                }
            } else if (dir == '-') {
                for (int i = 0; i < 3; i++) {
                    temp[i] = field[0][2][i];
                    field[0][2][i] = field[5][i][0];
                    field[5][i][0] = field[1][2][i];
                    field[1][2][i] = field[4][2-i][2];
                    field[4][2-i][2] = temp[i];
                }
            }
        } else if (side == 3) {
            if (dir == '+') {
                for (int i = 0; i < 3; i++) {
                    temp[i] = field[0][0][2-i];
                    field[0][0][2-i] = field[5][2-i][2];
                    field[5][2-i][2] = field[1][0][2-i];
                    field[1][0][2-i] = field[4][i][0];
                    field[4][i][0] = temp[i];
                }
            } else if (dir == '-') {
                for (int i = 0; i < 3; i++) {
                    temp[i] = field[0][0][2-i];
                    field[0][0][2-i] = field[4][i][0];
                    field[4][i][0] = field[1][0][2-i];
                    field[1][0][2-i] = field[5][2-i][2];
                    field[5][2-i][2] = temp[i];
                }
            }
        } else if (side == 4) {
            if (dir == '+') {
                for (int i = 0; i < 3; i++) {
                    temp[i] = field[0][i][0];
                    field[0][i][0] = field[3][2-i][2];
                    field[3][2-i][2] = field[1][2-i][2];
                    field[1][2-i][2] = field[2][i][0];
                    field[2][i][0] = temp[i];
                }
            } else if (dir == '-') {
                for (int i = 0; i < 3; i++) {
                    temp[i] = field[0][i][0];
                    field[0][i][0] = field[2][i][0];
                    field[2][i][0] = field[1][2-i][2];
                    field[1][2-i][2] = field[3][2-i][2];
                    field[3][2-i][2] = temp[i];
                }
            }
        } else if (side == 5) {
            if (dir == '+') {
                for (int i = 0; i < 3; i++) {
                    temp[i] = field[0][2-i][2];
                    field[0][2-i][2] = field[2][2-i][2];
                    field[2][2-i][2] = field[1][i][0];
                    field[1][i][0] = field[3][i][0];
                    field[3][i][0] = temp[i];
                }
            } else if (dir == '-') {
                for (int i = 0; i < 3; i++) {
                    temp[i] = field[0][2-i][2];
                    field[0][2-i][2] = field[3][i][0];
                    field[3][i][0] = field[1][i][0];
                    field[1][i][0] = field[2][2-i][2];
                    field[2][2-i][2] = temp[i];
                }
            }
        }

        // 돌리는 면 시뮬레이션
        char[][] newField = new char[3][3];

        if (dir == '+') {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    newField[i][j] = field[side][2-j][i];
                }
            }
        } else if (dir == '-') {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    newField[i][j] = field[side][j][2-i];
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            field[side][i] = Arrays.copyOf(newField[i], 3);
        }
    }
}
import java.util.*;
import java.io.*;

public class Main {

    static int N, M, K;
    static boolean[][] field;
    static List<boolean[][]> stickers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        input();
        for (boolean[][] sticker: stickers) {
            simulate(sticker);
        }
        System.out.println(calculate());
    }

    private static void simulate(boolean[][] sticker) {
        for (int i = 0; i < 4; i++) {
            int r = sticker.length;
            int c = sticker[0].length;
            for (int x = 0; x <= N-r; x++) {
                for (int y = 0; y <= M-c; y++) {
                    if (match(sticker, x, y)) {
                        paste(sticker, x, y);
                        return;
                    }
                }
            }
            sticker = rotate90(sticker);
        }
    }

    private static void paste(boolean[][] sticker, int x, int y) {
        for (int i = x; i < x + sticker.length; i++) {
            for (int j = y; j < y + sticker[0].length; j++) {
                if (!field[i][j]) field[i][j] = sticker[i-x][j-y];
            }
        }
    }

    private static boolean match(boolean[][] sticker, int x, int y) {
        for (int i = x; i < x + sticker.length; i++) {
            for (int j = y; j < y + sticker[0].length; j++) {
                if (sticker[i-x][j-y]
                        && field[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean[][] rotate90(boolean[][] prev) {
        int r = prev.length;
        int c = prev[0].length;

        boolean[][] newField = new boolean[c][r];

        for (int i = r-1; i >= 0; i--) {
            for (int j = 0; j < c; j++) {
                newField[j][r-1-i] = prev[i][j];
            }
        }

        return newField;
    }

    private static int calculate() {
        int n = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (field[i][j]) n++;
            }
        }

        return n;
    }

    private static void input() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        field = new boolean[N][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(in.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            boolean[][] sticker = new boolean[r][c];
            for (int j = 0; j < r; j++) {
                st = new StringTokenizer(in.readLine());
                for (int k = 0; k < c; k++) {
                    sticker[j][k] = (Integer.parseInt(st.nextToken()) == 1);
                }
            }
            stickers.add(sticker);
        }
    }
}
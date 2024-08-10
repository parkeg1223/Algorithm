import java.util.*;
import java.io.*;

public class Main {

    /**
     * 1. 머리 이동한 좌표가 벽 or 자기 자신이면 종료
     * 2. 머리 좌표에 사과 있으면 사과 없애고 이동 종료
     * 3. 사과 없으면 꼬리 비우기
     */

    static int N, M, maxSum;
    static int[][] field;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static List<boolean[][]> tetrominoes = new ArrayList<>();

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public static void main(String[] args) throws IOException {
        input();
        makeTetrominoes();
        match();

        System.out.println(maxSum);
    }

    private static void match() {
        for (boolean[][] tetromino: tetrominoes) {
            int h = tetromino.length;
            int w = tetromino[0].length;

            for (int i = 0; i <= N-h; i++) {
                for (int j = 0; j <= M-w; j++) {
                    int sum = 0;
                    for (int k = 0; k < h; k++) {
                        for (int l = 0; l < w; l++) {
                            if (tetromino[k][l]) {
                                sum += field[i+k][j+l];
                            }
                        }
                    }
                    maxSum = Math.max(maxSum, sum);
                }
            }
        }
    }

    private static void makeTetrominoes() {
        // 모양 1
        tetrominoes.add(new boolean[][]{{true, true, true, true}});
        tetrominoes.add(new boolean[][]{{true}, {true}, {true}, {true}});

        // 모양 2
        tetrominoes.add(new boolean[][]{{true, true}, {true, true}});

        // 모양 3
        tetrominoes.add(new boolean[][]{{true, false}, {true, false}, {true, true}});
        tetrominoes.add(new boolean[][]{{false, true}, {false, true}, {true, true}});
        tetrominoes.add(new boolean[][]{{true, true}, {true, false}, {true, false}});
        tetrominoes.add(new boolean[][]{{true, true}, {false, true}, {false, true}});
        tetrominoes.add(new boolean[][]{{true, true, true}, {true, false, false}});
        tetrominoes.add(new boolean[][]{{true, true, true}, {false, false, true}});
        tetrominoes.add(new boolean[][]{{true, false, false}, {true, true, true}});
        tetrominoes.add(new boolean[][]{{false, false, true}, {true, true, true}});

        // 모양 4
        tetrominoes.add(new boolean[][]{{true, false}, {true, true}, {false, true}});
        tetrominoes.add(new boolean[][]{{false, true}, {true, true}, {true, false}});
        tetrominoes.add(new boolean[][]{{false, true, true}, {true, true, false}});
        tetrominoes.add(new boolean[][]{{true, true, false}, {false, true, true}});

        // 모양 5
        tetrominoes.add(new boolean[][]{{true, true, true}, {false, true, false}});
        tetrominoes.add(new boolean[][]{{false, true, false}, {true, true, true}});
        tetrominoes.add(new boolean[][]{{false, true}, {true, true}, {false, true}});
        tetrominoes.add(new boolean[][]{{true, false}, {true, true}, {true, false}});
    }

    private static void input() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        field = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < M; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
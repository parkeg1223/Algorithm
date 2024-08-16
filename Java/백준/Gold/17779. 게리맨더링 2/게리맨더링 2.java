import java.util.*;
import java.io.*;

public class Main {

    static int N, nSum, maxDiff = Integer.MAX_VALUE;
    static int[][] A;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        A = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                nSum += A[i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 1; k < N-i && k <= j; k++) {
                    for (int l = 1; l < N-i-k && l < N-j; l++) {
                        renewDiff(i, j, k , l);
                    }
                }
            }
        }

        System.out.println(maxDiff);
    }

    public static void renewDiff(int x, int y, int d1, int d2) {
        int min, max, pop = 0;
        boolean[][] _5th = new boolean[N][N];

        int left = y, right = y;
        if (d1 >= d2) {
            for (int i = x; i < x+d2; i++) {
                for (int j = left; j <= right; j++) {
                    _5th[i][j] = true;
                    pop += A[i][j];
                }
                left--; right++;
            }

            if (d1 > d2) {
                for (int i = x+d2; i < x+d1; i++) {
                    for (int j = left; j <= right; j++) {
                        _5th[i][j] = true;
                        pop += A[i][j];
                    }
                    left--; right--;
                }
            }

            for (int i = x+d1; i <= x+d1+d2; i++) {
                for (int j = left; j <= right; j++) {
                    _5th[i][j] = true;
                    pop += A[i][j];
                }
                left++; right--;
            }
        } else {
            for (int i = x; i < x+d1; i++) {
                for (int j = left; j <= right; j++) {
                    _5th[i][j] = true;
                    pop += A[i][j];
                }
                left--; right++;
            }

            for (int i = x+d1; i < x+d2; i++) {
                for (int j = left; j <= right; j++) {
                    _5th[i][j] = true;
                    pop += A[i][j];
                }
                left++; right++;
            }

            for (int i = x+d2; i <= x+d1+d2; i++) {
                for (int j = left; j <= right; j++) {
                    _5th[i][j] = true;
                    pop += A[i][j];
                }
                left++; right--;
            }
        }

        min = max = pop;

        // 1번 선거구
        pop = 0;
        for (int i = 0; i < x+d1; i++) {
            for (int j = 0; j <= y; j++) {
                if (_5th[i][j]) continue;
                pop += A[i][j];
            }
        }
        min = Math.min(min, pop);
        max = Math.max(max, pop);

        // 2번 선거구
        pop = 0;
        for (int i = 0; i <= x+d2; i++) {
            for (int j = y+1; j < N; j++) {
                if (_5th[i][j]) continue;
                pop += A[i][j];
            }
        }

        min = Math.min(min, pop);
        max = Math.max(max, pop);

        // 3번 선거구
        pop = 0;
        for (int i = x+d1; i < N; i++) {
            for (int j = 0; j < y-d1+d2; j++) {
                if (_5th[i][j]) continue;
                pop += A[i][j];
            }
        }
        min = Math.min(min, pop);
        max = Math.max(max, pop);

        // 4번 선거구
        pop = 0;
        for (int i = x+d2+1; i < N; i++) {
            for (int j = y-d1+d2; j < N; j++) {
                if (_5th[i][j]) continue;
                pop += A[i][j];
            }
        }

        min = Math.min(min, pop);
        max = Math.max(max, pop);

        maxDiff = Math.min(maxDiff, max-min);
    }
}
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(in.readLine());
        double[][] pos = new double[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            pos[i][0] = Double.parseDouble(st.nextToken());
            pos[i][1] = Double.parseDouble(st.nextToken());
        }

        double width = 0;
        for (int i = 0; i < N-1; i++) {
            width += pos[i][0] * pos[i+1][1];
            width -= pos[i+1][0] * pos[i][1];
        }
        width += pos[N-1][0] * pos[0][1];
        width -= pos[N-1][1] * pos[0][0];
        width = Math.abs(width) / 2.0;
        System.out.printf("%.1f\n", width);
    }
}
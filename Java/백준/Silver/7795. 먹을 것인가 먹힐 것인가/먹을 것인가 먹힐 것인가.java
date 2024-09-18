import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(in.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(in.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int aIdx = N-1, bIdx = M-1, answer = 0;

            List<Integer> aList = new ArrayList<>();
            List<Integer> bList = new ArrayList<>();

            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < N; i++) {
                aList.add(Integer.parseInt(st.nextToken()));
            }

            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < M; j++) {
                bList.add(Integer.parseInt(st.nextToken()));
            }

            Collections.sort(aList);
            Collections.sort(bList);

            while (aIdx >= 0 && bIdx >= 0) {
                if (aList.get(aIdx) > bList.get(bIdx)) {
                    answer += (bIdx+1);
                    aIdx--;
                } else bIdx--;
            }
            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }
}
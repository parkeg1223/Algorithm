import java.io.*;
import java.util.*;

public class Main {

    static int N, T;
    static long answer;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(in.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(in.readLine());
            int[] nArr = new int[N];
            List<int[]> nList = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < N; i++) {
                int num = Integer.parseInt(st.nextToken());
                nArr[i] = num;
                nList.add(new int[] {i, num});
            }

            nList.sort((o1, o2) -> {
                if (o1[1] == o2[1]) return o1[0] - o2[0];
                return o2[1] - o1[1];
            });

            int idx = 0, curIdx;
            for (int i = 0; i < nList.size(); i++) {
                int[] curMax = nList.get(i);
                curIdx = curMax[0];
                if (curIdx < idx) continue;
                for (int j = idx; j < curIdx; j++) {
                    answer += curMax[1] - nArr[j];
                }
                idx = curIdx + 1;
                if (idx == nList.size()) break;
            }

            sb.append(answer).append("\n");
            answer = 0;
        }
        System.out.print(sb);
    }
}
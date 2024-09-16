import java.io.*;
import java.util.*;

public class Main {

    static int curr;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        List<Integer> nList = new ArrayList<>();
        Map<Integer, Integer> nMap = new HashMap<>();
        Map<Integer, Integer> sMap = new HashMap<>();
        st = new StringTokenizer(in.readLine());

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if (nMap.containsKey(n)) {
                nMap.put(n, nMap.get(n) + 1);
            } else {
                nList.add(n);
                sMap.put(n, curr++);
                nMap.put(n, 1);
            }
        }

        nList.sort((o1, o2) ->  {
            if (nMap.get(o1).equals(nMap.get(o2))) return sMap.get(o1) - sMap.get(o2);
            return nMap.get(o2) - nMap.get(o1);
        });

        sb = new StringBuilder();
        for (int num: nList) {
            for (int i = 0; i < nMap.get(num); i++)
                sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
}
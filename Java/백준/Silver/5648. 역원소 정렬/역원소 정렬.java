import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb;
        int n = -1;
        ArrayList<Long> nList = new ArrayList<>();
        while (n != 0) {
            st = new StringTokenizer(in.readLine());
            if (n == -1) n = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                sb = new StringBuilder(st.nextToken());
                nList.add(Long.parseLong(sb.reverse().toString()));
                n--;
            }
        }

        Collections.sort(nList);

        sb = new StringBuilder();
        for (Long num: nList) {
            sb.append(num).append("\n");
        }
        System.out.print(sb);
    }
}
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(in.readLine());
        List<Integer> nList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            nList.add(Integer.parseInt(in.readLine()));
        }

        nList.sort(Collections.reverseOrder());
        for (int n: nList) {
            sb.append(n).append("\n");
        }
        System.out.print(sb);
    }
}
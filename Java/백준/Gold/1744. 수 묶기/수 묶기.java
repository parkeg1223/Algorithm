import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        List<Integer> pos = new ArrayList<>();
        List<Integer> nonpos = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(in.readLine());
            if (n > 0) pos.add(n);
            else nonpos.add(n);
        }
        int pSize = pos.size(), npSize = nonpos.size();

        pos.sort(Collections.reverseOrder());
        Collections.sort(nonpos);

        int idx, maxSum = 0;
        for (idx = 0; idx < pSize-1; idx+=2) {
            if (pos.get(idx) != 1 && pos.get(idx+1) != 1) {
                maxSum += pos.get(idx) * pos.get(idx+1);
            } else maxSum += pos.get(idx) + pos.get(idx+1);
        }
        if (idx == pSize-1) maxSum += pos.get(idx);

        for (idx = 0; idx < npSize-1; idx+=2) {
            maxSum += nonpos.get(idx) * nonpos.get(idx+1);
        }
        if (idx == npSize-1) maxSum += nonpos.get(idx);
        System.out.println(maxSum);
    }
}
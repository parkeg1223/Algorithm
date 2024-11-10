import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(in.readLine());

        while (T-- > 0) {
            Map<String, Boolean> map = new HashMap<>();
            String record = in.readLine();
            String[] s;
            while ((s = in.readLine().split(" "))[1].equals("goes")) {
                map.put(s[2], true);
            }
            s = record.split(" ");
            for (String str: s) {
                if (!map.containsKey(str)) sb.append(str).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
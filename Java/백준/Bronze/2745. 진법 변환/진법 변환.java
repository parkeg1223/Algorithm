import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        String N = st.nextToken();
        int B = Integer.parseInt(st.nextToken());
        System.out.println(Integer.parseInt(N, B));
    }
}
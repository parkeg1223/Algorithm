import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        int w = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        if (w >= a*v) System.out.println(1);
        else System.out.println(0);
    }
}
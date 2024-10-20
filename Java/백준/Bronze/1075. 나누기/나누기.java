import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        n = (n/100) * 100;
        int f = Integer.parseInt(in.readLine());

        for (int i = n; i < n+10; i++) {
            if (i % f == 0) {
                System.out.println("0" + (i-n));
                return;
            }
        }

        for (int i = n+10; i < n+100; i++) {
            if (i % f == 0) {
                System.out.println(i-n);
                return;
            }
        }
    }
}
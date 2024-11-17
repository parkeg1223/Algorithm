import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] kda = Arrays.stream(in.readLine().split("/")).mapToInt(Integer::parseInt).toArray();
        if (kda[1] == 0 || kda[1] > kda[0] + kda[2]) System.out.println("hasu");
        else System.out.println("gosu");
    }
}

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        List<Integer> nList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            nList.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(nList);

        for (int i = 0; i < 3; i++) {
            System.out.print(nList.get(i) + " ");
        }
        System.out.println();
    }
}
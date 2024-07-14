import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int nRoom = 0;
		int[][] student = new int[7][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			int S = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			student[Y][S]++;
		}

		for (int i = 1; i <= 6; i++) {
			nRoom += (int)Math.ceil(student[i][0] / (double)K);
			nRoom += (int)Math.ceil(student[i][1] / (double)K);
		}

		System.out.println(nRoom);
	}
}
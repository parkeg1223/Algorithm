import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] buildings = new int[N];
		int[] nextIdx = new int[N];

		for (int i = 0; i < N; i++) {
			buildings[i] = Integer.parseInt(in.readLine());
		}

		int inf = Integer.MAX_VALUE;
		long answer = 0;
		for (int i = N-1; i >= 0; i--) {
			if (i == N-1) nextIdx[i] = inf;
			else {
				int k = i + 1;
				while (k < N && buildings[i] > buildings[k]) {
					k = nextIdx[k];
				}
				if (k != inf) answer += k-i-1;
				else answer += N-i-1;
				nextIdx[i] = k;
			}
		}
		System.out.println(answer);
	}
}
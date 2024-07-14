import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		StringTokenizer st;

		for (int i = 0; i < N; i++) {
			int[] alphas = new int[26];
			st = new StringTokenizer(in.readLine());
			String s1 = st.nextToken();
			String s2 = st.nextToken();
			for (char c: s1.toCharArray()) {
				alphas[c - 'a']++;
			}

			boolean isPossible = true;
			for (char c: s2.toCharArray()) {
				alphas[c - 'a']--;
			}

			for (int j = 0; j < 26; j++) {
				if (alphas[j] != 0) {
					System.out.println("Impossible");
					isPossible = false;
					break;
				}
			}

			if (isPossible) {
				System.out.println("Possible");
			}
		}
	}
}
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		Map<Integer, Boolean> map = new HashMap<>();

		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			map.put(num, true);
		}
		int x = Integer.parseInt(in.readLine());

		int answer = 0;
		for (int key: map.keySet()) {
			if (key < x && map.getOrDefault(x-key, false)) {
				answer++;
			}
		}

		System.out.println(answer/2);
	}
}
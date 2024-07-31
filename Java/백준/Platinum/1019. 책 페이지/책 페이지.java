import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main {

	static int res;
	static int[] nArr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		st = new StringTokenizer(in.readLine());
		int A = 1;
		int B = parseInt(st.nextToken());
		nArr = new int[10];
		
		int scale = 1;
		boolean isValid = true;
		while (A > 0 || B > 0) {
			// A++, B-- 반복해서 A는 0으로, B는 9로 끝나는 숫자로 만듦
			// A++, B-- 반복해서 A는 0으로, B는 9로 끝나는 숫자로 만듦
			while (A % 10 != 0) {
				calc(A, scale);
				A++;
				if (A > B) {
					isValid = false;
					break;
				}
			}
			if (!isValid) break;
			
			while (B % 10 != 9) {
				calc(B, scale);
				B--;
				if (A > B) {
					isValid = false;
					break;
				}
			}
			if (!isValid) break;
			
			A /= 10;
			B /= 10;
			
			for (int i = 0; i < 10; i++) {
				nArr[i] += (B-A+1) * scale;
			}
			
			scale *= 10;
			
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			sb.append(nArr[i]).append(" ");
		}
		System.out.println(sb.toString());
	}
	
	private static void calc(int n, int scale) {
		while (n > 0) {
			nArr[n%10] += scale;
			n /= 10;
		}
	}

}
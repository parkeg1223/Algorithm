import java.io.*;
import java.util.*;

public class Main {

    static class Score {
        String name;
        int kor, eng, mat;

        public Score(String name, int kor, int eng, int mat) {
            this.name = name;
            this.kor = kor;
            this.eng = eng;
            this.mat = mat;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(in.readLine());

        List<Score> sList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            sList.add(new Score(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        sList.sort((o1, o2) ->  {
            if (o1.kor != o2.kor) return o2.kor - o1.kor;
            if (o1.eng != o2.eng) return o1.eng - o2.eng;
            if (o1.mat != o2.mat) return o2.mat - o1.mat;
            return o1.name.compareTo(o2.name);
        });

        for (Score score: sList) {
            sb.append(score.name).append("\n");
        }
        System.out.print(sb);
    }
}
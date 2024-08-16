import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main {

	static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        int[] popu = new int[N+1];
        int minDiff = Integer.MAX_VALUE;
        
        st = new StringTokenizer(br.readLine());
        
        //인구수 정의
        int popuSum = 0;
        for(int i=1; i<=N; i++) {
            popuSum += popu[i] = Integer.parseInt(st.nextToken());
        }
        
        // 그래프 생성
        graph = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<Integer>());
        }
        
        // 그래프 입력
        for(int n=1; n<=N; n++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            for(int m=0; m<M; m++) {
                graph.get(n).add(Integer.parseInt(st.nextToken()));
            }
        }
        
        int[] secA, secB;
        for (int i=1; i<(1<<N)-1; i++) {
            visited = new boolean[N+1];
            int n = Integer.bitCount(i);
            secA = new int[n];
            secB = new int[N-n];
            
            // 영역별 노드
            int cntA = 0, cntB = 0;
            for (int j = 0; j < N; j++) {
                if ((i & (1<<j)) != 0) {
                    secA[cntA++] = j+1;
                } else {
                    secB[cntB++] = j+1;
                }
            }
            
            // 방문처리
            for(int x: secB) {
                visited[x] = true;
            }
            dfs(secA[0]);
            if(!isVisited()) continue;
            
            Arrays.fill(visited, false);
            for (int x: secA) {
                visited[x] = true;
            }
            dfs(secB[0]);
            if(!isVisited()) continue;
            
            // 인구계산
            int popuA = 0, popuB = 0;
            for(int x=0; x<n; x++) {
                popuA += popu[secA[x]];
            }
            popuB = popuSum - popuA;
            
            minDiff = Math.min(minDiff, Math.abs(popuA - popuB));
        }
        
        System.out.println(minDiff==Integer.MAX_VALUE?-1:minDiff);
        
        br.close();
    }
    
    // 영역 검증
    static void dfs(int cur) {
        visited[cur] = true;
        
        for(int next : graph.get(cur)) {
            if(!visited[next]) {
                dfs(next);
            }
        }
    }
    
    // 방문 확인
    static boolean isVisited() {
        for(int i=1; i<visited.length; i++) {
            if(visited[i] == false) return false; 
        }
        return true;
    }
}
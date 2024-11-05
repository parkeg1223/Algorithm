import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> sco = new PriorityQueue<>();
        for (int sc: scoville) {
            sco.add(sc);
        }
        
        int min1, min2;
        while (sco.peek() < K) {
            answer++;
            if (sco.size() == 1) return -1;
            min1 = sco.poll();
            min2 = sco.poll();
            sco.add(min1 + min2 * 2);
        }
        
        return answer;
    }
}
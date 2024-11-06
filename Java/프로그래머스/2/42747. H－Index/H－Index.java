import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int i = 0;
        Arrays.sort(citations);
        
        int idx = 0;
        for (i = 0; i <= citations.length; i++) {
            while (citations[idx] < i) {
                idx++;
                if (idx == citations.length) return i-1;
            }
            if (citations.length - idx < i) break;
        }
        return i-1;
    }
}
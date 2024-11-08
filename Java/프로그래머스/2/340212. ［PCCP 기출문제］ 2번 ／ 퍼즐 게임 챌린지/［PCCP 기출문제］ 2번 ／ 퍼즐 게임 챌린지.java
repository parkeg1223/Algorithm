import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int start = 1, mid = 0, end = 0;
        for (int diff: diffs) if (end < diff) end = diff;

        while (start < end) {
            mid = (start + end) / 2;
            if (solve(diffs, times, limit, mid)) {
                end = mid;
            } else {
                start = mid+1;
            }
        }
        
        return start;
    }
    
    public boolean solve(int[] diffs, int[] times, long limit, int level) {
        long time = 0;
        for (int i = 0; i < diffs.length; i++) {
            if (diffs[i] <= level) time += times[i];
            else {
                if (i == 0) time += times[i] * (diffs[i] - level + 1);
                else time += (times[i-1] + times[i]) * (diffs[i] - level) + times[i];
            }
        }
        
        if (time <= limit) return true;
        return false;
    }
}
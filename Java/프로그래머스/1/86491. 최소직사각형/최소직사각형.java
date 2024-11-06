class Solution {
    public int solution(int[][] sizes) {
        int maxMin = 0, maxMax = 0;
        for (int i = 0; i < sizes.length; i++) {
            maxMin = Math.max(maxMin, Math.min(sizes[i][0], sizes[i][1]));
            maxMax = Math.max(maxMax, Math.max(sizes[i][0], sizes[i][1]));
        }
        return maxMin * maxMax;
    }
}
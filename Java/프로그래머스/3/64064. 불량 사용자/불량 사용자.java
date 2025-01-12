import java.util.*;

class Solution {
    int nStep;
    Set<String> set = new HashSet<>();
    String[] userIds, bannedIds;
    
    public int solution(String[] user_id, String[] banned_id) {
        userIds = user_id;
        bannedIds = banned_id;
        nStep = bannedIds.length;
        dfs(new boolean[userIds.length], 0);
        return set.size();
    }
    
    public void dfs(boolean visited[], int step) {
        if (step == nStep) {
            String ans = "";
            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) ans += i;
            }
            set.add(ans);
            return;
        }
        
        for (int i = 0; i < userIds.length; i++) {
            if (visited[i]) continue;
            if (userIds[i].matches(bannedIds[step].replaceAll("\\*", "."))) {
                visited[i] = true;
                dfs(visited, step+1);
                visited[i] = false;
            }
        }
    }
}
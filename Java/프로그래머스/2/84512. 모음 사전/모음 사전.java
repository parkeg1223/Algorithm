import java.util.*;

class Solution {
    char[] alphabet = {' ', 'A', 'E', 'I', 'O', 'U'};
    char[] curr = new char[5];
    List<String> dict = new ArrayList<>();
    public int solution(String word) {
        makeDict(0);
        
        Set<String> temp = new HashSet<>(dict);
        dict = new ArrayList(temp);
        Collections.sort(dict);
        
        return dict.indexOf(word);
    }
    
    public void makeDict(int step) {
        if (step == 5) {
            String s = "";
            for (char c: curr) {
                if (c != ' ') s += c;
            }
            dict.add(s);
            return;
        }
        
        for (int i = 0; i < 6; i++) {
            curr[step] = alphabet[i];
            makeDict(step+1);
        }
    }
    
}
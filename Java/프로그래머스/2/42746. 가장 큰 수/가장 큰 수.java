import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        List<String> list = new ArrayList<>();
        
        for (int number: numbers) {
            list.add(Integer.toString(number));    
        }
        
        Collections.sort(list, (s1, s2) -> {
            return (s2+s1).compareTo(s1+s2);
        });
        
        for (String s: list) {
            answer += s;
        }
        
        if (answer.charAt(0) == '0') return "0";
        return answer;
    }
}
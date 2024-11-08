class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int vLen = toSec(video_len);
        int curr = toSec(pos);
        int start = toSec(op_start);
        int end = toSec(op_end);
        if (curr >= start && curr < end) curr = end;
        
        for (String command: commands) {
            if (command.equals("prev")) {
                if (curr - 10 < 0) curr = 0;   
                else curr -= 10;
            } else {
                if (curr + 10 > vLen) curr = vLen;
                else curr += 10;
            }
            if (curr >= start && curr < end) curr = end;
        }
        
        // else if (curr > vLen) curr = vLen;
        
        
        return String.format("%02d", curr/60) + ":" + String.format("%02d", curr%60);
    }
    
    public int toSec(String time) {
        return Integer.parseInt(time.substring(0, 2)) * 60 +
                Integer.parseInt(time.substring(3, 5));
    }
}
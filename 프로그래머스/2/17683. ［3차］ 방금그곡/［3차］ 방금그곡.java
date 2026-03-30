import java.io.*;

class Solution {
    
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int maxPlayTime = -1;
        
        m = replaceSharp(m); 
        
        for(String info : musicinfos) {
            String[] arr = info.split(",");
            
            String start = arr[0];
            String end = arr[1];
            String title = arr[2];
            String score = replaceSharp(arr[3]);
            
            int playTime = getPlayTime(start, end);
            
            StringBuilder playedMelody = new StringBuilder();
            for(int i=0; i<playTime; i++) {
                playedMelody.append(score.charAt(i % score.length()));
            }
            
            if(playedMelody.toString().contains(m)) {
                if(maxPlayTime < playTime) {
                    maxPlayTime = playTime;
                    answer = title;
                }
            }   
        }
        
        return answer; 
    }
    
    public String replaceSharp(String score) {
        score = score.replaceAll("C#", "c");
        score = score.replaceAll("D#", "d");
        score = score.replaceAll("F#", "f");
        score = score.replaceAll("G#", "g");
        score = score.replaceAll("A#", "a");
        score = score.replaceAll("B#", "b");
        return score;
    }
    
    public int getPlayTime(String start, String end) {
        String[] s = start.split(":");
        String[] e = end.split(":");
        
        int startMin = Integer.parseInt(s[0])*60 + Integer.parseInt(s[1]);
        int endMin = Integer.parseInt(e[0])*60 + Integer.parseInt(e[1]);
        
        return endMin - startMin;
    }
}
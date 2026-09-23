import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answerList = new ArrayList<>();
        
        int maxDay = 0; 
        
        for (int i = 0; i < progresses.length; i++) {
            int day = (100 - progresses[i] + speeds[i] - 1) / speeds[i];
            
            if (day > maxDay) {
                maxDay = day;
                answerList.add(1);
            } else {
                int lastIdx = answerList.size() - 1;
                answerList.set(lastIdx, answerList.get(lastIdx) + 1);
            }
        }
        
        return answerList.stream().mapToInt(i -> i).toArray();
    }
}
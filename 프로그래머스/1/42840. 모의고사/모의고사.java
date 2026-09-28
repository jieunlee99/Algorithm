import java.util.*;

class Solution {
    public int[] solution(int[] answers) {     
        int[] cnt = {0, 0, 0};
        
        int[] p1 = {1, 2, 3, 4, 5}; // 5개로 돌림
        int[] p2 = {2, 1, 2, 3, 2, 4, 2, 5}; // 8개로 돌림
        int[] p3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}; // 10개로 돌림
        
        for(int i=0; i<answers.length; i++) {
            if(answers[i] == p1[i%5]) {
                cnt[0]++;
            }
            
            if(answers[i] == p2[i%8]) {
                cnt[1]++;
            }
            
            if(answers[i] == p3[i%10]) {
                cnt[2]++;
            }
        }
        
        int maxScore = Math.max(cnt[0], Math.max(cnt[1], cnt[2]));
        
        List<Integer> answer = new ArrayList<>();
        for(int i=0; i<3; i++) {
            if(cnt[i] == maxScore) {
                answer.add(i+1);
            }
        }
        
        return answer.stream().mapToInt(i->i).toArray();
    }
}
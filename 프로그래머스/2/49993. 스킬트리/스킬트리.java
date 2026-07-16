import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for(int i = 0; i < skill_trees.length; i++) {
            
            // skill에 포함되는 스킬만 남김
            String pre = skill_trees[i].replaceAll("[^" + skill + "]", "");   
            
            // skill과 pre의 길이를 맞춰줌
            String need = skill.substring(0, pre.length());
            
            // 일치하면 가능
            if(pre.equals(need)) {
                answer++;
            }
        }
        
        return answer;
    }
}
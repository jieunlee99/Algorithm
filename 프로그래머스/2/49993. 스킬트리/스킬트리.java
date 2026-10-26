class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for(String skill_tree:skill_trees) {
            String str = skill_tree.replaceAll("[^"+skill+"]", "");
            
            for(int i=0; i<skill.length()+1; i++) {
                String sub_skill = skill.substring(0, i);
                
                if(str.equals(sub_skill)) {
                    answer++;
                    break;
                }
            }
        }
        return answer;
    }
}
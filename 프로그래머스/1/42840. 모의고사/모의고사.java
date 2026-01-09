import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        // 1. 수포자들의 패턴을 2차원 배열로 관리
        int[][] patterns = {
            {1, 2, 3, 4, 5},
            {2, 1, 2, 3, 2, 4, 2, 5},
            {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        };
        
        int[] scores = new int[3];
        
        // 2. 점수 계산 (반복문을 활용해 깔끔하게)
        for(int i = 0; i < answers.length; i++) {
            for(int j = 0; j < patterns.length; j++) {
                if(answers[i] == patterns[j][i % patterns[j].length]) {
                    scores[j]++;
                }
            }
        }
        
        // 3. 최대 점수 찾기
        int maxScore = Math.max(scores[0], Math.max(scores[1], scores[2]));
        
        // 4. 최대 점수와 일치하는 사람(인덱스+1)을 리스트에 담기
        List<Integer> list = new ArrayList<>();
        if(scores[0] == maxScore) list.add(1);
        if(scores[1] == maxScore) list.add(2);
        if(scores[2] == maxScore) list.add(3);
        
        // 5. 리스트를 배열로 변환하여 반환
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
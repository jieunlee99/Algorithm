import java.util.*;

class Solution {
    
    Map<String, List<Integer>> map = new HashMap<>();
    
    public int[] solution(String[] info, String[] query) {
        //  [조건]을 만족하는 사람 중 코딩테스트 점수를 X점 이상 받은 사람은 모두 몇 명인지
        int[] answer = new int[query.length];
        
        // 1. 지원자 정보 전처리
        for(String str:info) {
            String[] arr = str.split(" ");
            
            String[] condition = {
                arr[0], arr[1], arr[2], arr[3]
            };
            
            int score = Integer.parseInt(arr[4]);
            
            // 해당 지원자가 포함될 수 있는 모든 조건 조합 생성
            makeKey(condition, score, 0, "");
        }
        
        // 2. 각 조건에 들어있는 점수 리스트 정렬
        for(List<Integer> scores:map.values()) {
            Collections.sort(scores);
        }
        
        // 3. query 처리
        for(int i=0; i<query.length; i++) {
            String q = query[i].replace(" and ", " ");
            
            String[] arr = q.split(" ");
            
            String key = arr[0] + arr[1] + arr[2] + arr[3];
            int targetScore = Integer.parseInt(arr[4]);
            
            if(!map.containsKey(key)) {
                answer[i] = 0;
                continue;
            }
            
            List<Integer> scores = map.get(key);
            int idx = lowerBound(scores, targetScore);
            answer[i] = scores.size() - idx;
        }
        
        return answer;
    }
    
    public void makeKey(String[] condition, int score, int depth, String key) {
        if(depth == 4) {
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(score);
            return;
        }
        
        makeKey(condition, score, depth+1, key+condition[depth]);
        makeKey(condition, score, depth+1, key+"-");
    }
    
    public int lowerBound(List<Integer> scores, int target) {
        int left = 0;
        int right = scores.size();
        
        while(left < right) {
            int mid = (left+right)/2;
            
            if(scores.get(mid) >= target) {
                right = mid;
            } else{
                left = mid+1;
            }
        }
        
        return left;
    }
}
import java.util.*;

class Solution {
    Map<String, Integer> map;
    int maxOrder;
    
    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();
        
        // 각 주문을 오름차순으로 정렬
        for(int i=0; i<orders.length; i++) {
            char[] arr = orders[i].toCharArray();
            Arrays.sort(arr);
            orders[i] = new String(arr);
        }
        
        for(int c : course) {
            map = new HashMap<>();
            maxOrder = 0;
            
            for(String order : orders) {
                dfs(order, "", c, 0);
            }
            
            // 주문 수가 2 이상 + 가장 많았던 조합 모두 추가
            if(maxOrder >= 2) {
                for(String key : map.keySet()) {
                    if(map.get(key) == maxOrder) {
                        answer.add(key);
                    }
                }
            }
        }
        
        Collections.sort(answer);
        return answer.toArray(new String[0]);
    }
    
    public void dfs(String order, String current, int depth, int start) {
        if(current.length() == depth) {
            int count = map.getOrDefault(current, 0) + 1;
            map.put(current, count);
            
            if(maxOrder < count) {
                maxOrder = count;
            }
            return;
        }
        
        // 문자열 오름차순을 유지하기 위해 start부터 시작
        for(int i=start; i<order.length(); i++) {
            char c = order.charAt(i);
            dfs(order, current+c, depth, i+1);
        }
    }
}
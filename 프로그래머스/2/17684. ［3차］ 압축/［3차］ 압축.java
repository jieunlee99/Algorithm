import java.util.*;

class Solution {
    public int[] solution(String msg) {
        int idx = 1;
        
        // A~Z 넣기 
        Map<String, Integer> dict = new HashMap<>();
        for(char c='A'; c<='Z'; c++) {
            dict.put(String.valueOf(c), idx++);
        }
        
        List<Integer> list = new ArrayList<>();
        
        int n = msg.length();
        
        for(int i=0; i<n; i++) {
            
            String word = "";
            
            // 현재 입력과 일치하는 가장 긴 문자열 w 찾기
            while (i < n && dict.containsKey(word + msg.charAt(i))) {
                word += msg.charAt(i);
                i++;
            }

            // 정답에 추가
            list.add(dict.get(word));
            
            
            // 입력에서 처리되지 않은 다음 글자가 남아있다면(c), w+c에 해당하는 단어 사전에 등록
            if (i < n) {
                dict.put(word + msg.charAt(i), idx);
                idx++;
                i--;
            }
        }
        
        return list.stream().mapToInt(i->i).toArray();
    }
}
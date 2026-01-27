import java.util.*;


class Solution {
    public int solution(String name) {
        int answer = 0;
        int len = name.length();
        
        // 좌우 이동의 기본값은 오른쪽까지 끝까지 이동할 때의 len-1
        int move = len -1 ;
        
        for(int i=0; i<len; i++) {
            // 상하이동 => 고정값
            char c = name.charAt(i);
            answer += Math.min(c-'A', 'Z'-c+1);
            
            // 좌우이동 => 변동값
            int next = i+1;
            // 현재 위치 다음부터 연속된 'A'가 얼마나 이어지는지 확인
            while(next < len && name.charAt(next) == 'A') {
                next++;
            }
            
            // 방법 1: 오른쪽으로 i만큼 갔다가 다시 왼쪽으로 돌아가서 끝에서부터 next까지 오는 경우
            move = Math.min(move, i*2+(len-next));
            
            // 방법 2: 처음부터 왼쪽으로 돌아가서 끝에서 next까지 왔다가 다시 i까지 돌아오는 경우
            move = Math.min(move, (len-next)*2+i);
            
        }
        
        return answer+move;
    }
}
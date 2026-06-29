class Solution {
    public int solution(String name) {
        int answer = 0;
        
        int len = name.length();
        
        // 맨 왼쪽에서 맨 오른쪽으로 이동 (최대값)
        int move = len-1;

        for(int i=0; i<len; i++) {
            char c = name.charAt(i);
            answer += Math.min(c-'A', 'Z'-c+1);
            
            int next = i+1;
            
            while(next < len && name.charAt(next) == 'A') {
                next++;
            }
            
            // 방법 1: 오른쪽으로 i만큼 갔다가 다시 왼쪽으로 돌아가서 끝에서부터 next까지 오는 경우
            move = Math.min(move, i * 2 + (len-next));            
            // 방법 2: 처음부터 왼쪽으로 돌아가서 끝에서 next까지 왔다가 다시 i까지 돌아오는 경우

            move = Math.min(move, (len-next) * 2 + i);
        }
        
        return answer+move;
    }
}
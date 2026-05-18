class Solution {
    public int solution(int order) {
        int answer = 0;
        
        String stringOrder = String.valueOf(order);
        
        for(int i=0; i<stringOrder.length(); i++) {
        	
        	if(stringOrder.charAt(i)=='3'
        		|| stringOrder.charAt(i)=='6'
        		|| stringOrder.charAt(i)=='9') {
        		answer++;
        	}
        }
        
        return answer;
    }
}
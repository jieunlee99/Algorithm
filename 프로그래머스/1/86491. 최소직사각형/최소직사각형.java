class Solution {
    public int solution(int[][] sizes) {
        int n = sizes.length;
        
        int r = Integer.MIN_VALUE;
        int c = Integer.MIN_VALUE;
        
        for(int i=0; i<n; i++) {
            if(sizes[i][0] < sizes[i][1]) {
                int temp = sizes[i][0];
                sizes[i][0] = sizes[i][1];
                sizes[i][1] = temp;
            }
            
            r = Math.max(r, sizes[i][0]);
            c = Math.max(c, sizes[i][1]);
        }
        
        return r*c;
    }
}
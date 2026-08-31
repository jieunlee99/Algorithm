class Solution
{
    public int solution(int n, int a, int b)
    {
        int cnt = 0;
        
        while(a != b) {
            if(a%2 == 1) {
                a++;
            } 
            a/=2;
            
            if(b%2 == 1) {
                b++;
            } 
            b/=2;
            
            cnt++;
        }
        
        return cnt;
    }
}
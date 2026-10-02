#include <string>
#include <stack>

using namespace std;

bool solution(string s)
{
    stack<char> st;
    
    for(int i = 0; i < s.size(); i++) {
        char c = s[i];
        
        if(c == '(') {
            st.push('(');
        } 
        else if(c == ')') {
            
            if(st.empty()) {
                return false;
            }
            
            st.pop();
        }
    }
    
    return st.empty();
}
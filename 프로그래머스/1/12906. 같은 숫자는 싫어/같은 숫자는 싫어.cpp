#include <vector>
#include <iostream>
#include <stack>

using namespace std;

vector<int> solution(vector<int> arr) 
{
    vector<int> answer;
    
    stack<int> stack;
    
    for(int i=0; i<arr.size(); i++) {
        if(stack.empty() || stack.top() == arr[i]) { 
            stack.push(arr[i]);
            continue;
        }
        
        answer.push_back(stack.top());
        
        while(!stack.empty()) {
            stack.pop();
        }
        
        stack.push(arr[i]);
    }
    
    answer.push_back(stack.top());

    return answer;
}
#include <string>
#include <vector>

using namespace std;

static int n, target;
static vector<int> numbers;
static int answer = 0;

void dfs(vector<int> numbers, int target, int sum, int depth) {
    if(depth == n) {
        if(sum == target) {
            answer++;
        }
        return;
    }
    
    dfs(numbers, target, sum+numbers[depth], depth+1);
    dfs(numbers, target, sum-numbers[depth], depth+1);
}

int solution(vector<int> numbers, int target) {
    n = numbers.size();
    
    dfs(numbers, target, 0, 0);
    
    return answer;
}
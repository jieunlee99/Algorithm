#include <vector>
#include <unordered_set>
#include <algorithm>
using namespace std;

int solution(vector<int> nums)
{
    unordered_set<int> unique_types(nums.begin(), nums.end());
    return min(nums.size()/2, unique_types.size());
}
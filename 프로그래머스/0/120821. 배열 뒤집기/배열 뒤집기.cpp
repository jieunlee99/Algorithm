#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> num_list) {
    vector<int> reverse_list(num_list.size());
    
    for(int i=num_list.size()-1; i>=0; i--) {
        reverse_list[i] = num_list[num_list.size()-i-1];
    }
    
    return reverse_list;
}
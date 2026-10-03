#include <string>
#include <vector>
#include <cmath>

using namespace std;

int solution(vector<vector<int>> sizes) {
    
    int r = 0, c = 0;
    
    for(vector<int> size:sizes) {
        if(size[0] < size[1]) {
            int temp = size[0];
            size[0] = size[1];
            size[1] = temp;
        }
        
        r = max(r, size[0]);
        c = max(c, size[1]);
    }
    
    return r * c;
}
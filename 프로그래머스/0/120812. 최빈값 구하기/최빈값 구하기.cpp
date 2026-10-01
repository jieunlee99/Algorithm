#include <string>
#include <vector>

using namespace std;

int solution(vector<int> array) {
    int count[1000] = {0};

    for (int num : array) {
        count[num]++;
    }

    int answer = -1;
    
    int maxCount = 0;
    int modeCount = 0;

    for (int i = 0; i < 1000; i++) {
        if (count[i] > maxCount) {
            maxCount = count[i];
            answer = i;
            modeCount = 1;
        }
        else if (count[i] == maxCount && count[i] != 0) {
            modeCount++;
        }
    }

    return modeCount > 1 ? -1 : answer;
}
#include <string>
#include <vector>

using namespace std;

int solution(int n, vector<int> lost, vector<int> reserve) {
    vector<int> v(n, 1);

    for(int l : lost) {
        v[l - 1]--;
    }

    for(int r : reserve) {
        v[r - 1]++;
    }

    for(int i = 0; i < n; i++) {

        // 체육복이 있는 학생은 넘어감
        if(v[i] > 0) {
            continue;
        }

        // 앞 학생에게 빌리기
        if(i > 0 && v[i - 1] == 2) {
            v[i - 1]--;
            v[i]++;
        }

        // 뒤 학생에게 빌리기
        else if(i < n - 1 && v[i + 1] == 2) {
            v[i + 1]--;
            v[i]++;
        }
    }

    int answer = 0;

    for(int x : v) {
        if(x > 0) {
            answer++;
        }
    }

    return answer;
}
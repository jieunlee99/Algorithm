#include <string>
#include <vector>

using namespace std;

int gcd(int a, int b){
    if(a%b == 0) {
        return b;
    }
    
    return gcd(b, a%b);
}

vector<int> solution(int numer1, int denom1, int numer2, int denom2) {
    int numerator = numer1 * denom2 + numer2 * denom1;
    int denominator = denom1 * denom2;

    int g = gcd(numerator, denominator);

    return {numerator / g, denominator / g};
}


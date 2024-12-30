function solution(arr) {
    return arr.map((x)=> x >= 50 && x%2 === 0 ? x/2: (x < 50 && x%2 === 1 ? x*2 : x))
}
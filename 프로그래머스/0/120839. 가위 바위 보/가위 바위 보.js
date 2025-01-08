function solution(rsp) {
    return rsp.split('').map((x) => x === "0" ? "5" : (x === "2"? "0" : "2")).join('')
}
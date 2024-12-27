function solution(strArr) {
    let result = [];
    let len = strArr.length;
    
    for(let i=0; i<len; i++) {
        result.push(i%2 === 0 ? strArr[i].toLowerCase(): strArr[i].toUpperCase());
    }
    
    
    return result;
}
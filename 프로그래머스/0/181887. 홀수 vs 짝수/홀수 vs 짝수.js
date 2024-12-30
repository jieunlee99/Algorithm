function solution(num_list) {
    let sumOdd = 0;
    let sumEven = 0;
    
    for(let i=0; i<num_list.length; i++) {
        i%2 === 0 ? sumOdd += num_list[i] : sumEven += num_list[i];
    }
    
    return Math.max(sumOdd, sumEven);
}
function solution(start_num, end_num) {
    let nums = [];
    
    for(let i=start_num; i>=end_num; i--) {
        nums.push(i);
    }
    
    return nums;
}
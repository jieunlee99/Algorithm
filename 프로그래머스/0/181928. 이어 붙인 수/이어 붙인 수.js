function solution(num_list) {
    let str1 = "";
    let str2 = "";
    
    let len = num_list.length;
    for(let i=0;i<len; i++) {
        num_list[i]%2 === 0 ? str1 += num_list[i]:str2+=num_list[i];
    }
    
    return Number(str1)+Number(str2);
}
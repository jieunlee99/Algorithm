function solution(food) {
    let result = [];
    
    let end = food.length;
    for(let i=1; i<=end; i++) {
        let r = Math.floor(food[i]/2);
        for(let j=0; j<r; j++) {
            result.push(i);
        }
    }
    
    let str1 = result.join('');
    let str2 = result.reverse().join('');
    
    return `${str1}0${str2}`
}
function solution(numbers) {
    numbers.sort((n1, n2)=> n1-n2);
    let len = numbers.length;
    return numbers[len-1]*numbers[len-2];
}
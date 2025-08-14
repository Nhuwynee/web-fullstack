/**
 
Kiểu số (Number) trong javascript

1. Tạo giá trị Number
    - Các cách tạo
    - dùng cách nào? Tại sao?
    - Kiểm tra data type

2. Làm việc với Number
    - To string
    - to fixed
 */

var age = 1;
var PI = 3.14;
var number = 3000.8982392;

var result = 20 / 'ABC';
console.log(result); // NaN
console.log(typeof result); // Number

// Kiểm tra hàm NaN
console.log(isNaN(result));

console.log(PI.toFixed()); // 3
console.log(typeof PI.toFixed()); // string
console.log(number.toFixed(2)); // 3000.89
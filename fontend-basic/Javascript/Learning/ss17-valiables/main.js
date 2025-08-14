/**
 Kiểu dữ liệu trong javascript

 1. Dữ liệu nguyên thủy - Primitive Data
    - Number
    - String
    - Boolean
    - Undefined: var age;
    - Null
    - Symbol

2. Dữ liệu phức tạp - Complex Data
    - Function
    - Object
 */

// Number type
var a = 1;
var b = 2;
console.log(typeof a); // Kiểm tra KDL


// Symbol
var id = Symbol('id'); // unique
var id2 = Symbol('id'); // unique
console.log(id === id2);


// function
var myFunction = function() {
    alert('Hiiii');
}
myFunction();


// Object type
var myObject = {
    // key: value,
    name: 'Yen Nhu',
    age: 18,
    address: 'Da Nang',
    myFunc: function() {

    }
};
console.log('my object', myObject)


    // Có id nhưng dc gán tự tăng
var myArray = [
    'Java',
    'PHP',
    'Python'
];
console.log(myArray);

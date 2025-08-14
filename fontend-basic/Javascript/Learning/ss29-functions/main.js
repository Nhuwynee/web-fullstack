/**
  Hàm (function) trong javascript

  1. Hàm ?
    - Một khối mã
    - Làm 1 việc cụ thể
  
  2. Loại hàm
    - Build-in
    - Tự định nghĩa

  3. Tính chất
    - Không thực thi khi định nghĩa
    - Sẽ thực thi khi được gọi
    - Có thể nhận tham
    - Có thể trả về 1 giá trị

  4. Tạo hàm đầu tiên

  5. Các loại function
    - Declaration function
    - Expression function
    - Arrow function
    
 */

showMessage(); // gọi được trc khi thực thi
showMessage2(); // ko gọi dc trc khi thực thi

// 1. Declaration function
function showMessage() {
  console.log('Declaration function');
}

// 2. Expression function
var showMessage2 = function() {
  console.log('Expression function')
}

setTimeout(function logIn() {

})

var myObject = {
  myFunction: function testName() {

  }
}
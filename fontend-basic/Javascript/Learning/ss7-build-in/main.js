/**
    Giới thiệu 1 số hàm build-in
    1. Alert: Thông báo
    2. Console
    3. Comfirm
    4. Prompt
    5. Set timeout
    6. Set interval
 */

// 2.
var fullName = 'Yen Nhuw';
console.log(fullName);

// 3.
confirm('Xác nhận bạn đủ tuổi!');

// 4.
prompt('Xác nhận bạn đủ tuổi!');

// 5. truyền vào 1 function, chạy 1 lần
setTimeout(function() {
    alert('Thông báo!')
}, 1000)

// 6. Cách khoảng thời gian là chạy (chạy nhiều lần)
setInterval(function() {
    console.log('Đây là log!' + Math.random())
})
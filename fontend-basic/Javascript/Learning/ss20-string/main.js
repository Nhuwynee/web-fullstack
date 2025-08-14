/**
 * Chuỗi trong javascript
 * 
 * 1. Tạo chuỗi
 *     - Các cách tạo chuỗi
 *     - Nên dùng cách nào ? Lý do ?
 *     - Kiểm tra data type
 * 2. Một số case sử dụng backslash (\)
 * 3. Xem độ dài chuỗi
 * 4. Chú ý độ dài khi viết code
 * 5. Template string ES6;
 */

// var fullName = 'Yen Nhu'; // C1
var fullName1 = new String('Yen Nhu'); // C2

console.log(typeof fullName1); // in ra Object => không nên dùng

var fullName = 'Yen Nhu';
console.log(fullName.length);

var firstName = 'Nhu';
var lastName = 'Yen';
console.log(`Toi la: ${firstName} ${lastName}`);

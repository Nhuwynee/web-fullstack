// LÀM VIỆC VỚi CHUỖI

var myString = 'Lưu Ngọc Yến Như'

// keyword: Javascript string methods

// 1. Length
console.log(myString.length);

// 2. Find index
console.log(myString.indexOf('Y'));
console.log(myString.indexOf('Y', 4));
console.log(myString.search('Y'))
console.log(myString.toUpperCase);

// 3. Cut string
console.log(myString.slice(4, 6));
console.log(myString.slice(-3, -1)); // phải sang trái

// 4. Replace
console.log(myString.replace('N', 'n')); //chỉ thay thế chữ N đầu tiên thành n
console.log(myString.replace(/N/g, 'n')); // tìm dc tất cả chữ N trong chuỗi và thay thế

// 5. Convert to uppercase
console.log(myString.toUpperCase);

// 6. Convert to lowercase
console.log(myString.toLowerCase);

// 7. Trim
console.log(myString.trim);

// 8. Split
console.log(myString.split(' '));

// 9. Get a character by index
console.log(myString.charAt(1));


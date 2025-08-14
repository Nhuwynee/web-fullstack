// let myName = prompt("Mời nhập tên: ");
// console.log("My first name: " + myName);
// console.log(typeof myName);

const x1 = 10;
// x1 = 20; // ko khai báo lại dc 

const arr = [1, 2];
arr.push(3); // ok
arr[0] = 100; // ok
console.log("gán lại: " + arr[0]);

// arr = [4, 5]; // lỗi - gán lại tham chiếu mảng khác

const user1 = {name: 'Nhuw'};
user1.name = 'Như';
user1.age = 12;
// user1 = {name: 'Linh'}; // lỗi

console.log(user1);

const binhPhuong = (x) => {
    let kq = x * x;
    return kq;
}

console.log(binhPhuong);  // Kết quả ????

let animal = [
    'Chó',
    'Mèo',
    'Thỏ'
];

console.log[animal[0]];

animal[1] = 'Gấu';
console.log(animal[1]);
animal.forEach(a => console.log(a));
console.log(animal.length);


// ===============================================================
// ============= Tương tác với HTML ==============================

// 1. Lấy phần tử HTML

// 1.1. Lấy theo id
console.log('=============== Tương tác với HTML ==================')
let p = document.getElementById("title");
console.log('Lấy phần tử HTML: ' + p.innerText);
console.log(p);

// 1.2. Lấy theo class
let desc = document.querySelector(".desc");
// console.log('Lấy phần tử (class): ' + desc);
console.log(desc);

// ============= Thay đổi nội dung HTML ==============================
console.log('=============== Thay đổi nội dung HTML ==================')

// thay đổi text
p.innerText = "Lưu Ngọc Yến Như";

// thay đổi lung html
p.innerHTML = "<i>Heluu Nhuw</i>"

// thay đổi thuộc tính
document.querySelector(".myImg").src = "1.12.2.png";

// thay đổi css
desc.style.color = "red";

// ======================== Thêm sự kiện =============================
console.log('=============== Thêm sự kiện ==================')
let buttonElement = document.querySelector(".btn");
buttonElement.addEventListener("click", () => {
    console.log('Bạn vừa nhấn nút');
});






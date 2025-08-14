// 1.
bangCuuChuong();
function bangCuuChuong() {
    document.querySelector(".btn1").addEventListener("click", function () {
        let number = document.querySelector(".number1").value;

        let result = 'Bảng cửu chương của ' + number + '\n';
        for (let i = 1; i <= 10; i++) {
            result += `${number} x ${i} = ${number * i}\n`;
        }
        document.querySelector(".result1").innerText = result;
        document.querySelector(".number1").value = "";
    });
}


// 2.
inThu();
function inThu() {
    let buttonElement = document.querySelector(".btn2");
    buttonElement.addEventListener("click", function () {
        let number = document.querySelector(".number2").value;
        let result = 'Thứ của số ' + number + ' là: ';

        switch (number) {
            case 1: result += 'Chủ nhật'; break;
            case 2: result += 'Thứ hai'; break;
            case 3: result += 'Thứ ba'; break;
            case 4: result += 'Thứ tư'; break;
            case 5: result += 'Thứ năm'; break;
            case 6: result += 'Thứ sáu'; break;
            case 7: result += 'Thứ bảy'; break;
            default: result = 'Nhập không hợp lệ!';
        }

        document.querySelector(".result2").innerText = result;
        document.querySelector(".number2").value = "";
    });
}

// 3. 
inKyTu();
function inKyTu() {
    let buttonElement = document.querySelector(".btn3");
    buttonElement.addEventListener("click", function () {
        let input = document.querySelector(".input").innerText;
        let result = "";

        for (let c of input) {
            result += c + " ";
        }

        document.querySelector(".result3").innerText = result;
    });
}







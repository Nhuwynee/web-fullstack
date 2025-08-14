// 1.
inGiaiThua();
function inGiaiThua() {
    document.querySelector(".btn1").addEventListener("click", function () {
        let number = document.querySelector(".number1").value;

        let result = 'Kết quả giai thừa của ' + number + ' là: ' + giaiThua(number);

        document.querySelector(".result1").innerText = result;

        document.querySelector(".number1").value = "";
    });
}

function giaiThua(n) {
    if (n == 0 || n == 1) {
        return 1;
    }

    let result = 1;
    for (let i = 1; i <= n; i++) {
        result *= i;
    }

    return result;
}

// 2. 
function laSoChan(n) {
    if (n % 2 == 0) {
        return true;
    } else {
        return false;
    }
}

inKiemTraSoChan();
function inKiemTraSoChan() {
    document.querySelector(".btn2").addEventListener("click", function () {
        let number = document.querySelector(".number2").value;

        let result = number + " là số chẵn ? => " + laSoChan(number);

        document.querySelector(".result2").innerText = result;
        
        document.querySelector(".number2").value = "";
    })
}


// 3.
sayHello();
function sayHello(name = 'bạn') {
        document.querySelector(".btn3").addEventListener("click", function () {
        let text = document.querySelector(".text3").value;

        document.querySelector(".result3").innerText += " " + text;
        
        document.querySelector(".text3").value = "";
    })
}



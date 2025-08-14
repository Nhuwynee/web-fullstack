// 1.
addArr();
function addArr() {
    let input = document.querySelector(".result1").innerText;
    let arr = input.split(" ");
    document.querySelector(".btn1").addEventListener("click", function () {
        arr.push("React");

        let result = "";
        arr.forEach(element => {
            result += element + " ";
        });

        document.querySelector(".result1").innerText = result;
    });
}

// 2.
formatString();
function formatString() {
    let input = document.querySelector(".result2").innerText;
    document.querySelector(".btn2").addEventListener("click", function () {

        let result = input.toLowerCase() + " - Độ dài chuỗi: " + input.length;

        document.querySelector(".result2").innerText = result;
    });
}

// 3.
squareNumber();
function squareNumber() {
    let input = document.querySelector(".result3").innerText;
    let arr = input.split(" ");
    document.querySelector(".btn3").addEventListener("click", function () {
        let result = "";
        arr.forEach(element => {
            result += square(element) + " ";
        });

        document.querySelector(".result3").innerText = result;
    });
}

function square(number) {
    return number * number;
}



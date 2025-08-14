// 1.
changeColor();
function changeColor() {
    document.querySelector(".btn1").addEventListener("click", function () {
        document.body.style.backgroundColor = "pink";
    });
}

// 2.
inputText();
function inputText() {
    const inputElement = document.querySelector(".input2");
    const resultElement = document.querySelector(".result2");

    inputElement.addEventListener("input", function () {
        resultElement.innerText = inputElement.value;
    });
}

// 3.
inputName();
function inputName() {
    const formElement = document.querySelector(".form_submit");
    const inputElement = document.querySelector(".input3");

    formElement.addEventListener("submit", function (event) {
        event.preventDefault();
        console.log(inputElement.value);
    });
}




const button = document.querySelector(".button");
const nameInput = document.getElementById("name");
const emailInput = document.getElementById("email");
const contentInput = document.getElementById("content");
const resultBox = document.getElementById("result");

resultBox.innerHTML = `
    <div class="title">
        <h4>Thông tin bạn đã gửi:</h4>
        <input type="text" value="Chưa có dữ liệu.." readonly>
    </div>
`;

button.addEventListener('click', () => {
    const name = nameInput.value.trim();
    const email = emailInput.value.trim();
    const content = contentInput.value.trim();

    if (name && email && content) {
        resultBox.innerHTML = `
            <div class="title">
                <h4>Thông tin bạn đã gửi:</h4>
            </div>
            <div class="boxResult">
                <strong>Họ tên:</strong> ${name}
            </div>
            <div class="boxResult">
                <strong>Email:</strong> ${email}
            </div>
            <div class="boxResult">
                <strong>Tin nhắn:</strong> ${content}
            </div>
        `;
        nameInput.value = "";
        emailInput.value = "";
        contentInput.value = "";
    } else {
        resultBox.innerHTML = `
            <div class="title">
                <h4>Thông tin bạn đã gửi:</h4>
                <input type="text" value="Vui lòng nhập đủ thông tin" readonly>
            </div>
        `;
    }
});
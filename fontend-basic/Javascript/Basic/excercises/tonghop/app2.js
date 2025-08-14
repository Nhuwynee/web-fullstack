// ======== Helper ========
const studentNameInput = document.getElementById("studentName");
const studentList = document.getElementById("studentList");
const totalEl = document.getElementById("total");
const totalCharEl = document.getElementById("totalCharacter");
let selectedLi = null;
let sortAsc = true;

const getStudents = () => [...studentList.querySelectorAll("li")];
const resetInput = () => (studentNameInput.value = "");
const updateStats = () => {
    const total = getStudents().length;
    const totalChars = getStudents().reduce((sum, li) => {
        return sum + li.firstChild.textContent.trim().length;
    }, 0);
    totalEl.innerText = `Tổng số sinh viên: ${total}`;
    totalCharEl.innerText = `Tổng số ký tự: ${totalChars}`;
};

// ======== 1. Thêm sinh viên ========
document.getElementById("addBtn").addEventListener("click", () => {
    const name = studentNameInput.value.trim();
    if (!name) return alert("Vui lòng nhập tên để thêm!");

    studentList.insertAdjacentHTML(
        "afterbegin",
        `<li>${name} <button class="deleteBtn">Xóa</button></li>`
    );
    resetInput();
    updateStats();
});

// ======== 2. Xóa / Chọn sinh viên ========
studentList.addEventListener("click", (e) => {
    if (e.target.classList.contains("deleteBtn")) {
        e.target.parentElement.remove();
        updateStats();
    } else if (e.target.tagName === "LI") {
        selectedLi = e.target;
        studentNameInput.value = selectedLi.firstChild.textContent.trim();
    }
});

// ======== 3. Sửa sinh viên ========
document.getElementById("updateBtn").addEventListener("click", () => {
    if (!selectedLi) return alert("Vui lòng chọn một sinh viên để sửa!");
    const newName = studentNameInput.value.trim();
    if (!newName) return alert("Tên không được để trống!");

    selectedLi.firstChild.textContent = newName + " ";
    selectedLi = null;
    resetInput();
    updateStats();
});

// ======== 4. Tìm kiếm ========
document.getElementById("searchBtn").addEventListener("click", () => {
    const keyword = studentNameInput.value.trim().toLowerCase();
    if (!keyword) {
        alert("Vui lòng nhập tên để tìm kiếm!");
        getStudents().forEach((li) => (li.style.display = ""));
        return;
    }
    getStudents().forEach((li) => {
        li.style.display = li.firstChild.textContent.trim().toLowerCase().includes(keyword)
            ? ""
            : "none";
    });
});

// ======== 5. Tìm kiếm & đánh dấu ========
document.getElementById("searchBtn2").addEventListener("click", () => {
    const keyword = studentNameInput.value.trim().toLowerCase();
    if (!keyword) {
        alert("Vui lòng nhập tên để tìm kiếm!");
        getStudents().forEach((li) => (li.style.backgroundColor = ""));
        return;
    }
    let found = false;
    getStudents().forEach((li) => {
        if (li.firstChild.textContent.trim().toLowerCase().includes(keyword)) {
            li.style.backgroundColor = "yellow";
            found = true;
        } else {
            li.style.backgroundColor = "";
        }
    });
    if (!found) alert("Không tìm thấy");
    resetInput();
});

// ======== 6. Sắp xếp ========
document.querySelector(".sortBtn").addEventListener("click", () => {
    const students = getStudents().sort((a, b) => {
        const nameA = a.firstChild.textContent.trim();
        const nameB = b.firstChild.textContent.trim();
        return sortAsc ? nameA.localeCompare(nameB, "vi") : nameB.localeCompare(nameA, "vi");
    });
    studentList.innerHTML = "";
    students.forEach((li) => studentList.appendChild(li));
    sortAsc = !sortAsc;
});

// ======== 7. Đổi màu nền danh sách ========
document.getElementById("blueBtn").addEventListener("click", () => {
    studentList.style.backgroundColor = "lightblue";
});
document.getElementById("yellowBtn").addEventListener("click", () => {
    studentList.style.backgroundColor = "yellow";
});
document.getElementById("whiteBtn").addEventListener("click", () => {
    studentList.style.backgroundColor = "white";
});

// ======== 8. Hover highlight tên ========
studentList.addEventListener("mouseover", (e) => {
    if (e.target.tagName === "LI") {
        e.target.style.fontWeight = "bold";
        e.target.style.color = "red";
        e.target.style.cursor = "pointer";
    }
});
studentList.addEventListener("mouseout", (e) => {
    if (e.target.tagName === "LI") {
        e.target.style.fontWeight = "normal";
        e.target.style.color = "black";
    }
});

// ======== Khởi tạo ========
updateStats();

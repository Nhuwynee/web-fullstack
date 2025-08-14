// 1, 2, 3, 4
printTotalStudent();
totalCharacter();
inputName();
function inputName() {
    document.getElementById("addBtn").addEventListener("click", () => {
        const studentName = document.getElementById("studentName").value.trim();
        if (studentName === "") {
            alert('Vui lòng nhập tên để thêm!');
            return;
        } else {
            document.getElementById("studentList")
                .insertAdjacentHTML('afterbegin',
                                     `<li>${studentName}
                                        <button id="deleteBtn">Xóa</button>
                                     </li>`);
            
            document.getElementById("studentName").value = "";
        }
        printTotalStudent();
        totalCharacter();
    });
}

// 5
function printTotalStudent() {
    const total = document.querySelectorAll("#studentList li").length;
    document.getElementById("total").innerText = `Tổng số sinh viên: ${total}`;
}

// 6
searchStudent();
function searchStudent() {
    document.getElementById("searchBtn").addEventListener("click", () => {
        const students = document.querySelectorAll("#studentList li");
        const searchName = document.getElementById("studentName").value.trim().toLowerCase();

        if (searchName === "") {
            alert('Vui lòng nhập tên để tìm kiếm!');
            students.forEach(student => student.style.display = "");
            return;
        }

        students.forEach(student => {
            const name = student.innerText.toLowerCase();
            if (name.includes(searchName)) {
                student.style.display = "";
            } else {
                student.style.display = "none";
            }
        });
    });
}

// 7 
updateStudent();
let selectedLi = null;
function updateStudent() {
    document.getElementById("studentList").addEventListener("click", (e) => {
        if (e.target.tagName === "LI") { 
            selectedLi = e.target;
            document.getElementById("studentName").value = selectedLi.firstChild.textContent.trim();
        }
    });

    document.getElementById("updateBtn").addEventListener("click", () => {
        if (!selectedLi) {
            alert("Vui lòng chọn một sinh viên để sửa!");
            return;
        }
        const newName = document.getElementById("studentName").value.trim();
        if (newName === "") {
            alert("Tên không được để trống!");
            return;
        }
        selectedLi.firstChild.textContent = newName + " ";
        selectedLi = null;
        document.getElementById("studentName").value = "";

        totalCharacter();
    });
}

// 8.
sortStudent();
let sortAsc = true;
function sortStudent() {
    document.querySelector(".sortBtn").addEventListener("click", () => {
        const list = document.getElementById("studentList");
        const students = Array.from(list.querySelectorAll("li"));

        students.sort((a, b) => {
            const nameA = a.firstChild.textContent.trim();
            const nameB = b.firstChild.textContent.trim();
            return sortAsc ? nameA.localeCompare(nameB, 'vi') : nameB.localeCompare(nameA, 'vi');
        });

        list.innerHTML = "";
        students.forEach(li => list.appendChild(li));

        sortAsc = !sortAsc;
    });
}


// 9.
changeColor();
function changeColor() {
    document.getElementById("blueBtn").addEventListener("click", () => {
        document.getElementById("studentList").style.backgroundColor = "lightblue";
    });

    document.getElementById("yellowBtn").addEventListener("click", () => {
        document.getElementById("studentList").style.backgroundColor = "yellow";
    });

    document.getElementById("whiteBtn").addEventListener("click", () => {
        document.getElementById("studentList").style.backgroundColor = "white";
    });
}

// 10.
highlightStudentName();
function highlightStudentName() {
    document.getElementById("studentList").addEventListener("mouseover", (e) => {
        if (e.target.tagName === "LI") {
            e.target.style.fontWeight = "bold";
            e.target.style.cursor = "pointer";
            e.target.style.color = "red";
        }
    });

    document.getElementById("studentList").addEventListener("mouseout", (e) => {
        if (e.target.tagName === "LI") {
            e.target.style.fontWeight = "normal";
            e.target.style.color = "black";
        }
    });
}

// 11.
function totalCharacter() {
    const students = document.querySelectorAll("#studentList li");
    let total = 0;
    students.forEach(s => {
        const studentName = s.firstChild.textContent.trim();
        total += studentName.length;
    });
    document.getElementById("totalCharacter").innerText = `Tổng số ký tự: ${total}`;
}

// 12
searchHighlightStudent();
function searchHighlightStudent() {
    document.getElementById("searchBtn2").addEventListener("click", () => {
        const students = document.querySelectorAll("#studentList li");
        const searchName = document.getElementById("studentName").value.trim().toLowerCase();

        if (searchName === "") {
            alert('Vui lòng nhập tên để tìm kiếm!');
            students.forEach(student => student.style.backgroundColor = "");
            return;
        }

        let found = false;

        students.forEach(student => {
            const name = student.firstChild.textContent.trim().toLowerCase();
            if (name.includes(searchName)) {
                student.style.backgroundColor = "yellow";
                found = true;
            } else {
                student.style.backgroundColor = "";
            }
        });

        if (!found) {
            alert("Không tìm thấy");
        }

        document.getElementById("studentName").value = "";
    });
}

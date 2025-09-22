import { useCallback, useState } from "react";   // ⬅ cần import useState
import ToDoList from "./components/ToDoList";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import InputAdornment from "@mui/material/InputAdornment"; // ⬅ cần import
import { v4 } from 'uuid'

function App() {
  // Khai báo state cho input
  const [toDoList, setToDoList] = useState([]);
  const [textInput, setTextInput] = useState("");

  const onTextInputChange = useCallback((e) => {
    setTextInput(e.target.value);
  }, []);

    // hàm xử lý khi bấm nút Thêm
  const handleAdd = useCallback(() => {
    if (!textInput.trim()) return;

    // cập nhật state dựa vào biến hiện tại
    setToDoList([{id: v4(), name: textInput, isCompleted: false}, ...toDoList]);
    setTextInput("");
  }, [textInput, toDoList]);

  return (
    <>
      <div style={{ padding: "16px" }}>
        <h3>Danh sách cần làm</h3>
        <TextField
          fullWidth
          name="add-todo"
          placeholder="Thêm việc cần làm..."
          value={textInput}
          onChange={onTextInputChange}
          InputProps={{
            endAdornment: (
              <InputAdornment position="end">
                <Button
                  variant="contained"
                  color="primary"
                  onClick={handleAdd}
                  disabled={!textInput.trim()} // giống isDisabled
                  sx={{ ml: 1 }}
                >
                  Thêm
                </Button>
              </InputAdornment>
            ),
          }}
          sx={{ p: "2px 4px 2px" }}
        />
      </div>
      <ToDoList todoList={toDoList}/>
    </>
  );
}

export default App;

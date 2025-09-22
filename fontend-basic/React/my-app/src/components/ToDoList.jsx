import React from "react";
import ToDo from "./ToDo";

export default function ToDoList({ todoList }) {
  return (
    <>
      {todoList.map(todo => (
        <ToDo key={todo.id} todo={todo} />
      ))}
        </>
    )
}
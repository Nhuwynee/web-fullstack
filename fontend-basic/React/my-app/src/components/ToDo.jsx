import Button from '@mui/material/Button'
import React from 'react'
import styled from 'styled-components'
import CheckIcon from "@mui/icons-material/Check";

const ButtonStyled = styled(Button)`
    margin-top: 5px;
`;
export default function ToDo({ todo }) {
  return (
    <ButtonStyled
      fullWidth
      variant="outlined"
      endIcon={<CheckIcon sx={{ color: "#4fff4f" }} />} // giống iconAfter
      sx={{ mt: 1, textAlign: "left" }}
    >
      {todo.name}
    </ButtonStyled>
  );
}


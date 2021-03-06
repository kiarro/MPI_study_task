//Исполнитель заявок

import { StrictMode } from "react";
import './WorkerMain.css'
import { createRoot } from "react-dom/client";

import { styled } from "@mui/material/styles";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import { Button, TextField, List } from "@mui/material";

import { useNavigate } from "react-router-dom";
import { useState, useEffect } from 'react';

export default function App()
{

    const history = useNavigate();


    const profileClick = async () => {
        history("/users/current");
    };

    const exitClick = async () => {
        try {
            const response = await fetch('http://localhost:8080/users/logout', {
                method: 'POST',
                body: JSON.stringify({
                    value: "exit"
                }),
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            if (!response.ok) {
                throw new Error(`Error! status: ${response.status}`);
            }

            // const result = await response.json();

            // console.log('result is: ', JSON.stringify(result, null, 4));

            history("/login");
        } catch (err) {
        } finally {
        }
    };

return (
    <main>
        <Box sx={{ flexGrow: 1, width: 400 }} margin="10px" padding="10px">
            <Button variant="contained" onClick={() => profileClick()}>Профиль</Button>
        </Box>
        <Box sx={{ flexGrow: 1, width: 400 }} margin="10px" padding="10px">
            <Button variant="contained" >Заявки к принятию</Button>
        </Box>
        <Box sx={{ flexGrow: 1, width: 400 }} margin="10px" padding="10px">
            <Button variant="contained" onClick={() => exitClick()}>Выйти</Button>
        </Box>
    </main>
);
}
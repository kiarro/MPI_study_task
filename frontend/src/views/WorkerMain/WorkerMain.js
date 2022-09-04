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

function ApplicationsList() {

    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);

    // Примечание: пустой массив зависимостей [] означает, что
    // этот useEffect будет запущен один раз
    // аналогично componentDidMount()
    useEffect(() => {
        fetch("/api/applications")
            .then(res => res.json())
            .then(
                (result) => {
                    setIsLoaded(true);
                    setItems(result);
                },
                // Примечание: важно обрабатывать ошибки именно здесь, а не в блоке catch(),
                // чтобы не перехватывать исключения из ошибок в самих компонентах.
                (error) => {
                    setIsLoaded(true);
                    setError(error);
                }
            )
    }, [])

    if (error) {
        return <div>Ошибка: {error.message}</div>;
    } else if (!isLoaded) {
        return <div>Загрузка...</div>;
    } else {
        return (
            <div>
                {items.map(item => (
                    <Box sx={{ m: 0, p: 0, border: "1px dashed" }}>
                        <Grid container spacing={2}>
                            <Grid item xs={1}>
                                <Box m={1} display="flex" justifyContent="center">
                                    <Item>item.id</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={2}>
                                <Box m={1} display="flex" justifyContent="left">
                                    <Item>item.type</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={4}>
                                <Box m={1} display="flex" justifyContent="left">
                                    <Item>item.creatorId</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={2}>
                                <Box m={1} display="flex" justifyContent="center">
                                    <Item>item.status</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={3}>
                                <Box m={1} display="flex" justifyContent="left">
                                    <Item>item.executionGroup</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={3}>
                                <Box m={1} display="flex" justifyContent="left">
                                    <Item>item.experimentId</Item>
                                </Box>
                            </Grid>
                        </Grid>
                    </Box>
                ))}
            </div>
        );
    }

}

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

    const applicationsClick = async () => {
        history("/worker/apps-to-get");
    };

return (
    <main>
        <Box sx={{ flexGrow: 1, width: 400 }} margin="10px" padding="10px">
            <Button variant="contained" onClick={() => profileClick()}>Профиль</Button>
        </Box>
        <Box sx={{ flexGrow: 1, width: 400 }} margin="10px" padding="10px">
            <Button variant="contained" onClick={() => applicationsClick()} >Принять новую заявку</Button>
        </Box>
        <ApplicationsList></ApplicationsList>
        <Box sx={{ flexGrow: 1, width: 400 }} margin="10px" padding="10px">
            <Button variant="contained" onClick={() => exitClick()}>Выйти</Button>
        </Box>
    </main>
);
}
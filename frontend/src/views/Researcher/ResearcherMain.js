import { StrictMode } from "react";
import { createRoot } from "react-dom/client";

import { styled } from "@mui/material/styles";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import { Button, TextField, List } from "@mui/material";

import { useNavigate } from "react-router-dom";
import { useState, useEffect } from 'react';

const rootElement = document.getElementById("root");
const root = createRoot(rootElement);

const Item = styled(Button)(({ theme }) => ({
    padding: theme.spacing(2),
    textAlign: "left"
}));

export default function App() {

    const history = useNavigate();

    const [items, setItems] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/experiments/current")
            .then(res => res.json())
            .then(
                (result) => {
                    setItems(result);
                },
                // Примечание: важно обрабатывать ошибки именно здесь, а не в блоке catch(),
                // чтобы не перехватывать исключения из ошибок в самих компонентах.
                (error) => {
                }
            )
    }, [])

    const newExperimentClick = async () => {
        history("/experiments/new");
    };

    const profileClick = async () => {
        history("/users/current");
    };

    const expClick = async (id) => {
        history("/experiments/"+id);
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
                <Button variant="contained" onClick={() => profileClick()}>User profile</Button>
            </Box>
            <Box sx={{ flexGrow: 1, width: 400 }} margin="10px" padding="10px">
                <Button variant="contained" onClick={() => newExperimentClick()}>Creare new experiment</Button>
            </Box>
            <Box sx={{ flexGrow: 1, width: 400 }} margin="10px" padding="10px">
                <Button variant="contained" onClick={() => exitClick()}>Exit</Button>
            </Box>
            <div>
                <Box>
                    <Item>Applications</Item>
                </Box>
            <Box sx={{ m: 0, p: 0, border: "1px dashed" }}>
                    <Grid container spacing={2}>
                        <Grid item xs={2}>
                            <Box m={1} display="flex" justifyContent="center">
                                <Item>id</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={5}>
                            <Box m={1} display="flex" justifyContent="center">
                                <Item>title</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={5}>
                            <Box m={1} display="flex" justifyContent="left">
                                <Item>status</Item>
                            </Box>
                        </Grid>
                    </Grid>
                </Box>
            {items.map(item => (
                <Box sx={{ m: 0, p: 0, border: "1px dashed" }}>
                    <Grid container spacing={2}>
                        <Grid item xs={2}>
                            <Box m={1} display="flex" justifyContent="center">
                                <Item><Button onClick={() => expClick(item.id)}>{item.id}</Button></Item>
                            </Box>
                        </Grid>
                        <Grid item xs={5}>
                            <Box m={1} display="flex" justifyContent="center">
                                <Item>{item.title}</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={5}>
                            <Box m={1} display="flex" justifyContent="left">
                                <Item>{item.status}</Item>
                            </Box>
                        </Grid>
                    </Grid>
                </Box>
            ))}
            </div>
        </main>
    );
}

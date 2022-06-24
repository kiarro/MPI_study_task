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

    const newUserClick = async () => {
        history("/users/new");
    };

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
                <Button variant="contained" onClick={() => profileClick()}>User profile</Button>
            </Box>
            <Box sx={{ flexGrow: 1, width: 400 }} margin="10px" padding="10px">
                <Button variant="contained" onClick={() => newUserClick()}>Creare new user</Button>
            </Box>
            <UserList></UserList>
            <Box sx={{ flexGrow: 1, width: 400 }} margin="10px" padding="10px">
                <Button variant="contained" onClick={() => exitClick()}>Exit</Button>
            </Box>
        </main>
    );
}

function UserList() {
    const history = useNavigate();

    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);

    // Примечание: пустой массив зависимостей [] означает, что
    // этот useEffect будет запущен один раз
    // аналогично componentDidMount()
    useEffect(() => {
        fetch("http://localhost:8080/users")
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

    const userClick = async (id) => {
        history("/users/"+id);
    };

    if (error) {
        return <div>Ошибка: {error.message}</div>;
    } else if (!isLoaded) {
        return <div>Загрузка...</div>;
    } else {
        return (
            <div>
                <Box sx={{ m: 0, p: 0, border: "1px dashed" }}>
                    <Grid container spacing={2}>
                        <Grid item xs={1}>
                            <Box m={1} display="flex" justifyContent="center">
                                <Item>id</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={3}>
                            <Box m={1} display="flex" justifyContent="left">
                                <Item>firstName</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={3}>
                            <Box m={1} display="flex" justifyContent="left">
                                <Item>lastName</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={3}>
                            <Box m={1} display="flex" justifyContent="center">
                                <Item>username</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={2}>
                            <Box m={1} display="flex" justifyContent="left">
                                <Item>role</Item>
                            </Box>
                        </Grid>
                    </Grid>
                </Box>
                {items.map(item => (
                    <Box sx={{ m: 0, p: 0, border: "1px dashed" }}>
                        <Grid container spacing={2}>
                            <Grid item xs={1}>
                                <Box m={1} display="flex" justifyContent="center">
                                    <Item><Button onClick={() => userClick(item.id)}>{item.id}</Button></Item>
                                </Box>
                            </Grid>
                            <Grid item xs={3}>
                                <Box m={1} display="flex" justifyContent="left">
                                    <Item>{item.firstName}</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={3}>
                                <Box m={1} display="flex" justifyContent="left">
                                    <Item>{item.lastName}</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={3}>
                                <Box m={1} display="flex" justifyContent="center">
                                    <Item>{item.username}</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={2}>
                                <Box m={1} display="flex" justifyContent="left">
                                    <Item>{item.role}</Item>
                                </Box>
                            </Grid>
                        </Grid>
                    </Box>
                ))}
            </div>
        );
    }

}

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

const Item = styled(Button)(({ theme }) => ({
    padding: theme.spacing(2),
    textAlign: "left"
}));

function ApplicationsList() {

    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);

    // Примечание: пустой массив зависимостей [] означает, что
    // этот useEffect будет запущен один раз
    // аналогично componentDidMount()
    function reload() {
        fetch("http://localhost:8080/applications?status=ACCEPTED")
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
    }

    const report = async () => {
        history("add_rep");
    };

    useEffect(() => {
        // fetch("http://localhost:8080/applications")
        //     .then(res => res.json())
        //     .then(
        //         (result) => {
        //             setIsLoaded(true);
        //             setItems(result);
        //         },
        //         // Примечание: важно обрабатывать ошибки именно здесь, а не в блоке catch(),
        //         // чтобы не перехватывать исключения из ошибок в самих компонентах.
        //         (error) => {
        //             setIsLoaded(true);
        //             setError(error);
        //         }
        //     )
        reload();
    }, [])

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
                            <Grid item xs={2}>
                                <Box m={1} display="flex" justifyContent="left">
                                    <Item>type</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={1}>
                                <Box m={1} display="flex" justifyContent="left">
                                    <Item>creatorId</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={2}>
                                <Box m={1} display="flex" justifyContent="center">
                                    <Item>status</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={3}>
                                <Box m={1} display="flex" justifyContent="left">
                                    <Item>Actions</Item>
                                </Box>
                            </Grid>
                        </Grid>
                    </Box>
                {items.map(item => (
                    <Box sx={{ m: 0, p: 0, border: "1px dashed" }}>
                        <Grid container spacing={2}>
                            <Grid item xs={1}>
                                <Box m={1} display="flex" justifyContent="center">
                                    <Item>{item.id}</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={2}>
                                <Box m={1} display="flex" justifyContent="left">
                                    <Item>{item.type}</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={1}>
                                <Box m={1} display="flex" justifyContent="left">
                                    <Item>{item.creatorId}</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={2}>
                                <Box m={1} display="flex" justifyContent="center">
                                    <Item>{item.status}</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={3}>
                                <Box m={1} display="flex" justifyContent="center">
                                    <Item><Button onClick={() => report(item.id)}>Report</Button></Item>
                                </Box>
                            </Grid>
                        </Grid>
                    </Box>
                ))}
            </div>
        );
    }
}

function ApplicationsListToGet() {

    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);

    // Примечание: пустой массив зависимостей [] означает, что
    // этот useEffect будет запущен один раз
    // аналогично componentDidMount()
    function reload() {
        fetch("http://localhost:8080/applications?status=APPROVED")
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
    }

    useEffect(() => {
        reload()
    }, [])
    
    const accept = async (aid) => {
        try {
            const response = await fetch("http://localhost:8080/applications/" + String(aid) + "/accept", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            if (!response.ok) {
                throw new Error(`Error! status: ${response.status}`);
            }
            
            await reload();

            await ApplicationsList.reload();
            
        } catch (err) {
        } finally {
        }
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
                            <Grid item xs={2}>
                                <Box m={1} display="flex" justifyContent="left">
                                    <Item>type</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={1}>
                                <Box m={1} display="flex" justifyContent="left">
                                    <Item>creatorId</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={2}>
                                <Box m={1} display="flex" justifyContent="center">
                                    <Item>status</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={3}>
                                <Box m={1} display="flex" justifyContent="left">
                                    <Item>Action</Item>
                                </Box>
                            </Grid>
                        </Grid>
                    </Box>
                {items.map(item => (
                    <Box sx={{ m: 0, p: 0, border: "1px dashed" }}>
                        <Grid container spacing={2}>
                            <Grid item xs={1}>
                                <Box m={1} display="flex" justifyContent="center">
                                    <Item>{item.id}</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={2}>
                                <Box m={1} display="flex" justifyContent="left">
                                    <Item>{item.type}</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={1}>
                                <Box m={1} display="flex" justifyContent="left">
                                    <Item>{item.creatorId}</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={2}>
                                <Box m={1} display="flex" justifyContent="center">
                                    <Item>{item.status}</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={3}>
                                <Box m={1} display="flex" justifyContent="center">
                                    <Item><Button onClick={() => accept(item.id)}>Accept</Button></Item>
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

return (
    <main>
        <Box sx={{ flexGrow: 1, width: 400 }} margin="10px" padding="10px">
            <Button variant="contained" onClick={() => profileClick()}>Профиль</Button>
        </Box>
        {/* <Box sx={{ flexGrow: 1, width: 400 }} margin="10px" padding="10px">
            <Button variant="contained" onClick={() => applicationsClick()} >Принять новую заявку</Button>
        </Box> */}
        <Box>
            <Item>Accepted applications</Item>
        </Box>
        <ApplicationsList></ApplicationsList>
        <Box>
            <Item>Applications to accept</Item>
        </Box>
        <ApplicationsListToGet></ApplicationsListToGet>
        <Box sx={{ flexGrow: 1, width: 400 }} margin="10px" padding="10px">
            <Button variant="contained" onClick={() => exitClick()}>Выйти</Button>
        </Box>
    </main>
);
}
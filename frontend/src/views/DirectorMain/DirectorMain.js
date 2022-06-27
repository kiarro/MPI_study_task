import { StrictMode } from "react";
import './DirectorMain.css'
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

    const checkApplicationClick = async () => {
        history("/applications/current");
    };

    const approveApplicationClick = async () => {
        history("/applications/approved");
    };

    const rejectApplicationClick = async () => {
        history("/applications/rejected");
    };

    const checkExperimentClick = async () => {
        history("/experiments/current");
    };

    const approveExperimentClick = async () => {
        history("/experiments/approved");
    };

    const rejectExperimentClick = async () => {
        history("/experiments/rejected");
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
                <Button variant="contained" onClick={() => checkApplicationClick()}>Посмотреть заявку</Button>
            </Box>
            <ApplicationList></ApplicationList>
            <Box sx={{ flexGrow: 1, width: 400 }} margin="10px" padding="10px">
                <Button variant="contained" onClick={() => approveApplicationClick()}>Одобрить заявку</Button>
            </Box>
            <Box sx={{ flexGrow: 1, width: 400 }} margin="10px" padding="10px">
                <Button variant="contained" onClick={() => checkExperimentClick()}>Посмотреть эксперимент</Button>
            </Box>
            <ExperimentList></ExperimentList>
            <Box sx={{ flexGrow: 1, width: 400 }} margin="10px" padding="10px">
                <Button variant="contained" onClick={() => approveExperimentClick()}>Утверждить закрытие эксперимента</Button>
            </Box>
            <Box sx={{ flexGrow: 1, width: 400 }} margin="10px" padding="10px">
                <Button variant="contained" onClick={() => rejectExperimentClick()}>Отклонить закрытие эксперимента</Button>
            </Box>
            <Box sx={{ flexGrow: 1, width: 400 }} margin="10px" padding="10px">
                <Button variant="contained" onClick={() => rejectApplicationClick()}>Отклонить заявку</Button>
            </Box>
            <Box sx={{ flexGrow: 1, width: 400 }} margin="10px" padding="10px">
                <Button variant="contained" onClick={() => exitClick()}>Выйти</Button>
            </Box>
        </main>
    );
}

function ExperimentList() {
    const history = useNavigate();

    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);

    // Примечание: пустой массив зависимостей [] означает, что
    // этот useEffect будет запущен один раз
    // аналогично componentDidMount()
    useEffect(() => {
        fetch("http://localhost:8080/experiments")
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
        history("/experiments/"+id);
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
                                <Item>title</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={3}>
                            <Box m={1} display="flex" justifyContent="left">
                                <Item>description</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={3}>
                            <Box m={1} display="flex" justifyContent="center">
                                <Item>creationTime</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={2}>
                            <Box m={1} display="flex" justifyContent="left">
                                <Item>researchGroup</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={2}>
                            <Box m={1} display="flex" justifyContent="left">
                                <Item>state</Item>
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
                                    <Item>{item.title}</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={3}>
                                <Box m={1} display="flex" justifyContent="left">
                                    <Item>{item.description}</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={3}>
                                <Box m={1} display="flex" justifyContent="center">
                                    <Item>{item.creationTime}</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={2}>
                                <Box m={1} display="flex" justifyContent="left">
                                    <Item>{item.researcherGroup}</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={2}>
                                <Box m={1} display="flex" justifyContent="left">
                                    <Item>{item.state}</Item>
                                </Box>
                            </Grid>
                        </Grid>
                    </Box>
                ))}
            </div>
        );
    }
}

function ApplicationList() {
    const history = useNavigate();

    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);

    // Примечание: пустой массив зависимостей [] означает, что
    // этот useEffect будет запущен один раз
    // аналогично componentDidMount()
    useEffect(() => {
        fetch("http://localhost:8080/applications")
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
        history("/applications/"+id);
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
                                <Item>type</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={3}>
                            <Box m={1} display="flex" justifyContent="left">
                                <Item>creatorId</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={3}>
                            <Box m={1} display="flex" justifyContent="center">
                                <Item>status</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={2}>
                            <Box m={1} display="flex" justifyContent="left">
                                <Item>executionGroupId</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={2}>
                            <Box m={1} display="flex" justifyContent="left">
                                <Item>experimentId</Item>
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
                                    <Item>{item.type}</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={3}>
                                <Box m={1} display="flex" justifyContent="left">
                                    <Item>{item.creatorId}</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={3}>
                                <Box m={1} display="flex" justifyContent="left">
                                    <Item>{item.status}</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={2}>
                                <Box m={1} display="flex" justifyContent="center">
                                    <Item>{item.executionGroup.id}</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={2}>
                                <Box m={1} display="flex" justifyContent="left">
                                    <Item>{item.experimentId}</Item>
                                </Box>
                            </Grid>
                        </Grid>
                    </Box>
                ))}
            </div>
        );
    }
}

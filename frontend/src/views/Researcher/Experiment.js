import { StrictMode } from "react";
import { createRoot } from "react-dom/client";

import { styled } from "@mui/material/styles";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import { Button, TextField, List } from "@mui/material";

import { useState, useEffect } from 'react';
import { useParams, useNavigate } from "react-router-dom";

const rootElement = document.getElementById("root");
const root = createRoot(rootElement);

const Item = styled(Button)(({ theme }) => ({
    padding: theme.spacing(2),
    textAlign: "left"
}));



function App() {
    const history = useNavigate();

    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);

    const [exp, setExp] = useState('');

    const { id } = useParams();

    useEffect(() => {
        fetch("http://localhost:8080/experiments/" + id)
            .then(res => res.json())
            .then(
                (result) => {
                    setIsLoaded(true);
                    setExp(result);
                },
                // Примечание: важно обрабатывать ошибки именно здесь, а не в блоке catch(),
                // чтобы не перехватывать исключения из ошибок в самих компонентах.
                (error) => {
                    setIsLoaded(true);
                    setError(error);
                }
            )
    }, [])


    const backClick = async () => {
        history(-1);
    };

    const finishExp = async () => {
        try {
            const response = await fetch('http://localhost:8080/experiments/'+String(id)+'/finishing', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            if (!response.ok) {
                throw new Error(`Error! status: ${response.status}`);
            }
            history(-1);
            
        } catch (err) {
        } finally {
        }
    };

    const createApp = async () => {
        history("add_app");
    };

    const createRep = async () => {
        history("add_rep");
    };

    var b_disabled = (exp.status != "IN_PROGRESS")?"disabled":"";

    if (error) {
        return <div>Ошибка: {error.message}</div>;
    } else if (!isLoaded) {
        return <div>Загрузка...</div>;
    } else {
        return (
            <main>
                <Box sx={{ flexGrow: 1 }} margin="10px" padding="10px">
                    <Grid container spacing={2}>
                        <Grid item xs={3}>
                            <Box>
                                <Item>Идентификатор:</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={3}>
                            <Box>
                                <TextField disabled value={exp.id} fullWidth></TextField>
                            </Box>
                        </Grid>
                        <Grid item xs={3}>
                            <Box>
                                <Item>Команда:</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={3}>
                            <Box>
                                <TextField disabled value={exp.researchGroup.id} fullWidth></TextField>
                            </Box>
                        </Grid>
                        <Grid item xs={3}>
                            <Box>
                                <Item>Состояние:</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={9}>
                            <Box>
                                <TextField disabled value={exp.status} fullWidth></TextField>
                            </Box>
                        </Grid>
                        <Grid item xs={3}>
                            <Box>
                                <Item>Заголовок:</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={9}>
                            <Box>
                                <TextField
                                    disabled
                                    value={exp.title}
                                    fullWidth
                                ></TextField>
                            </Box>
                        </Grid>
                        <Grid item xs={12} marginBottom="0px" paddingBottom="0px">
                            <Box>
                                <Item>Описание:</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={12} marginTop="0px" paddingTop="0px">
                            <Box>
                                <TextField
                                    disabled
                                    fullWidth
                                    multiline="true"
                                    rows="6"
                                    value={exp.description}
                                ></TextField>
                            </Box>
                        </Grid>
                        <Grid item xs={12} marginBottom="0px" paddingBottom="0px">
                            <Box>
                                <Item>Заявки и отчеты:</Item>
                            </Box>
                        </Grid>
                        <Grid container spacing={2} margin="0px">
                            <Grid item xs={1}>
                                <Box m={1} display="flex" justifyContent="center">
                                    <Item>id</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={2}>
                                <Box m={1} display="flex" justifyContent="center">
                                    <Item>Тип</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={4}>
                                <Box m={1} display="flex" justifyContent="center">
                                    <Item>Заголовок</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={2}>
                                <Box m={1} display="flex" justifyContent="center">
                                    <Item>Дата</Item>
                                </Box>
                            </Grid>
                        </Grid>
                        <Grid item xs={12} marginTop="0px" paddingTop="0px">
                            <AppList id={id}></AppList>
                        </Grid>
                    </Grid>
                    <Grid container spacing={2}>
                        <Grid item xs={6}>
                            <Box m={1} display="flex" justifyContent="flex-start">
                                <Button disabled={b_disabled} onClick={() => createRep()} variant="contained">Создать отчет</Button>
                            </Box>
                        </Grid>
                        <Grid item xs={6}>
                            <Box m={1} display="flex" justifyContent="flex-end">
                                <Button disabled={b_disabled} onClick={() => createApp()} variant="contained">Создать заявку</Button>
                            </Box>
                        </Grid>
                    </Grid>
                    <Grid container spacing={2}>
                        <Grid item xs={6}>
                            <Box m={1} display="flex" justifyContent="flex-start">
                                <Button disabled={b_disabled} onClick={() => finishExp()} variant="contained">Завершить эксперимент</Button>
                            </Box>
                        </Grid>
                        <Grid item xs={6}>
                            <Box m={1} display="flex" justifyContent="flex-end">
                                <Button variant="contained" onClick={() => backClick()}>Назад</Button>
                            </Box>
                        </Grid>
                    </Grid>
                </Box>
            </main>
        );
    }
}

function AppList(input) {
    const history = useNavigate();

    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [items1, setItems1] = useState([]);

    // console.log(id.id);
    // Примечание: пустой массив зависимостей [] означает, что
    // этот useEffect будет запущен один раз
    // аналогично componentDidMount()
    useEffect(() => {
        fetch("http://localhost:8080/applications?experiment=" + input.id)
            .then(res => res.json())
            .then(
                (result) => {
                    setIsLoaded(true);
                    setItems1(result);
                },
                // Примечание: важно обрабатывать ошибки именно здесь, а не в блоке catch(),
                // чтобы не перехватывать исключения из ошибок в самих компонентах.
                (error) => {
                    setIsLoaded(true);
                    setError(error);
                }
            )
    }, [])

    const [items2, setItems2] = useState([]);
    useEffect(() => {
        fetch("http://localhost:8080/reports?experiment=" + input.id)
            .then(res => res.json())
            .then(
                (result) => {
                    setIsLoaded(true);
                    setItems2(result);
                },
                // Примечание: важно обрабатывать ошибки именно здесь, а не в блоке catch(),
                // чтобы не перехватывать исключения из ошибок в самих компонентах.
                (error) => {
                    setIsLoaded(true);
                    setError(error);
                }
            )
    }, [])
    
    const reportClick = async (id) => {
        history("/reports/" + id);
    };

    const appClick = async (id) => {
        history("/applications/" + id);
    }

    if (error) {
        return <div>Ошибка: {error.message}</div>;
    } else if (!isLoaded) {
        return <div>Загрузка...</div>;
    } else {
        return (
            <List sx={{ m: 0, p: 0 }}>
                {items1.concat(items2).map(item => (
                    <Box sx={{ m: 0, p: 0, border: "1px dashed" }}>
                        <Grid container spacing={2}>
                            <Grid item xs={1}>
                                <Box m={1} display="flex" justifyContent="center">
                                    <Item><Button onClick={() => !item.type?reportClick(item.id):appClick(item.id)}>{item.id}</Button></Item>
                                </Box>
                            </Grid>
                            <Grid item xs={2}>
                                <Box m={1} display="flex" justifyContent="left">
                                    <Item>{!item.type?"REPORT":"APPLICATION"}</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={4}>
                                <Box m={1} display="flex" justifyContent="left">
                                    <Item>{item.title}</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={2}>
                                <Box m={1} display="flex" justifyContent="center">
                                    <Item>{item.creationDate}</Item>
                                </Box>
                            </Grid>
                        </Grid>
                    </Box>
                ))}
            </List>
        );
    }

}

export default App;
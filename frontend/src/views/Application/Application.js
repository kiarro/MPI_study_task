import { StrictMode } from "react";
import './Application.css'
import { createRoot } from "react-dom/client";
import { styled } from "@mui/material/styles";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import { Button, TextField, List } from "@mui/material";

import { useState, useEffect } from 'react';
import { useParams, useNavigate } from "react-router-dom";
import {Component} from "@types/react";

const rootElement = document.getElementById("root");
const root = createRoot(rootElement);

const Item = styled(Button)(({ theme }) => ({
    padding: theme.spacing(2),
    textAlign: "left"
}));


class App extends Component {
    render() {
        const history = useNavigate();

        const [error, setError] = useState(null);
        const [isLoaded, setIsLoaded] = useState(false);
        const [items, setItems] = useState([]);

        const [exp, setApp] = useState('');

        const {id} = useParams();

        useEffect(() => {
            fetch("http://localhost:8080/applications/" + id)
                .then(res => res.json())
                .then(
                    (result) => {
                        setIsLoaded(true);
                        setApp(result);
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

        const newAppClick = async () => {
            history("/applications/");
        };

        if (error) {
            return <div>Ошибка: {error.message}</div>;
        } else if (!isLoaded) {
            return <div>Загрузка...</div>;
        } else {
            return (
                <main>
                    <Box sx={{flexGrow: 1}} margin="10px" padding="10px">
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
                                    <Item>Исполнители:</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={3}>
                                <Box>
                                    <TextField disabled value={exp.executionGroup.id} fullWidth></TextField>
                                </Box>
                            </Grid>
                            <Grid item xs={3}>
                                <Box>
                                    <Item>Тип заявки:</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={9}>
                                <Box>
                                    <TextField
                                        disabled
                                        value={exp.type}
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
                                <Grid item xs={3}>
                                    <Box m={1} display="flex" justifyContent="center">
                                        <Item>Исследователь</Item>
                                    </Box>
                                </Grid>
                            </Grid>
                            <Grid item xs={12} marginTop="0px" paddingTop="0px">
                                <AppList id={id}></AppList>
                            </Grid>
                        </Grid>
                        <Grid container spacing={2}>
                            <Grid item xs={6}>
                                <Box m={1} display="flex" justifyContent="flex-end">
                                    <Button variant="contained" onClick={() => newAppClick()}>Создать заявку</Button>
                                </Box>
                            </Grid>
                        </Grid>
                        <Grid container spacing={2}>
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
}

function AppList(input) {

    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);

    // console.log(id.id);
    // Примечание: пустой массив зависимостей [] означает, что
    // этот useEffect будет запущен один раз
    // аналогично componentDidMount()
    useEffect(() => {
        fetch("http://localhost:8080/applications?application=" + input.id)
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
            <List sx={{ m: 0, p: 0 }}>
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
                                    <Item>item.description</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={2}>
                                <Box m={1} display="flex" justifyContent="center">
                                    <Item>item.creationDate</Item>
                                </Box>
                            </Grid>
                            <Grid item xs={3}>
                                <Box m={1} display="flex" justifyContent="left">
                                    <Item>item.creator.id</Item>
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
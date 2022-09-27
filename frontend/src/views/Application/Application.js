import { StrictMode } from "react";
import './Application.css'
import { createRoot } from "react-dom/client";
import { styled } from "@mui/material/styles";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import { Button, TextField, List, FormControl, Select, InputLabel, MenuItem } from "@mui/material";

import TechApplication from './TechApplication';
import AnalysisApplication from './AnalysisApplication';

import { useState, useEffect } from 'react';
import { useParams, useNavigate } from "react-router-dom";

const Item = styled(Button)(({ theme }) => ({
    padding: theme.spacing(2),
    textAlign: "left"
}));

export default function App() {
    const history = useNavigate();

    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);

    const [team, setTeam] = useState('');
    const [type, setType] = useState('');
    const [description, setDescription] = useState('');

    const [app_, setApp_] = useState({});

    const { id } = useParams();

    const backClick = async () => {
        history(-1);
    };

    useEffect(() => {
        fetch("http://localhost:8080/applications/" + id)
            .then(res => res.json())
            .then(
                (result) => {
                    setIsLoaded(true);
                    setType(result.type);
                    setDescription(result.description);
                    setApp_(result);
                },
                // Примечание: важно обрабатывать ошибки именно здесь, а не в блоке catch(),
                // чтобы не перехватывать исключения из ошибок в самих компонентах.
                (error) => {
                    setIsLoaded(true);
                    setError(error);
                }
            )
    }, [])

    const specific_part = () => {
        switch (type) {
            case "TECHNIC": return (<TechApplication disabled={true}></TechApplication>);
            case "ANALYSIS": return (<AnalysisApplication disabled={true} is_card={true} application={app_}></AnalysisApplication>);
            default: return (<Item>{type}</Item>);
        }
    }

    // console.log(id);
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
                                <Item>Тип заявки:</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={9}>
                            <Box>
                                <TextField disabled
                                    value={type}
                                    fullWidth
                                ></TextField>
                            </Box>
                        </Grid>
                        <Grid item xs={3} marginBottom="0px" paddingBottom="0px">
                            <Box>
                                <Item>Описание:</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={12} marginTop="0px" paddingTop="0px">
                            <Box>
                                <TextField disabled
                                    fullWidth
                                    value={description}
                                    multiline="true"
                                    rows="6"
                                ></TextField>
                            </Box>
                        </Grid>
                    </Grid>
                    {specific_part()}

                    <Grid container spacing={2}>
                        <Grid item xs={6}>
                            <Box m={1} display="flex" justifyContent="flex-start">

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
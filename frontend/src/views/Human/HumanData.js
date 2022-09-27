import * as React from "react";
import { styled } from "@mui/material/styles";
import Box from "@mui/material/Box";
import Paper from "@mui/material/Paper";
import Grid from "@mui/material/Grid";
import { Button, TextField, List } from "@mui/material";

import { useState, useEffect } from 'react';

const Item = styled(Button)(({ theme }) => ({
    padding: theme.spacing(2),
    textAlign: "left"
}));

export default function App(data) {

    const [subject_id, setSubId] = useState(data.sub_id);
    var disabled = data.disabled;

    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [subject, setSubject] = useState({});

    const [hairColor, sethairColor] = useState("");
    const [eyesColor, seteyesColor] = useState("");
    const [skinColor, setskinColor] = useState("");
    const [specials, setspecials] = useState("");

    const [weight, setWeight] = useState("");
    const [height, setHeight] = useState("");
    const [birthDate, setbirthDate] = useState("");

    const save = async () => {
        try {
            subject.hairColor = hairColor;
            subject.eyesColor = eyesColor;
            subject.skinColor = skinColor;
            subject.specials = specials;
            subject.weight = weight;
            subject.height = height;
            subject.birthDate = birthDate;

            const response = await fetch("http://localhost:8080/subjects/" + String(subject_id), {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(subject)
            });

            if (!response.ok) {
                throw new Error(`Error! status: ${response.status}`);
            }

        } catch (err) {
        } finally {
        }
    };

    useEffect(() => {
        fetch("http://localhost:8080/subjects/" + subject_id)
            .then(res => res.json())
            .then(
                (result) => {
                    setIsLoaded(true);
                    setSubject(result);

                    sethairColor(result.hairColor)
                    seteyesColor(result.eyesColor)
                    setskinColor(result.skinColor)
                    setspecials(result.specials)

                    setWeight(result.weight)
                    setHeight(result.height)
                    setbirthDate(result.birthDate)
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
                <Grid container spacing={2}>
                    <Grid item xs={4}>
                        <Box>
                            <Item>Идентификатор:</Item>
                        </Box>
                    </Grid>
                    <Grid item xs={6}>
                        <Box>
                            <TextField disabled={true} fullWidth
                                value={subject.id}></TextField>
                        </Box>
                    </Grid>
                    <Grid item xs={4}>
                        <Box>
                            <Item>Имя:</Item>
                        </Box>
                    </Grid>
                    <Grid item xs={6}>
                        <Box>
                            <TextField disabled={true} fullWidth
                                value={subject.name}></TextField>
                        </Box>
                    </Grid>
                </Grid>

                <Box marginTop="20px" marginBottom="0px">
                    <Item>Внешние данные:</Item>
                </Box>
                <Grid container spacing={1} margin="0px">
                    <Grid item xs={4}>
                        <Box>
                            <Item>Цвет волос:</Item>
                        </Box>
                    </Grid>
                    <Grid item xs={7}>
                        <Box>
                            <TextField disabled={disabled} fullWidth
                                value={hairColor}
                                onChange={(e) => sethairColor(e.target.value)}></TextField>
                        </Box>
                    </Grid>
                    <Grid item xs={4}>
                        <Box>
                            <Item>Цвет глаз:</Item>
                        </Box>
                    </Grid>
                    <Grid item xs={7}>
                        <Box>
                            <TextField disabled={disabled} fullWidth
                                value={eyesColor}
                                onChange={(e) => seteyesColor(e.target.value)}></TextField>
                        </Box>
                    </Grid>
                    <Grid item xs={4}>
                        <Box>
                            <Item>Цвет кожи:</Item>
                        </Box>
                    </Grid>
                    <Grid item xs={7}>
                        <Box>
                            <TextField disabled={disabled} fullWidth
                                value={skinColor}
                                onChange={(e) => setskinColor(e.target.value)}></TextField>
                        </Box>
                    </Grid>
                    <Grid item xs={4}>
                        <Box>
                            <Item>Особые приметы:</Item>
                        </Box>
                    </Grid>
                    <Grid item xs={7}>
                        <Box>
                            <TextField
                                disabled={disabled}
                                fullWidth
                                value={specials}
                                onChange={(e) => setspecials(e.target.value)}
                                multiline="true"
                                rows="4"
                            ></TextField>
                        </Box>
                    </Grid>
                </Grid>

                <Box marginTop="20px" marginBottom="0px">
                    <Item>Основные показатели:</Item>
                </Box>
                <Grid container spacing={1} margin="0px">
                    <Grid item xs={4}>
                        <Box>
                            <Item>Вес:</Item>
                        </Box>
                    </Grid>
                    <Grid item xs={7}>
                        <Box>
                            <TextField disabled={disabled} fullWidth
                                value={weight}
                                onChange={(e) => setWeight(e.target.value)}></TextField>
                        </Box>
                    </Grid>
                    <Grid item xs={4}>
                        <Box>
                            <Item>Рост:</Item>
                        </Box>
                    </Grid>
                    <Grid item xs={7}>
                        <Box>
                            <TextField disabled={disabled} fullWidth
                                value={height}
                                onChange={(e) => setHeight(e.target.value)}></TextField>
                        </Box>
                    </Grid>
                    <Grid item xs={4}>
                        <Box>
                            <Item>Дата рождения:</Item>
                        </Box>
                    </Grid>
                    <Grid item xs={7}>
                        <Box>
                            <TextField disabled={true} fullWidth
                                value={birthDate}
                                onChange={(e) => setbirthDate(e.target.value)}></TextField>
                        </Box>
                    </Grid>
                </Grid>
                { !disabled &&
                <Grid container spacing={2}>
                    <Grid item xs={6}>
                        <Box m={1} display="flex" justifyContent="flex-start">
                            <Button variant="contained" onClick={() => save()}>Изменить</Button>
                        </Box>
                    </Grid>
                </Grid>
                }
            </div>
        );
    }
}

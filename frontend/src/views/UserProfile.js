import * as React from "react";
import { styled } from "@mui/material/styles";
import Box from "@mui/material/Box";
import Paper from "@mui/material/Paper";
import Grid from "@mui/material/Grid";
import { Button, TextField, List } from "@mui/material";

import { useState } from 'react';


const Item = styled(Button)(({ theme }) => ({
    padding: theme.spacing(2),
    textAlign: "left"
}));

export default function App() {

    const [phone, setPhone] = useState('');
    const [email, setEmail] = useState('');
    const [about, setAbout] = useState('');

    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);

    useEffect(() => {
        fetch("/api/users/current")
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

    const sendClick = async () => {
        try {
            const response = await fetch('/users/current/update_info', {
                method: 'POST',
                body: JSON.stringify({
                    phone: phone,
                    about: about,
                    email: email
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
        } catch (err) {
        } finally {
        }
    };

    return (
        <main>
            <Box sx={{ flexGrow: 1 }} margin="10px" padding="10px">
                <Grid container spacing={2}>
                    <Grid item xs={3}>
                        <Box display="flex" justifyContent="end">
                            <Item>Идентификатор:</Item>
                        </Box>
                    </Grid>
                    <Grid item xs={3}>
                        <Box>
                            <TextField disabled="true" fullWidth>{result.id}</TextField>
                        </Box>
                    </Grid>
                    <Grid item xs={3}>
                        <Box display="flex" justifyContent="end">
                            <Item>Роль:</Item>
                        </Box>
                    </Grid>
                    <Grid item xs={3}>
                        <Box>
                            <TextField disabled="true" fullWidth>{result.role}</TextField>
                        </Box>
                    </Grid>
                </Grid>

                <Box marginTop="20px" marginBottom="0px">
                    <Item>Основные данные:</Item>
                </Box>
                <Grid container spacing={1} margin="10px" marginTop="0px" border="solid">
                    <Grid item xs={4}>
                        <Box>
                            <Item>Фамилия:</Item>
                        </Box>
                    </Grid>
                    <Grid item xs={6}>
                        <Box>
                            <TextField disabled="true" fullWidth>{result.last_name}</TextField>
                        </Box>
                    </Grid>
                    <Grid item xs={4}>
                        <Box>
                            <Item>Имя:</Item>
                        </Box>
                    </Grid>
                    <Grid item xs={6}>
                        <Box>
                            <TextField disabled="true" fullWidth>{result.first_name}</TextField>
                        </Box>
                    </Grid>
                    <Grid item xs={4}>
                        <Box>
                            <Item>Дата рождения:</Item>
                        </Box>
                    </Grid>
                    <Grid item xs={6}>
                        <Box>
                            <TextField disabled="true" fullWidth>{result.birth_date}</TextField>
                        </Box>
                    </Grid>
                    <Grid item xs={4}>
                        <Box>
                            <Item>Номер договора:</Item>
                        </Box>
                    </Grid>
                    <Grid item xs={6}>
                        <Box>
                            <TextField disabled="true" fullWidth>{result.job_agreement_number}</TextField>
                        </Box>
                    </Grid>
                </Grid>

                <Box marginTop="20px" marginBottom="0px">
                    <Item>Прочее:</Item>
                </Box>
                <Grid container spacing={1} border="solid" margin="10px" marginTop="0px">
                    <Grid item xs={4}>
                        <Box>
                            <Item>Номер телефона:</Item>
                        </Box>
                    </Grid>
                    <Grid item xs={6}>
                        <Box>
                            <TextField onChange={(e) => setPhone(e.target.value)} fullWidth>{result.phone_number}</TextField>
                        </Box>
                    </Grid>
                    <Grid item xs={4}>
                        <Box>
                            <Item>Почтовый адрес:</Item>
                        </Box>
                    </Grid>
                    <Grid item xs={6}>
                        <Box>
                            <TextField onChange={(e) => setEmail(e.target.value)}
                                fullWidth
                            >{result.email}</TextField>
                        </Box>
                    </Grid>
                    <Grid item xs={4}>
                        <Box>
                            <Item>О себе:</Item>
                        </Box>
                    </Grid>
                    <Grid item xs={6}>
                        <Box>
                            <TextField onChange={(e) => setAbout(e.target.value)}
                                fullWidth
                                multiline="true"
                                rows="4"
                            >{result.about_yourself}</TextField>
                        </Box>
                    </Grid>
                </Grid>
                <Grid container spacing={2}>
                    <Grid item xs={6}>
                        <Box m={1} display="flex" justifyContent="flex-start">
                            <Button variant="contained" onClick={() => sendClick()}>Изменить прочее</Button>
                        </Box>
                    </Grid>
                    <Grid item xs={6}>
                        <Box m={1} display="flex" justifyContent="flex-end">
                            <Button variant="contained">Назад</Button>
                        </Box>
                    </Grid>
                </Grid>
            </Box>
        </main>
    );
}

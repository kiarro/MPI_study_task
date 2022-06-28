import * as React from "react";
import { styled } from "@mui/material/styles";
import Box from "@mui/material/Box";
import Paper from "@mui/material/Paper";
import Grid from "@mui/material/Grid";
import { Button, TextField, List } from "@mui/material";

import { useState, useEffect } from 'react';
import { useNavigate } from "react-router-dom";


const Item = styled(Button)(({ theme }) => ({
    padding: theme.spacing(2),
    textAlign: "left"
}));

export default function App() {
    const history = useNavigate();

    const [phoneNumber, setPhone] = useState('');
    const [email, setEmail] = useState('');
    const [aboutYourself, setAbout] = useState('');

    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [user, setItems] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/users/current")
            .then(res => res.json())
            .then(
                (result) => {
                    setIsLoaded(true);
                    setItems(result);

                    setPhone(result.phoneNumber);
                    setEmail(result.email);
                    setAbout(result.aboutYourself);
                },
                // Примечание: важно обрабатывать ошибки именно здесь, а не в блоке catch(),
                // чтобы не перехватывать исключения из ошибок в самих компонентах.
                (error) => {
                    setIsLoaded(true);
                    setError(error);
                }
            )
    }, []);

    const backClick = async () => {
        history(-1);
    };

    const sendClick = async () => {
        try {
            user.phoneNumber = phoneNumber;
            user.email = email;
            user.aboutYourself = aboutYourself;

            const response = await fetch('http://localhost:8080/users/current', {
                method: 'PUT',
                body: JSON.stringify(user),
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

    const [password, setPassword] = useState('');
    const passChangeClick = async () => {
        try {
            user.phoneNumber = phoneNumber;
            user.email = email;
            user.aboutYourself = aboutYourself;

            const response = await fetch('http://localhost:8080/users/set_password', {
                method: 'POST',
                body: password,
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
                            <Box display="flex" justifyContent="end">
                                <Item>Идентификатор:</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={3}>
                            <Box>
                                <TextField disabled="true" value={user.id} fullWidth></TextField>
                            </Box>
                        </Grid>
                        <Grid item xs={3}>
                            <Box display="flex" justifyContent="end">
                                <Item>Роль:</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={3}>
                            <Box>
                                <TextField disabled="true" value={user.role} fullWidth></TextField>
                            </Box>
                        </Grid>
                    </Grid>
                    <Grid container spacing={2}>
                        <Grid item xs={3}>
                            <Box display="flex" justifyContent="end">
                                <Item>Группа:</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={3}>
                            <Box>
                                <TextField value={user.userGroup.id} disabled fullWidth></TextField>
                            </Box>
                        </Grid>
                    </Grid>

                    <Grid container spacing={1} margin="10px" marginTop="0px" border="solid">
                        <Grid item xs={4}>
                        <Box marginTop="20px" marginBottom="0px">
                            <Item>Изменить пароль:</Item>
                        </Box>
                        </Grid>
                        <Grid item xs={4}>
                        <Box marginTop="20px" marginBottom="0px">
                            <TextField onChange={(e) => setPassword(e.target.value)} fullWidth></TextField>
                        </Box>
                        </Grid>
                        <Grid item xs={4}>
                        <Box marginTop="20px" marginBottom="0px">
                            <Button variant="contained" onClick={() => passChangeClick()}>Изменить пароль</Button>
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
                                <TextField disabled="true" value={user.lastName} fullWidth></TextField>
                            </Box>
                        </Grid>
                        <Grid item xs={4}>
                            <Box>
                                <Item>Имя:</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={6}>
                            <Box>
                                <TextField disabled="true" value={user.firstName} fullWidth></TextField>
                            </Box>
                        </Grid>
                        <Grid item xs={4}>
                            <Box>
                                <Item>Дата рождения:</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={6}>
                            <Box>
                                <TextField disabled="true" value={user.birthDate} fullWidth></TextField>
                            </Box>
                        </Grid>
                        <Grid item xs={4}>
                            <Box>
                                <Item>Номер договора:</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={6}>
                            <Box>
                                <TextField disabled="true" value={user.jobAgreementNumber} fullWidth></TextField>
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
                                <TextField onChange={(e) => setPhone(e.target.value)}
                                    value={phoneNumber} fullWidth></TextField>
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
                                    value={email} fullWidth
                                ></TextField>
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
                                    value={aboutYourself}
                                ></TextField>
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
                                <Button variant="contained" onClick={() => backClick()}>Назад</Button>
                            </Box>
                        </Grid>
                    </Grid>
                </Box>
            </main>
        );
    }
}

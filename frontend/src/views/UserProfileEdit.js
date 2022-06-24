import * as React from "react";
import { styled } from "@mui/material/styles";
import Box from "@mui/material/Box";
import Paper from "@mui/material/Paper";
import Grid from "@mui/material/Grid";
import { Button, TextField, List } from "@mui/material";

import { useState, useEffect } from 'react';
import { useNavigate, useParams } from "react-router-dom";


const Item = styled(Button)(({ theme }) => ({
    padding: theme.spacing(2),
    textAlign: "left"
}));

export default function App() {
    const history = useNavigate();

    const { id } = useParams();

    const [phone, setPhone] = useState('');
    const [email, setEmail] = useState('');
    const [about, setAbout] = useState('');
    const [group, setGroup] = useState('');
    const [last_name, setLastName] = useState('');
    const [first_name, setFirstName] = useState('');
    const [birth_date, setBirthDate] = useState('');

    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [item, setItems] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/users/" + id)
            .then(res => res.json())
            .then(
                (result) => {
                    setIsLoaded(true);
                    setItems(result);

                    setPhone(result.phone);
                    setEmail(result.email);
                    setAbout(result.aboutYourself);

                    setFirstName(result.firstName);
                    setLastName(result.lastName);
                    setBirthDate(result.birthDate);
                    setGroup(result.group);
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
            const response = await fetch('http://localhost:8080/users/'+id, {
                method: 'PUT',
                body: JSON.stringify({
                    phoneNumber: phone,
                    about: about,
                    email: email,
                    lastName: last_name,
                    firstName: first_name,
                    birthDate: birth_date,
                    userGroup: group
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
                                <TextField disabled="true" value={item.id} fullWidth></TextField>
                            </Box>
                        </Grid>
                        <Grid item xs={3}>
                            <Box display="flex" justifyContent="end">
                                <Item>Роль:</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={3}>
                            <Box>
                                <TextField disabled="true" value={item.role} fullWidth></TextField>
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
                                <TextField onChange={(e) => setGroup(e.target.value)} value={group} fullWidth></TextField>
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
                                <TextField onChange={(e) => setLastName(e.target.value)} value={last_name} fullWidth></TextField>
                            </Box>
                        </Grid>
                        <Grid item xs={4}>
                            <Box>
                                <Item>Имя:</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={6}>
                            <Box>
                                <TextField onChange={(e) => setFirstName(e.target.value)} value={first_name} fullWidth></TextField>
                            </Box>
                        </Grid>
                        <Grid item xs={4}>
                            <Box>
                                <Item>Дата рождения:</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={6}>
                            <Box>
                                <TextField onChange={(e) => setBirthDate(e.target.value)} value={birth_date} fullWidth></TextField>
                            </Box>
                        </Grid>
                        <Grid item xs={4}>
                            <Box>
                                <Item>Номер договора:</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={6}>
                            <Box>
                                <TextField disabled="true" value={item.jobAgreementNumber} fullWidth></TextField>
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
                                    value={phone} fullWidth></TextField>
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
                                    value={about}
                                ></TextField>
                            </Box>
                        </Grid>
                    </Grid>
                    <Grid container spacing={2}>
                        <Grid item xs={6}>
                            <Box m={1} display="flex" justifyContent="flex-start">
                                <Button variant="contained" onClick={() => sendClick()}>Изменить</Button>
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

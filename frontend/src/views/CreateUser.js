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

    const [role, setRole] = useState('');
    const [last_name, setLastName] = useState('');
    const [first_name, setFirstName] = useState('');
    const [birth_date, setBirthDate] = useState('');
    const [job_agreement_number, setJobAgreementNumber] = useState('');

    const newUserClick = async () => {
        window.open("/admin");
    };

    const sendClick = async () => {
        try {
            const response = await fetch('/users/', {
                method: 'POST',
                body: JSON.stringify({
                    role: role,
                    last_name: last_name,
                    first_name: first_name,
                    birth_date: birth_date,
                    job_agreement_number: job_agreement_number,
                }),
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            if (!response.ok) {
                throw new Error(`Error! status: ${response.status}`);
            }

            const result = await response.json();

            console.log('result is: ', JSON.stringify(result, null, 4));

            window.open("/admin");
        } catch (err) {
        } finally {
        }
    };

    return (
        <Box sx={{ flexGrow: 1 }} margin="10px" padding="10px">
            <Grid container spacing={2}>
                <Grid item xs={3}>
                    <Box display="flex" justifyContent="end">
                        <Item>Роль:</Item>
                    </Box>
                </Grid>
                <Grid item xs={3}>
                    <Box>
                        <TextField onChange={(e) => setRole(e.target.value)} fullWidth></TextField>
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
                        <TextField  onChange={(e) => setLastName(e.target.value)} fullWidth></TextField>
                    </Box>
                </Grid>
                <Grid item xs={4}>
                    <Box>
                        <Item>Имя:</Item>
                    </Box>
                </Grid>
                <Grid item xs={6}>
                    <Box>
                        <TextField onChange={(e) => setFirstName(e.target.value)} fullWidth></TextField>
                    </Box>
                </Grid>
                <Grid item xs={4}>
                    <Box>
                        <Item>Дата рождения:</Item>
                    </Box>
                </Grid>
                <Grid item xs={6}>
                    <Box>
                        <TextField onChange={(e) => setBirthDate(e.target.value)} fullWidth></TextField>
                    </Box>
                </Grid>
                <Grid item xs={4}>
                    <Box>
                        <Item>Номер договора:</Item>
                    </Box>
                </Grid>
                <Grid item xs={6}>
                    <Box>
                        <TextField onChange={(e) => setJobAgreementNumber(e.target.value)} fullWidth></TextField>
                    </Box>
                </Grid>
            </Grid>
            <Grid container spacing={2}>
                <Grid item xs={6}>
                    <Box m={1} display="flex" justifyContent="flex-start">
                        <Button variant="contained" onClick={() => sendClick()}>Добавить</Button>
                    </Box>
                </Grid>
                <Grid item xs={6}>
                    <Box m={1} display="flex" justifyContent="flex-end">
                        <Button variant="contained" onClick={() => backClick()}>Назад</Button>
                    </Box>
                </Grid>
            </Grid>
        </Box>
    );
}

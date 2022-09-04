import * as React from "react";
import { styled } from "@mui/material/styles";
import Box from "@mui/material/Box";
import Paper from "@mui/material/Paper";
import Grid from "@mui/material/Grid";
import { Button, TextField, List, FormControl, Select, InputLabel, MenuItem } from "@mui/material";

import { useState } from 'react';
import { useNavigate } from "react-router-dom";


const Item = styled(Button)(({ theme }) => ({
    padding: theme.spacing(2),
    textAlign: "left"
}));

export default function App() {
    const history = useNavigate();

    const [role, setRole] = useState('');
    const [last_name, setLastName] = useState('');
    const [first_name, setFirstName] = useState('');
    const [birth_date, setBirthDate] = useState('');
    const [job_agreement_number, setJobAgreementNumber] = useState('');

    const backClick = async () => {
        history("/admin");
    };

    const sendClick = async () => {
        try {
            const response = await fetch('http://localhost:8080/users/', {
                method: 'POST',
                body: JSON.stringify({
                    role: role,
                    lastName: last_name,
                    firstName: first_name,
                    birthDate: birth_date,
                    jobAgreementNumber: job_agreement_number,
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

            history("/admin");
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
                        {/* <TextField onChange={(e) => setRole(e.target.value)} fullWidth></TextField> */}
                        <FormControl fullWidth>
                            <InputLabel id="demo-simple-select-label"></InputLabel>
                            <Select
                                labelId="demo-simple-select-label"
                                id="demo-simple-select"
                                value={role}
                                label="role"
                                onChange={(e) => setRole(e.target.value)}
                            >
                                <MenuItem value={"RESEARCHER"}>RESEARCHER</MenuItem>
                                <MenuItem value={"ADMIN"}>ADMIN</MenuItem>
                                <MenuItem value={"DIRECTOR"}>DIRECTOR</MenuItem>
                                <MenuItem value={"ANALYTIC"}>ANALYTIC</MenuItem>
                                <MenuItem value={"TECHNICIAN"}>TECHNICIAN</MenuItem>
                                <MenuItem value={"LANDER"}>LANDER</MenuItem>
                            </Select>
                        </FormControl>
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

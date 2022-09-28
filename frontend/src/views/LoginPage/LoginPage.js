import { StrictMode } from "react";
import './LoginPage.css'
import { createRoot } from "react-dom/client";

import { styled } from "@mui/material/styles";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import { Button, TextField, List } from "@mui/material";

import { useState } from 'react';

import { useNavigate } from "react-router-dom";

const rootElement = document.getElementById("root");
const root = createRoot(rootElement);

const Item = styled(Button)(({ theme }) => ({
    padding: theme.spacing(2),
    textAlign: "left"
}));




function App() {


    const [data, setData] = useState();
    const [isLoading, setIsLoading] = useState(false);
    const [err, setErr] = useState('');

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const history = useNavigate();

    const handleClick = async () => {
        setIsLoading(true);

        try {
            const response = await fetch('http://localhost:8080/users/login', {
                method: 'POST',
                body: JSON.stringify({
                    username: username,
                    password: password,
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


            switch (result.role) {
                case "ADMIN":
                    history("/admin");
                    break;
                case "RESEARCHER":
                    history("/researcher");
                    break;
                case "DIRECTOR":
                    history("/director");
                    break;
                case "ANALYTIC":
                    history("/worker/ANALYTIC")
                    break;
                case "TECHNICIAN":
                    history("/worker/TECHNICIAN")
                    break;
                case "LANDER":
                    history("/worker/LANDER")
                    break;

            }

            // if (result.role == "ADMIN") {
            //     // window.open("/admin")
            //     history("/admin");
            // } else if (result.role == "RESEARCHER") {
            //     history("/researcher")
            // }

            setData(result);
        } catch (err) {
            setErr(err.message);
        } finally {
            setIsLoading(false);
        }
    };
    return (
        <main className="LoginMain" >
            <Box sx={{ flexGrow: 1, width: 400 }} margin="auto" padding="100px">
                <Grid container spacing={0}>
                    <Grid item xs={6}>
                        <Box>
                            <Item>Логин:</Item>
                        </Box>
                    </Grid>
                    <Grid item xs={6}>
                        <Box>
                            <TextField onChange={(e) => setUsername(e.target.value)} placeholder="" fullWidth></TextField>
                        </Box>
                    </Grid>
                    <Grid item xs={6}>
                        <Box>
                            <Item>Пароль:</Item>
                        </Box>
                    </Grid>
                    <Grid item xs={6}>
                        <Box>
                            <TextField type={"password"} onChange={(e) => setPassword(e.target.value)} placeholder="" fullWidth></TextField>
                        </Box>
                    </Grid>
                </Grid>
                <Grid container spacing={2}>
                    <Grid item xs={12}>
                        <Box m={1} display="flex" justifyContent="flex-end">
                            <Button variant="contained" onClick={() => handleClick()}>Войти</Button>
                        </Box>
                    </Grid>
                </Grid>
            </Box>
        </main>
    );
}

export default App;
import { StrictMode } from "react";
import { createRoot } from "react-dom/client";

import { styled } from "@mui/material/styles";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import { Button, TextField, List } from "@mui/material";

const rootElement = document.getElementById("root");
const root = createRoot(rootElement);

const Item = styled(Button)(({ theme }) => ({
    padding: theme.spacing(2),
    textAlign: "left"
}));

export default function App() {
    const newUserClick = async () => {
        window.open("/users/new");
    };

    const exitClick = async () => {
        try {
            const response = await fetch('/users/logout', {
                method: 'POST',
                body: JSON.stringify({
                    exit: "exit"
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

            window.open("/login");
        } catch (err) {
        } finally {
        }
    };

    return (
        <main>
            <Box sx={{ flexGrow: 1, width: 400 }} margin="10px" padding="10px">
                <Button variant="contained" onClick={() => newUserClick()}>Creare new user</Button>
            </Box>
            <Box sx={{ flexGrow: 1, width: 400 }} margin="10px" padding="10px">
                <Button variant="contained" onClick={() => exitClick()}>Exit</Button>
            </Box>
        </main>
    );
}

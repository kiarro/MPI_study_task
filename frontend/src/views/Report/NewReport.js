import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { styled } from "@mui/material/styles";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import { Button, TextField, List } from "@mui/material";

import { useState, useEffect } from 'react';
import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";

const Item = styled(Button)(({ theme }) => ({
    padding: theme.spacing(2),
    textAlign: "left"
}));

export default function App() {
    const history = useNavigate();

    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);

    const [content, setContent] = useState('');
    const [title, setTitle] = useState('');

    const { id, type } = useParams();

    const backClick = async () => {
        history(-1);
    };

    const sendClick = async () => {
        try {
            const response = await fetch('http://localhost:8080/'+type+'/'+id+'/reports', {
                method: 'POST',
                body: JSON.stringify({
                    title: title,
                    content: content,
                    experiment: {id: id}
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

            history(-1);
        } catch (err) {
        } finally {
        }
    }

    // console.log(id);
    return (
        <main>
            <Box sx={{ flexGrow: 1 }} margin="10px" padding="10px">
                <Grid container spacing={2}>
                    
                    <Grid item xs={3}>
                        <Box>
                            <Item>Заголовок:</Item>
                        </Box>
                    </Grid>
                    <Grid item xs={9}>
                        <Box>
                            <TextField onChange={(e) => setTitle(e.target.value)}
                                fullWidth
                            ></TextField>
                        </Box>
                    </Grid>
                    <Grid item xs={3} marginBottom="0px" paddingBottom="0px">
                        <Box>
                            <Item>Содержание:</Item>
                        </Box>
                    </Grid>
                    <Grid item xs={12} marginTop="0px" paddingTop="0px">
                        <Box>
                            <TextField onChange={(e) => setContent(e.target.value)}
                                fullWidth
                                multiline="true"
                                rows="6"
                            ></TextField>
                        </Box>
                    </Grid>
                </Grid>
                <Grid container spacing={2}>
                    <Grid item xs={6}>
                        <Box m={1} display="flex" justifyContent="flex-start">
                            <Button variant="contained" onClick={() => sendClick()}>Сохранить отчет</Button>
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

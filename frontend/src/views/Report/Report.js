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

    const { id } = useParams();

    const backClick = async () => {
        history(-1);
    };

    useEffect(() => {
        fetch("http://localhost:8080/reports/" + id)
            .then(res => res.json())
            .then(
                (result) => {
                    setIsLoaded(true);
                    setTitle(result.title);
                    setContent(result.content);
                },
                // Примечание: важно обрабатывать ошибки именно здесь, а не в блоке catch(),
                // чтобы не перехватывать исключения из ошибок в самих компонентах.
                (error) => {
                    setIsLoaded(true);
                    setError(error);
                }
            )
    }, [])


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
                            <TextField disabled
                                value={title}
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
                            <TextField 
                                value={content}
                                fullWidth
                                disabled
                                multiline="true"
                                rows="6"
                            ></TextField>
                        </Box>
                    </Grid>
                </Grid>
                <Grid container spacing={2}>
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

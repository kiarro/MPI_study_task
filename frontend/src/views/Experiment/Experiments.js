import { StrictMode } from "react";
import { createRoot } from "react-dom/client";

import { styled } from "@mui/material/styles";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import { Button, TextField, List } from "@mui/material";

import { useState, useEffect } from 'react';

const rootElement = document.getElementById("root");
const root = createRoot(rootElement);

const Item = styled(Button)(({ theme }) => ({
    padding: theme.spacing(2),
    textAlign: "left"
}));


function App() {

    return (
        <main>
            <Button href="/experiments/new">Add experiment</Button>
            <ExpList></ExpList>
        </main>
    );
}

function ExpList() {

    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);

    // Примечание: пустой массив зависимостей [] означает, что
    // этот useEffect будет запущен один раз
    // аналогично componentDidMount()
    useEffect(() => {
        fetch("/api/experiments")
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

    if (error) {
        return <div>Ошибка: {error.message}</div>;
    } else if (!isLoaded) {
        return <div>Загрузка...</div>;
    } else {
        return (
            <div>
            {items.map(item => (
                <Box sx={{ m: 0, p: 0, border: "1px dashed" }}>
                    <Grid container spacing={2}>
                        <Grid item xs={1}>
                            <Box m={1} display="flex" justifyContent="center">
                                <Item>item.id</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={2}>
                            <Box m={1} display="flex" justifyContent="left">
                                <Item>item.type</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={4}>
                            <Box m={1} display="flex" justifyContent="left">
                                <Item>item.description</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={2}>
                            <Box m={1} display="flex" justifyContent="center">
                                <Item>item.creationDate</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={3}>
                            <Box m={1} display="flex" justifyContent="left">
                                <Item>item.creator.first_name</Item>
                            </Box>
                        </Grid>
                    </Grid>
                </Box>
            ))}
            </div>
        );
    }

}

export default App;
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
    return (
        <main>
            <Box sx={{ flexGrow: 1 }} margin="10px">
                <List>
                    <Grid container spacing={1} border="solid">
                        <Grid item xs={4}>
                            <Box>
                                <Item>Тип артефактов:</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={6}>
                            <Box>
                                <TextField fullWidth></TextField>
                            </Box>
                        </Grid>
                        <Grid item xs={4}>
                            <Box>
                                <Item>Количество:</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={6}>
                            <Box>
                                <TextField fullWidth></TextField>
                            </Box>
                        </Grid>
                        <Grid item xs={4}>
                            <Box>
                                <Item>Место высадки:</Item>
                            </Box>
                        </Grid>
                        <Grid item xs={6}>
                            <Box>
                                <TextField fullWidth></TextField>
                            </Box>
                        </Grid>
                        <Grid item xs={2}>
                            <Box
                                m={1}
                                display="flex"
                                justifyContent="center"
                                alignItems="center"
                            >
                                <Button variant="contained">Выбрать</Button>
                            </Box>
                        </Grid>
                    </Grid>
                </List>
                <Grid container spacing={2}>
                    <Grid item xs={12}>
                        <Box m={1} display="flex" justifyContent="flex-end">
                            <Button variant="contained">Добавить артефакт</Button>
                        </Box>
                    </Grid>
                </Grid>
            </Box>
        </main>
    );
}

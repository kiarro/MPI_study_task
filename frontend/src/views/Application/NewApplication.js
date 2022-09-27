import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import './NewApplication.css'

import { styled } from "@mui/material/styles";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import { Button, TextField, List, FormControl, Select, InputLabel, MenuItem } from "@mui/material";
import TechApplication from './TechApplication';
import AnalysisApplication from './AnalysisApplication';
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

    const [team, setTeam] = useState('');
    const [type, setType] = useState('');
    const [description, setDescription] = useState('');

    const [data, setData] = useState({});

    const { id } = useParams();

    const backClick = async () => {
        history(-1);
    };

    const sendClick = async () => {
        try {
            var body = {
                type: type,
                description: description,
                status: "CREATED",
                experiment: {id: id}
            };

            if (type == "ANALYSIS") {
                body.subject = {id: data.subject_id};
            }

            const response = await fetch('http://localhost:8080/experiments/'+id+'/applications', {
                method: 'POST',
                body: JSON.stringify(body),
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

    const specific_part = () => {
        switch (type){
            case "TECHNIC": return (<TechApplication disabled={false} data={data}></TechApplication>);
            case "ANALYSIS": return (<AnalysisApplication disabled={false} is_card={false} data={data}></AnalysisApplication>);
            default: return (<Item>{type}</Item>);
        }
    }


    // console.log(id);
    return (
        <main>
            <Box sx={{ flexGrow: 1 }} margin="10px" padding="10px">
                <Grid container spacing={2}>

                    <Grid item xs={3}>
                        <Box>
                            <Item>Тип заявки:</Item>
                        </Box>
                    </Grid>
                    <Grid item xs={9}>
                        <Box>
                            {/* <TextField onChange={(e) => setType(e.target.value)}
                                       fullWidth
                            ></TextField> */}

                            <FormControl fullWidth>
                                <InputLabel id="demo-simple-select-label"></InputLabel>
                                <Select
                                    labelId="demo-simple-select-label"
                                    id="demo-simple-select"
                                    value={type}
                                    label="type"
                                    onChange={(e) => setType(e.target.value)}
                                >
                                    <MenuItem value={"ANALYSIS"}>ANALYSIS</MenuItem>
                                    <MenuItem value={"TECHNIC"}>TECHNIC</MenuItem>
                                    <MenuItem value={"LANDING"}>LANDING</MenuItem>
                                </Select>
                            </FormControl>
                        </Box>
                    </Grid>
                    <Grid item xs={3} marginBottom="0px" paddingBottom="0px">
                        <Box>
                            <Item>Описание:</Item>
                        </Box>
                    </Grid>
                    <Grid item xs={12} marginTop="0px" paddingTop="0px">
                        <Box>
                            <TextField onChange={(e) => setDescription(e.target.value)}
                                       fullWidth
                                       multiline="true"
                                       rows="6"
                            ></TextField>
                        </Box>
                    </Grid>
                </Grid>
                {specific_part()}
 
                <Grid container spacing={2}>
                    <Grid item xs={6}>
                        <Box m={1} display="flex" justifyContent="flex-start">
                            <Button variant="contained" onClick={() => sendClick()}>Отправить заявку</Button>
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
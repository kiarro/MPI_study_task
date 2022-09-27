import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { styled } from "@mui/material/styles";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import { Button, TextField, List } from "@mui/material";

import { useState, useEffect } from 'react';
import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";

import HumanData from "./../Human/HumanData";

const Item = styled(Button)(({ theme }) => ({
    padding: theme.spacing(2),
    textAlign: "left"
}));

export default function App(params) {
    var disabled = params.disabled;
    var is_card = params.is_card;
    var data = params.data;
    var application = params.application;

    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);

    // const [data, setData] = useState(params.data);
    // const [isDisabled, setDisabled] = useState('');


    const human_part = () => {
        if (is_card) {
            return (
                <Grid container spacing={1} margin="0px" marginTop="10px" padding="0px" paddingBottom="10px" border="solid">
                    <HumanData disabled={disabled} sub_id={application.subject.id}></HumanData>
                </Grid>
            );
        } else {
            return (
                <Grid container spacing={2}>
                    <Grid item xs={2}>
                        <Box>
                            <Item>Номер Подопытного:</Item>
                        </Box>
                    </Grid>
                    <Grid item xs={2}>
                        <Box sx={{ flexGrow: 1 }} margin="10px">
                            <TextField disabled={disabled}
                                onChange={(e) => /*setHuman(e.target.value)*/ data.subject_id = e.target.value}
                            ></TextField>

                        </Box>
                    </Grid>
                </Grid>
            );
        }
    }

    return (
        <main>
            {human_part()}
        </main>
    );
}

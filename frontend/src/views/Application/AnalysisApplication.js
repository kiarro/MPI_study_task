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
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);

    const [human, setHuman] = useState('');
    
    const getData = () => {
        return {
            human: human,
        }
    }

    const setData = (data) => {
        setHuman(data.human);
    }

    return (
        <main>
            <Box sx={{ flexGrow: 1 }} margin="10px">
                <TextField onChange={(e) => setHuman(e.target.value)}
                ></TextField>
                
            </Box>
        </main>
    );
}

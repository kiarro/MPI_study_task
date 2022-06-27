import {
  BrowserRouter,
  Routes,
  Route,
  Link,
  NavLink,
  useRouteMatch,
  useParams,
} from 'react-router-dom';
import { Button, TextField, List } from "@mui/material";
import HeaderButton from './components/Buttons/HeaderButton';

import logo from './logo.svg';

import './App.css';

import LoginPage from './views/LoginPage/LoginPage';
import UserProfile from './views/UserProfile';
import UserProfileEdit from './views/UserProfileEdit';
import Experiments from './views/Experiments';
import Experiment from './views/Experiment';
import NewExperiment from './views/NewExperiment';
import AdminMain from './views/AdminMain';
import ResearcherMain from './views/ResearcherMain';
import CreateUser from './views/CreateUser';
import WorkerMain from './view/'

function App() {
  return (
    <div className="App">
      <body>
        <BrowserRouter>
          <Routes>
            <Route exact path='' element={<LoginPage />} />
            <Route exact path='login' element={<LoginPage />} />
            <Route exact path='admin' element={<AdminMain />} />
            <Route exact path='researcher' element={<ResearcherMain />} />
            <Route exact path='users/current' element={<UserProfile />} />
            <Route exact path='users/:id' element={<UserProfileEdit />} />
            <Route exact path='users/new' element={<CreateUser />} />
            <Route exact path='experiments' element={<Experiments />} />
            <Route exact path='experiments/new' element={<NewExperiment />} />
            <Route exact path='experiments/:id' element={<Experiment />} />
          </Routes>
        </BrowserRouter>
      </body>
    </div>
  );
}

export default App;

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
import UserProfile from './views/UserProfile/UserProfile';
import UserProfileEdit from './views/UserProfile/UserProfileEdit';
import Experiments from './views/Experiment/Experiments';
import Experiment from './views/Researcher/Experiment';
import NewExperiment from './views/Experiment/NewExperiment';
import AdminMain from './views/Admin/AdminMain';
import ResearcherMain from './views/Researcher/ResearcherMain';
import CreateUser from './views/Admin/CreateUser';
import WorkerMain from './views/Worker/WorkerMain';
import DirectorMain from './views/Director/DirectorMain';
import NewApplication from './views/Application/NewApplication';
// import Application from './views/Application/Application';
import NewReport from './views/Report/NewReport';
import Report from './views/Report/Report';
import Application from './views/Application/Application';

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
            <Route exact path='worker' element={<WorkerMain />} />
            <Route exact path='director' element={<DirectorMain />} />
            <Route exact path='users/current' element={<UserProfile />} />
            <Route exact path='users/:id' element={<UserProfileEdit />} />
            <Route exact path='users/new' element={<CreateUser />} />
            <Route exact path='experiments' element={<Experiments />} />
            <Route exact path='experiments/new' element={<NewExperiment />} />
            <Route exact path='experiments/:id' element={<Experiment />} />
            <Route exact path='experiments/:id/add_rep' element={<NewReport />}/>
            <Route exact path='experiments/:id/add_app' element={<NewApplication />}/>
            <Route exact path='reports/:id' element={<Report />}/>
            <Route exact path='applications/:id' element={<Application />}/>

            {/* <Route exact path='applications/:id' element={<Application/>}/> */}
          </Routes>
        </BrowserRouter>
      </body>
    </div>
  );
}

export default App;

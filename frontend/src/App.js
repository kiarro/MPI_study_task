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
import Experiments from './views/Experiments';
import Experiment from './views/Experiment';
import AdminMain from './views/AdminMain';
import CreateUser from './views/CreateUser';

function App() {
  return (
    <div className="App">
      <body>
        <BrowserRouter>
          <Routes>
            <Route path='' element={<LoginPage />}/>
            {/* <Route exact path='login' element={<LoginPage />} /> */}
            <Route exact path='admin' element={<AdminMain />} />
            <Route exact path='user' element={<UserProfile />} />
            <Route exact path='users/new' element={<CreateUser />} />
            <Route exact path='experiments' element={<Experiments />} />
            <Route exact path='experiments/:id' element={<Experiment />} />
          </Routes>
        </BrowserRouter>
      </body>
    </div>
  );
}

export default App;

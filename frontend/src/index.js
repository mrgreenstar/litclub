import React from 'react';
import ReactDOM from 'react-dom';
import {Router, Route} from 'react-router';
import {BrowserRouter} from 'react-router-dom';
import './css/index.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import App from './js/App';
import NavBar from "./js/navBar";
import AddUser from "./js/addUser";
import * as serviceWorker from './js/serviceWorker';

ReactDOM.render(<NavBar/>, document.getElementById("navbar"));

ReactDOM.render(
    <BrowserRouter>
        <div>
            <Route path="/user/:id" component={App}/>
            <Route path="/addUser" component={AddUser}/>
        </div>
    </BrowserRouter>,
    document.getElementById('root'));
// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();

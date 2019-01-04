import React from 'react';
import {
    Navbar, NavbarBrand,
    NavItem, Nav, NavLink
} from 'reactstrap';
import { BrowserRouter, Route, Link, Switch } from "react-router-dom";
import Users from "./users";
import OwnError from "./OwnError";
import Registration from "./Registration";
import AddBook from "./AddBook";


class App extends React.Component {
    render() {
        return (
            <BrowserRouter>
                <div>
                <Navbar color="light" light>
                    <NavbarBrand tag={Link} to="/">LitClub</NavbarBrand>
                    <Nav className="ml-auto">
                        <NavItem>
                            <NavLink tag={Link} to="/users/1">user</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink tag={Link} to="/addBook">Add new book</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink tag={Link} to="/registration">Registration</NavLink>
                        </NavItem>
                    </Nav>
                </Navbar>
                <Switch>
                    <Route path="/addBook" component={AddBook}/>
                    <Route path="/users/:id" component={Users}/>
                    <Route path="/error" component={OwnError}/>
                    <Route path="/registration" component={Registration}/>
                </Switch>
                </div>
            </BrowserRouter>
        )
    }
}

export default App;
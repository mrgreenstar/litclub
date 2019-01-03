import React from 'react';
import {
    Navbar, NavbarBrand,
    NavItem, Nav, NavLink
} from 'reactstrap';
import { BrowserRouter, Route, Link, Switch } from "react-router-dom";
import Users from "./users";
import OwnError from "./OwnError";
import AddUser from "./addUser";


class App extends React.Component {
    render() {
        return (
            <BrowserRouter>
                <div>
                <Navbar color="light" light expand="md">
                    <NavbarBrand>LitClub</NavbarBrand>
                    <Nav className="ml-auto">
                        <NavItem>
                            <NavLink tag={Link} to="/users/1">user</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink tag={Link} to="/users/2">user2</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink tag={Link} to="/addUser">addnewuser</NavLink>
                        </NavItem>
                    </Nav>
                </Navbar>
                <Switch>
                    <Route path="/users/:id" component={Users}/>
                    <Route path="/error" component={OwnError}/>
                    <Route path="/addUser" component={AddUser}/>
                </Switch>
                </div>
            </BrowserRouter>
        )
    }
}

export default App;
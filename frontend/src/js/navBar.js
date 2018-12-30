import React from 'react';
import {
    Navbar, NavbarBrand,
    NavItem, Nav, NavLink
} from 'reactstrap';

class NavBar extends React.Component {
    render() {
        return (
            <div>
                <Navbar color="light" light expand="md">
                    <NavbarBrand>LitClub</NavbarBrand>
                    <Nav className="ml-auto">
                        <NavItem>
                            <NavLink href="/users/1">user1</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink href="/users/2">user2</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink href="/addUser">Add new user</NavLink>
                        </NavItem>
                    </Nav>
                </Navbar>
            </div>
        )
    }
}

export default NavBar;
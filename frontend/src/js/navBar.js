import React from 'react';
import {
    Navbar,
    NavbarBrand,
    NavItem,
    Nav,
    NavLink
} from 'reactstrap';

class NavBar extends React.Component {
    render() {
        return (
            <div>
                <Navbar color="light" light expand="md">
                    <NavbarBrand>Test</NavbarBrand>
                    <Nav className="ml-auto">
                        <NavItem>
                            <NavLink href="/user/1">user1</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink href="/user/2">user2</NavLink>
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
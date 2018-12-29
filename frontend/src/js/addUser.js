import React from 'react';
import {
    Form, FormGroup, Label, Input, Button,
    Col, Alert
} from 'reactstrap';

class AddUser extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            firstName: '',
            lastName: '',
            email: '',
            login: '',
            password: '',
            isUserCreated: false
        };
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event) {
        this.setState({[event.target.name] : event.target.value});
    }

    handleSubmit(event) {
        /*
        fetch("/addUser", {
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json'
            },
            body: JSON.stringify({
                firstName: this.state.firstName,
                lastName: this.state.lastName,
                email: this.state.email,
                login: this.state.login,
                password: this.state.password
            })
        });
        */
        event.preventDefault();
        this.setState({isUserCreated: true});
        //this.props.history.push("/user/1");
    }

    render() {
        if (this.state.isUserCreated) {
            return <Message/>
        }
        return (
                <Form onSubmit={this.handleSubmit} method="POST">
                    <FormGroup row>
                        <Label sm={{size:2, offset: 1}}>First name</Label>
                        <Col sm={8}>
                            <Input type="text" name="firstName" placeholder="Your first name" onChange={this.handleChange}/>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Label sm={{size:2, offset: 1}}>Second name</Label>
                        <Col sm={8}>
                            <Input type="text" name="lastName" placeholder="Your last name" onChange={this.handleChange}/>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Label sm={{size:2, offset: 1}}>Email</Label>
                        <Col sm={8}>
                            <Input type="email" name="email" placeholder="Type your email here" onChange={this.handleChange}/>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Label sm={{size:2, offset: 1}}>Login</Label>
                        <Col sm={8}>
                            <Input type="text" name="login" placeholder="Type your login here" onChange={this.handleChange}/>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Label sm={{size:2, offset: 1}}>Password</Label>
                        <Col sm={8}>
                            <Input type="text" name="password" placeholder="Type your password here" onChange={this.handleChange}/>
                        </Col>
                    </FormGroup>
                    <FormGroup check row>
                        <Col sm={{size: 6, offset: 5}}>
                            <Button type="submit" color="success">Add new user</Button>
                        </Col>
                    </FormGroup>
                </Form>
        )
    }
}

class Message extends React.Component {
    render() {
        return (
            <Alert color="success">User has been added</Alert>
        )
    }
}

export default AddUser;

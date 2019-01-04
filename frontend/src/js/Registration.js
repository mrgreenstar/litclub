import React from 'react';
import {
    Form, FormGroup, Label,
    Input, Button, Col
} from 'reactstrap';

import Message from './Message';

/**
 * Must fix bug: form's fields wouldn't reset if two or more users have been added
 * Fields shouldn't be cleared if user wasn't added
 */

class Registration extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            firstName: '',
            lastName: '',
            email: '',
            login: '',
            password: '',
            status: 200,
            resultMessage: ""
        };
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);

    }

    handleChange(event) {
        this.setState({[event.target.name] : event.target.value});
    }

    async handleSubmit(event) {
        event.preventDefault();
        await fetch("/users", {
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
        }).then((response) => this.setState({status: response.status}));

    }

    render() {
        const form =
            <Form onSubmit={this.handleSubmit}>
            <FormGroup row>
                <Label sm={{size:2, offset: 1}}>First name</Label>
                <Col sm={8}>
                    <Input type="text" name="firstName" placeholder="Your first name" onChange={this.handleChange}/>
                </Col>
            </FormGroup>
            <FormGroup row>
                <Label sm={{size:2, offset: 1}}>Last name</Label>
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
        </Form>;
        if (this.state.status === 201) {
            return (
                <React.Fragment>
                    <Message message="User has been created" type="success"/>
                    {form}
                </React.Fragment>
            );
        }
        else if (this.state.status === 409) {
            return (
                <React.Fragment>
                    <Message message="User has not been created. Try other login or email" type="danger"/>
                    {form}
                </React.Fragment>
            );
        }
        return form;
    }
}

export default Registration;

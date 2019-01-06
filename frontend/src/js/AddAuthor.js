import React from 'react';
import {
    Form, FormGroup, Input,
    Label, Row, Col, Button
} from 'reactstrap';

import Message from './Message';

class AddAuthor extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            authorFirstName: '',
            authorLastName: '',
            birthDay: '',
            deathDate: '',
            biography: '',
            status: 200
        };
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    async handleSubmit(event) {
        event.preventDefault();
        await fetch("../api/authors", {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-type': 'application/json'
                },
                body: JSON.stringify({
                        firstName: this.state.authorFirstName,
                        lastName: this.state.authorLastName,
                        birthday: this.state.birthDay,
                        deathDate: this.state.deathDate,
                        biography: this.state.biography
                    })
            }).then((response) => this.setState({status: response.status}))
    }

    handleChange(event) {
        this.setState({[event.target.name]:event.target.value})
    }

    render() {
        const form =
            <Form onSubmit={this.handleSubmit}>
                <Row>
                    <Col md={{size: 4, offset: 2}}>
                        <FormGroup>
                            <Label>First name</Label>
                            <Input type="text" name="authorFirstName" placeholder="Author's first name" onChange={this.handleChange}/>
                        </FormGroup>
                    </Col>
                    <Col md={{size: 4}}>
                        <FormGroup>
                            <Label>Last name</Label>
                            <Input type="text" name="authorLastName" placeholder="Author's last name" onChange={this.handleChange}/>
                        </FormGroup>
                    </Col>
                </Row>
                <Row>
                    <Col md={{size: 4, offset: 2}}>
                        <FormGroup>
                            <Label>Birthday</Label>
                            <Input type="date" name="birthDay" onChange={this.handleChange}/>
                        </FormGroup>
                    </Col>
                    <Col md={{size: 4}}>
                        <Label>Death day</Label>
                        <Input type="date" name="deathDate" onChange={this.handleChange}/>
                    </Col>
                </Row>
                <Row>
                    <Col md={{size: 8, offset: 2}}>
                        <Input type="textarea" name="biography" placeholder="Author's biography" onChange={this.handleChange}/>
                    </Col>
                </Row>
                <Row>
                    <Col md={{size:6, offset: 5}}>
                        <FormGroup check>
                            {/* This label adds space between textarea and button. */}
                            <Label md={{size: 6, offset: 6}}/>
                            <Button type="submit" color="success">Add new author</Button>
                        </FormGroup>
                    </Col>
                </Row>
            </Form>;

        if (this.state.status === 200) {
            return form;
        }
        if (this.state.status === 501) {
            return (
                <React.Fragment>
                    <Message message="Some problems occurred. Author hasn't been added" type="danger"/>
                    {form}
                </React.Fragment>
            )
        }
        if (this.state.status === 201)
        return (
            <React.Fragment>
                <Message message="New author has been added" type="success"/>
                {form}
            </React.Fragment>
        )
    }
}

export default AddAuthor;
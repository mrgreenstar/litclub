import React from 'react';
import {
    Button, Form, Col,
    FormGroup, Label, Input
} from 'reactstrap';

import Message from './Message';

class AddBook extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            title: "",
            description: "",
            selectedGenre: "",
            publicationDate: "",
            authors: "",
            genres: [],
            selectedGenreId: 1,
            isLoading: true
        };
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    componentDidMount() {
        fetch("api/genres")
            .then((response) => {
                if (response.ok) {
                    response.json()
                        .then((data) => this.setState({genres: data._embedded.genres, isLoading: false}))
                        .catch((err) => this.setState({error: err}))
                }
            });
    }

    async handleSubmit(event) {
        event.preventDefault();
        await fetch("api/books", {
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json'
            },
            body: JSON.stringify({
                title: this.state.title,
                description: this.state.description,
                genres: {
                    genreName: this.state.selectedGenre,
                    id: this.state.selectedGenreId
                },
                publicationDate: this.state.publicationDate,
            })
        }).then((response) => this.setState({status: response.status}));
        alert(this.state.status);
    }

    handleChange(event) {
        if (event.target.name === "selectedGenre")
            this.setState({selectedGenreId:event.target.options[event.target.options.selectedIndex].getAttribute('data-key')});
        this.setState({[event.target.name] : event.target.value});
    }

    render() {
        const form =
            <Form onSubmit={this.handleSubmit}>
                <FormGroup row>
                    <Label sm={{size: 2, offset: 1}}>Book title</Label>
                    <Col sm={{size: 6}}>
                        <Input type="text" name="title" placeholder="Enter the book title" onChange={this.handleChange}/>
                    </Col>
                </FormGroup>
                <FormGroup row>
                    <Label sm={{size: 2, offset: 1}}>Description</Label>
                    <Col sm={{size: 6}}>
                        <Input type="textarea" name="description" placeholder="Short book's description" onChange={this.handleChange}/>
                    </Col>
                </FormGroup>
                <FormGroup row>
                    <Label sm={{size: 2, offset: 1}}>Genre</Label>
                    <Col sm={{size: 6}}>
                        <Input type="select" name="selectedGenre" onChange={this.handleChange}>
                            {this.state.genres.map((genre) =>
                            <option key={genre.id} data-key={genre.id}>
                                {genre.genreName}
                            </option>
                            )}
                        </Input>
                    </Col>
                </FormGroup>
                <FormGroup row>
                    <Label sm={{size: 2, offset: 1}}>Publication date</Label>
                    <Col sm={{size: 6}}>
                        <Input type="date" name="publicationDate" onChange={this.handleChange}/>
                    </Col>
                </FormGroup>
                <FormGroup check row>
                    <Col sm={{size:6, offset: 5}}>
                        <Button type="submit" color="success">Add book</Button>
                    </Col>
                </FormGroup>
            </Form>;
        if (this.state.status === 201) {
            return (
                <React.Fragment>
                    <Message message="Book has been added" type="success"/>
                    {form}
                </React.Fragment>
            )
        }
        else if (this.state.status === 409) {
            return (
                <React.Fragment>
                    <Message message="This book already in database" type="warning"/>
                    {form}
                </React.Fragment>
            )
        }
        else if (!this.state.isLoading) return form;
        else return <p>Loading...</p>
    }
}

export default AddBook;
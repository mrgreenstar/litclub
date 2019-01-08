import React, { Component } from 'react';
import {Redirect} from 'react-router';
import '../css/users.css';
import {Col} from 'reactstrap';

class Users extends Component {
    constructor(props) {
        super(props);
        this.props = props;
        this.state = {
            code: 200,
            isLoading: true,
            isErrorOccurred: false
        };
    }

    componentDidUpdate(prevProps) {
        if (this.props.match.params.id !== prevProps.match.params.id) {
            this.parseUserInfo();
        }
    }

    componentDidMount() {
        this.parseUserInfo();
    }

    async parseUserInfo() {
        const currId = this.props.match.params.id;
        await fetch("../api/users/" + currId)
            .then((response) => {
                if (response.ok) {
                    response.json()
                        // we need to set code: 200 in state because
                        // if we wouldn't and try to go from existing user to not existing user
                        // we will get wrong info about user
                        .then((data) => this.setState({userInfo: data, isLoading: false, code: 200}))
                        .catch(error => this.setState({isErrorOccurred: true, error: error}));
                }
                else this.setState({isLoading: false, code: 404});
            });
    }

    render() {
        if (this.state.isLoading && !this.state.isErrorOccurred) {
            return <p>Loading...</p>
        }
        else if (this.state.isErrorOccurred) {
            return <Redirect to="/error"/>
        }
        if (this.state.code === 404) {
            return <p>This user doesn't exist</p>;
        }
        return (
            <Col sm={{offset: 1}}>
                <p>{this.state.userInfo.firstName}</p>
                <p>{this.state.userInfo.lastName}</p>
                <p>{this.state.userInfo.email}</p>
                <p>{this.state.userInfo.password}</p>
            </Col>
        );
    }
}

export default Users;

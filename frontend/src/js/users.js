import React, { Component } from 'react';
import {Redirect} from 'react-router';
import '../css/users.css';

class Users extends Component {
    constructor(props) {
        super(props);
        this.props = props;
        this.state = {
            code: 200,
            isLoading: true,
            isErrorOccurred: false
        };
        this.parseUserInfo = this.parseUserInfo.bind(this);
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
        await fetch("/users/" + currId)
            .then((response) => {
                if (response.ok) {
                    response.json()
                        .then((data) => this.setState({answer: data, isLoading: false, code: 200}))
                        .catch(error => this.setState({isErrorOccurred: true, error: error}));
                }
                else this.setState({isLoading: false, code: 404});
            });
    }

    render() {
        if (this.state.isLoading && !this.state.isErrorOccurred) {
            return <p>Loading...</p>
        }
        if (this.state.code === 404) {
            return <p>This user doesn't exist</p>;
        }

        if (this.state.isErrorOccurred) {
            console.log(this.state.error);
            return <Redirect to="/error"/>
        }
        return (
            <div>
                {Object.keys(this.state.answer).map((key) =>
                    <div key={key}>
                        <b>{key}</b>: {this.state.answer[key].toString()}
                    </div>

                )}
            </div>
        );

    }
}

export default Users;
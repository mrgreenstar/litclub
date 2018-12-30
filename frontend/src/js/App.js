import React, { Component } from 'react';
import {Redirect} from 'react-router';
import '../css/App.css';

class App extends Component {
    state = {
        isLoading: true,
        isErrorOccurred: false
    };

    async componentDidMount() {
        const currId = window.location.href[window.location.href.length - 1];
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

export default App;

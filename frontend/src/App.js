import React, { Component } from 'react';
import './App.css';

class App extends Component {
    state = {
        isLoading: true
    };

    async componentDidMount() {
        const currId = window.location.href[window.location.href.length - 1];
        await fetch("/user/" + currId)
            .then((response) => {
                if (response.ok) {
                    response.json()
                        .then((data) => this.setState({answer: data, isLoading: false, code: 200}));

                }
                else this.setState({isLoading: false, code: 404});
            });
    }

    render() {
        if (this.state.isLoading) {
            return <p>Loading...</p>
        }
        if (this.state.code === 404) {
            return <p>This user doesn't exist</p>;
        }
        return (
            <div>
                {Object.keys(this.state.answer).map((key) =>
                    <div key={key}>
                        {this.state.answer[key].toString()}
                    </div>
                )}
            </div>
        );

    }
}

export default App;

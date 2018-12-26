import React from 'react';
import ReactDom from 'react-dom';

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {info: []};
    }

    componentDidMount() {
        const parts = window.location.href.split('/');
        const curId = parts[parts.length - 1];
        fetch('http://localhost:8080/api/users/' + curId)
            .then(response => response.json())
            .then(response => this.setState({info: response}));
    }

    render() {
        return(
            <div>
                {Object.keys(this.state.info).map(i => (
                    <div key={i}>
                        {this.state.info[i].toString()}
                    </div>
                ))}
            </div>
        );
    }
}

ReactDom.render(<App />, document.getElementById("root"));
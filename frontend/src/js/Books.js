import React from 'react';

class Books extends React.Component {
    constructor(props) {
        super(props);
        this.state = {isLoading: true};
    }

    componentDidMount() {
        fetch("api/books")
            .then((response) => {
                if (response.ok) {
                    response.json()
                        .then((data) => this.setState({data: data, isLoading: false}))
                        .catch((err) => this.setState({error: err}));
                }
            });
    }

    render() {
        if (!this.state.isLoading)
            return (
                <div>
                    {Object.keys(this.state.data).map((item) =>
                        <div key={this.state.data[item].id}>
                            <p>{this.state.data[item].title}</p>
                            <p>{this.state.data[item].description}</p>
                            <p>{this.state.data[item].publicationDate}</p>
                            <p>{this.state.data[item].rating}</p>
                        </div>
                    )}
                </div>
            );
        else return <p>Loading...</p>
    }
}

export default Books;
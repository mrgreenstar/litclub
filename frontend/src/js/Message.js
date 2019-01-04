import React from "react";
import {Alert} from "reactstrap";

class Message extends React.Component {
    render() {
        return (
            <Alert color={this.props.type}>
                {this.props.message}
            </Alert>
        )
    }
}

export default Message;
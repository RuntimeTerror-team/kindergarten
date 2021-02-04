import React, {Component} from 'react';

class KindergartenGroupInfoContainer extends Component {
    constructor (props) {
        super(props);
        this.state = {
            msg: "Labas"
        }
    }
    render() {
        return (
            <div>
                Grupių info
                <p>{this.state.msg}</p>
                <p>{this.props.kindergartenId}</p>
            </div>
        )
    }

}

export default KindergartenGroupInfoContainer;
import React, { Component } from "react";
import '../../styles/fileInput.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCloudUploadAlt } from '@fortawesome/free-solid-svg-icons'
import baseUrl from "../../AppConfig";
import axios from "axios";

class FileInput extends Component {
    constructor(props) {
        super(props);
        this.state = {
            files: [],
            message: "",
            messageStyle: ""
        };
    }

    alertTimer = null;

    componentWillUnmount = () => {
        if (this.alertTimer) {
            clearTimeout(this.alertTimer);
        }
    }

    onChange = (e) => {
        let files = e.target.files;
        console.log(files);
        let filesArr = Array.prototype.slice.call(files);
        console.log(filesArr);
        this.setState({ files: [...this.state.files, ...filesArr] });
    }

    removeFile = (f) => {
        this.setState({ files: this.state.files.filter(x => x !== f) });
    }

    handleSubmit = (e) => {
        e.preventDefault();
        console.log("Submitted");
        console.log("Selected child: " + this.props.selectedChildId);
        console.log(e);

        const formData = new FormData();

        formData.append('file', this.state.files[0]);

        axios
            .post(`${baseUrl}/api/health-forms/${this.props.selectedChildId}`, formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            })
            .then((res) => {
                this.setState({ message: res.data.message })
                this.setState({ messageStyle: "alert alert-success" })
                this.setState({ files: [] })
                this.alertTimer = setTimeout(() => {
                    this.setState({ message: "" })
                    this.setState({ messageStyle: "" })
                }, 3000);
            })
            .catch((err) => {
                if (err.response.status && err.response.status === 417) {
                    this.setState({ message: err.response.data })
                    this.setState({ messageStyle: "alert alert-danger" })
                    this.alertTimer = setTimeout(() => {
                        this.setState({ message: "" })
                        this.setState({ messageStyle: "" })
                    }, 3000);
                } else {
                    console.log(err);
                }
            })
    }

    render() {
        return (
            <div className="col-8 row">
                <form className="col-12 row" onSubmit={this.handleSubmit} style={{ height: "40px" }}>
                    <label className="m-0 btn btn-main col-3">
                        <input type="file" multiple onChange={this.onChange} />
                        <FontAwesomeIcon icon={faCloudUploadAlt} /> Ieškoti
                    </label>
                    {this.state.files.map((x, index) =>
                        <div key={index} className="file-preview my-auto col-6" style={{ overflow: "hidden" }} onClick={this.removeFile.bind(this, x)}>{x.name}</div>
                    )}
                    {this.state.files.length > 0 && <button type="submit" className="btn btn-green col ml-2">Išsaugoti</button>}
                </form>
                {this.state.message
                    && <span className={`float-right mt-2 offset-1 ${this.state.messageStyle}`} style={{ width: "23em" }}>
                        {this.state.message}
                    </span>}
            </div>
        );
    }
}

export default FileInput;
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
            file: null,
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
        let selectedFile = e.target.files[0];
        if (selectedFile.type === "application/pdf") {
            this.setState({ file: e.target.files[0] });
        } else {
            this.showAlert("Prašome pasirinkti pdf formato failą", "warning");
        }

    }

    showAlert = (msg, style) => {
        this.setState({ message: msg })
        this.setState({ messageStyle: `alert alert-${style}` })
        this.alertTimer = setTimeout(() => {
            this.setState({ message: "" })
            this.setState({ messageStyle: "" })
        }, 3000);
    }

    handleSubmit = (e) => {
        e.preventDefault();

        const formData = new FormData();

        formData.append('file', this.state.file);

        if (this.props.selectedChildId !== "Pasirinkite vaiką") {
            axios
                .post(`${baseUrl}/api/health-forms/${this.props.selectedChildId}`, formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                })
                .then((res) => {
                    this.setState({ file: null })
                    this.showAlert(res.data.message, "success")
                    this.props.updateForms();
                })
                .catch((err) => {
                    if (err.response && err.response.status && err.response.status === 417) {
                        this.showAlert(err.response.data.message, "danger")
                    } else {
                        console.log(err);
                    }
                })
        } else {
            this.showAlert("Pasirinkite vaiką, kurio pažymą saugosite.", "danger");
        }
    }

    render() {
        return (
            <div className="col-8 row">
                <form className="col-12 row" onSubmit={this.handleSubmit} style={{ height: "40px" }}>
                    <label className="m-0 btn btn-main col-3">
                        <input type="file" multiple onChange={this.onChange} />
                        <FontAwesomeIcon icon={faCloudUploadAlt} /> Ieškoti
                    </label>
                    {this.state.file &&
                        <div className="file-preview my-auto col-6" style={{ overflow: "hidden" }} >{this.state.file.name}</div>
                    }
                    {this.state.file && <button type="submit" className="btn btn-green col ml-2">Išsaugoti</button>}
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
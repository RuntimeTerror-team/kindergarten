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

        if (this.props.selectedChildId !== "Pasirinkti vaiką" && this.props.selectedChildId.length !== 0) {

            if (this.state.file.size >= 2000000) {
                this.showAlert("Pasirinkote per didelį failą (iki 2MB).", "danger")
                return;
            }

            const formData = new FormData();

            formData.append('file', this.state.file.size);

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
                    this.showAlert("Klaida. Failo nepavyko išsaugoti.", "danger")
                    console.log(err);
                })
        } else {
            this.showAlert("Pasirinkite vaiką, kurio pažymą saugosite.", "danger")
        }
    }

    render() {
        return (
            <div className="col-8 row">
                <form className="col-12 row" onSubmit={this.handleSubmit} style={{ height: "40px" }}>
                    <label className="m-0 btn btn-main col-3 h-100">
                        <input type="file" multiple onChange={this.onChange} />
                        <FontAwesomeIcon icon={faCloudUploadAlt} /> Ieškoti
                    </label>
                    {this.state.file &&
                        <div className="file-preview my-auto col-6 h-100" style={{ overflow: "hidden" }} >{this.state.file.name}</div>
                    }
                    {this.state.file && <button type="submit" className="btn btn-green col ml-2 h-100">Išsaugoti</button>}
                </form>
                {this.state.message
                    && <div className={`my-3 offset-1 ${this.state.messageStyle}`} style={{ width: "23em" }}>
                        {this.state.message}
                    </div>}
            </div>
        );
    }
}

export default FileInput;
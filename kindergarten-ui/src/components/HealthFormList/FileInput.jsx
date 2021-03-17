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
        console.log(this.props.selectedChildId);
        if (this.props.selectedChildId !== "Pasirinkti vaiką" && this.props.selectedChildId.length !== 0) {

            if (this.state.file.size >= 2000000) {
                this.showAlert("Pasirinkote per didelį failą (iki 2MB).", "danger")
                return;
            }

            const formData = new FormData();

            formData.append('file', this.state.file);

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
            <div className="form-group col-lg-12 mb-0">
                <form onSubmit={this.handleSubmit}>
                    <input name="fileToUpload" id="fileToUpload" type="file" className="filestyle" onChange={this.onChange} />
                    <div className="bootstrap-filestyle input-group">
                        <span className="group-span-filestyle input-group col-12 px-0">
                            <label className="templatemo-blue-button" htmlFor="fileToUpload"><FontAwesomeIcon icon={faCloudUploadAlt} /> Ieškoti</label>
                            <input type="text" className="form-control" disabled value={(this.state.file && this.state.file.name) || ""} />
                        </span>
                    </div>
                    <p>Formatas PDF. Maksimalus failo dydis 2MB.</p>
                    <div className={`my-3 ${this.state.messageStyle}`}>
                        {this.state.message}
                    </div>
                    <div className="form-group col-12 text-right mb-0 px-0">
                        <button type="submit" className="templatemo-blue-button">Išsaugoti</button>
                    </div>
                </form>
            </div>
        );
    }
}

export default FileInput;
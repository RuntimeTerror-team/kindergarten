import React, { Component } from "react";
import '../../styles/fileInput.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCloudUploadAlt } from '@fortawesome/free-solid-svg-icons'

class FileInput extends Component {
    constructor() {
        super();
        this.onChange = this.onChange.bind(this);
        this.state = {
            files: []
        };
    }

    onChange(e) {
        let files = e.target.files;
        console.log(files);
        let filesArr = Array.prototype.slice.call(files);
        console.log(filesArr);
        this.setState({ files: [...this.state.files, ...filesArr] });
    }

    removeFile(f) {
        this.setState({ files: this.state.files.filter(x => x !== f) });
    }

    render() {
        return (
            <div className="col-8 row">
                <div className="col-10 row">
                    <label className="m-0 btn btn-main col-4">
                        <input type="file" multiple onChange={this.onChange} />
                        <FontAwesomeIcon icon={faCloudUploadAlt} /> Ieškoti
                    </label>
                    {this.state.files.map(x =>
                        <div className="file-preview my-auto col-8" style={{ overflow: "hidden" }} onClick={this.removeFile.bind(this, x)}>{x.name}</div>
                    )}

                </div>
                {this.state.files.length > 0 && <button className="btn btn-green p-0 ml-2 col">Išsaugoti</button>}
            </div>
        );
    }
}

export default FileInput;
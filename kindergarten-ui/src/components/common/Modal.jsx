import React from "react";

const Modal = ({ modalTitle, modalMessage, modalApprove, modalButtonMessage, modalButtonStyle, targetId }) => (
    <div className="modal fade" id="exampleModal" tabIndex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div className="modal-dialog">
            <div className="modal-content">
                <div className="modal-header">
                    <h5 className="modal-title" id="exampleModalLabel">{modalTitle}</h5>
                    <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div className="modal-body">
                    {modalMessage}
                </div>
                <div className="modal-footer">
                    <button type="button" className="btn btn-secondary" data-dismiss="modal">Atsisakyti</button>
                    <button type="button" id={targetId} className={`btn btn-${modalButtonStyle}`} data-dismiss="modal" onClick={modalApprove}>{modalButtonMessage}</button>
                </div>
            </div>
        </div>
    </div>
)

export default Modal;
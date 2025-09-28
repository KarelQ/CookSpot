import React, { useState } from "react";
import styled from "styled-components";
import { addReport } from "../services/UserInteractionService.jsx";

const Overlay = styled.div`
    position: fixed;
    top: 0;
    left: 0;
    width: 100vw;
    height: 100vh;
    background: rgba(0, 0, 0, 0.5);
    backdrop-filter: blur(5px);
    display: flex;
    align-items: center;
    justify-content: center;
    z-index: 999;
`;

const Popup = styled.div`
    background-color: #2A3C4C;
    border: 2px solid #FB8A22;
    border-radius: 12px;
    padding: 20px;
    width: 400px;
    max-width: 90%;
    text-align: center;
    color: #D1D1D1;
    display: flex;
    flex-direction: column;
    align-items: center;
`;

const Title = styled.h3`
    margin-bottom: 20px;
    color: #FB8A22;
`;

const ReasonButton = styled.button`
    border: 2px solid #FB8A22;
    border-radius: 2em;
    text-align: center;
    color: black;
    background-color: #ffffff;
    letter-spacing: 0.7px;
    width: 90%;
    padding: 0.5em;
    font-style: normal;
    font-weight: 400;
    font-size: 15px;
    line-height: 18px;
    margin-bottom: 10px;
    cursor: pointer;
    transition: background 0.2s;

    &:hover {
        background-color: #FB8A22;
        color: #fff;
    }
`;

const CloseButton = styled.button`
    background: transparent;
    color: #FB8A22;
    border: none;
    font-size: 20px;
    position: absolute;
    top: 10px;
    right: 15px;
    cursor: pointer;
`;

const ReportIconWrapper = styled.div`
    display: inline-block;
    margin-left: 10px;

    i {
        font-size: 24px;
        color: #888;
        cursor: pointer;
        transition: color 0.2s;
    }

    i:hover {
        color: red;
    }
`;

const ReportPopup = ({ idPost }) => {
    const [showPopup, setShowPopup] = useState(false);

    const handleReport = async (reason) => {
        try {
            await addReport({ idPost, report_content: reason });
            alert(`Zgłoszenie wysłane: ${reason}`);
            setShowPopup(false);
        } catch (err) {
            console.error("Błąd wysyłania zgłoszenia:", err);
        }
    };

    return (
        <ReportIconWrapper>
            <i className="material-symbols-outlined" onClick={() => setShowPopup(true)}>Report</i>

            {showPopup && (
                <Overlay>
                    <Popup>
                        <CloseButton onClick={() => setShowPopup(false)}>×</CloseButton>
                        <Title>Do you want to report this recipe?</Title>
                        <ReasonButton onClick={() => handleReport("Spam")}>Spam</ReasonButton>
                        <ReasonButton onClick={() => handleReport("Inappropriate content")}>Inappropriate content</ReasonButton>
                        <ReasonButton onClick={() => handleReport("Inappropriate images")}>Inappropriate images</ReasonButton>
                        <ReasonButton onClick={() => handleReport("Incorrect recipe")}>Incorrect recipe</ReasonButton>
                        <ReasonButton onClick={() => handleReport("Advertisement")}>Advertisement</ReasonButton>
                        <ReasonButton onClick={() => handleReport("Offensive language")}>Offensive language</ReasonButton>
                        <ReasonButton onClick={() => handleReport("Other reason")}>Other reason</ReasonButton>
                    </Popup>
                </Overlay>
            )}
        </ReportIconWrapper>
    );
};

export default ReportPopup;

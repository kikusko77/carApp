package org.but.eloryksauthorization.newbackend.api;

public class ResponseStatusDTO {
    private int errorCode;
    private String message;
    public ResponseStatusDTO() {}
    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

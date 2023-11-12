package org.but.eloryksauthorization.newbackend.api;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class SignKeyDTO {
    @JsonIgnore
    private Long sign_key_id;
    private int keyType;
    private String cordX;
    private String cordY;

    public Long getSign_key_id() {
        return sign_key_id;
    }

    public void setSign_key_id(Long sign_key_id) {
        this.sign_key_id = sign_key_id;
    }

    public int getKeyType() {
        return keyType;
    }

    public void setKeyType(int keyType) {
        this.keyType = keyType;
    }

    public String getCordX() {
        return cordX;
    }

    public void setCordX(String cordX) {
        this.cordX = cordX;
    }

    public String getCordY() {
        return cordY;
    }

    public void setCordY(String cordY) {
        this.cordY = cordY;
    }
}

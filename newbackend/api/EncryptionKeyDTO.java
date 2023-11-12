package org.but.eloryksauthorization.newbackend.api;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class EncryptionKeyDTO {
    @JsonIgnore
    private Long encryptionKeyId;
    private int keyType;
    private String cordX;
    private String cordY;

    public Long getEncryptionKeyId() {
        return encryptionKeyId;
    }

    public void setEncryptionKeyId(Long encryption_key_id) {
        this.encryptionKeyId = encryption_key_id;
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

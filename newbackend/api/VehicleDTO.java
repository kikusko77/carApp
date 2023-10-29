package org.but.eloryksauthorization.newbackend.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VehicleDTO {

    @JsonProperty("StationId")
    private Long stationId;

    @JsonProperty("StationType")
    private String stationType;

    @JsonProperty("Position")
    private PositionDTO position;

    @JsonProperty("CertificateId")
    private String certificateId;

    @JsonProperty("EncryptionKey")
    private EncryptionKeyDTO encryptionKey;

    @JsonProperty("SignKey")
    private SignKeyDTO signKey;

    @JsonIgnore
    private SpeedLimitDTO speedLimit;

    public VehicleDTO(){};

    public SpeedLimitDTO getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(SpeedLimitDTO speedLimit) {
        this.speedLimit = speedLimit;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public String getStationType() {
        return stationType;
    }

    public void setStationType(String stationType) {
        this.stationType = stationType;
    }

    public PositionDTO getPosition() {
        return position;
    }

    public void setPosition(PositionDTO position) {
        this.position = position;
    }

    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }

    public EncryptionKeyDTO getEncryptionKey() {
        return encryptionKey;
    }

    public void setEncryptionKey(EncryptionKeyDTO encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    public SignKeyDTO getSignKey() {
        return signKey;
    }

    public void setSignKey(SignKeyDTO signKey) {
        this.signKey = signKey;
    }
}

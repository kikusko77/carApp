package org.but.eloryksauthorization.newbackend.api;
public class VehicleDTO {

    private Long vehicleId;
    private String stationType;
    private PositionDTO position;
    private String certificateId;
    private EncryptionKeyDTO encryptionKey;
    private SignKeyDTO signKey;
    private SpeedLimitDTO speedLimit;

    public SpeedLimitDTO getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(SpeedLimitDTO speedLimit) {
        this.speedLimit = speedLimit;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
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

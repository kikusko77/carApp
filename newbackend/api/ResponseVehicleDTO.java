package org.but.eloryksauthorization.newbackend.api;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseVehicleDTO {
    private Long stationId;
    private int errorCode;
    private String message;
    private SpeedLimitDTO speedLimit;
    private String speedLimitEncrypted;

    public ResponseVehicleDTO(){};

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

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

    public SpeedLimitDTO getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(SpeedLimitDTO speedLimit) {
        this.speedLimit = speedLimit;
    }

    public String getSpeedLimitEncrypted() {
        return speedLimitEncrypted;
    }

    public void setSpeedLimitEncrypted(String speedLimitEncrypted) {
        this.speedLimitEncrypted = speedLimitEncrypted;
    }
}
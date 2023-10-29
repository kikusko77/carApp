package org.but.eloryksauthorization.newbackend.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VehicleUpdateDTO {

    @JsonProperty("StationId")
    private Long stationId;

    @JsonProperty("SpeedLimit")
    private SpeedLimitDTO speedLimit;

    @JsonProperty("Position")
    private PositionDTO position;

    public VehicleUpdateDTO() {}

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public SpeedLimitDTO getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(SpeedLimitDTO speedLimit) {
        this.speedLimit = speedLimit;
    }

    public PositionDTO getPosition() {
        return position;
    }

    public void setPosition(PositionDTO position) {
        this.position = position;
    }
}

package org.but.eloryksauthorization.newbackend.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SpeedLimitDTO {
    @JsonIgnore
    private Long speedLimitId;

    @JsonProperty("Speed")
    private int speed;

    @JsonProperty("EngineSpeed")
    private int engineSpeed;

    public SpeedLimitDTO() {}

    public Long getSpeedLimitId() {
        return speedLimitId;
    }

    public void setSpeedLimitId(Long speedLimitId) {
        this.speedLimitId = speedLimitId;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getEngineSpeed() {
        return engineSpeed;
    }

    public void setEngineSpeed(int engineSpeed) {
        this.engineSpeed = engineSpeed;
    }
}

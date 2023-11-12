package org.but.eloryksauthorization.newbackend.api;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SpeedLimitDTO {
    @JsonIgnore
    private Long id;
    private int speed;
    private int engineSpeed;

    public SpeedLimitDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

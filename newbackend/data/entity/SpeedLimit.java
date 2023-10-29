package org.but.eloryksauthorization.newbackend.data.entity;
import javax.persistence.*;

@Entity
@Table(name = "speed_limit")
public class SpeedLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "speed_limit_id")
    private Long speedLimitId;

    private int speed;

    private int engineSpeed;

    @OneToOne(mappedBy = "speedLimit")
    private Vehicle vehicle;

    public SpeedLimit(Long speedLimitId, int speed, int engineSpeed,Vehicle vehicle) {
        this.speedLimitId = speedLimitId;
        this.speed = speed;
        this.engineSpeed = engineSpeed;
        this.vehicle=vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public SpeedLimit() {}

    public Long getSpeedLimitId() {return speedLimitId;}

    public void setSpeedLimitId(Long speedLimitId) {this.speedLimitId = speedLimitId;}

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

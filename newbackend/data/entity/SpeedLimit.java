package org.but.eloryksauthorization.newbackend.data.entity;
import javax.persistence.*;

@Entity
@Table(name = "speed_limit")
public class SpeedLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private int speed;
    private int engineSpeed;
    @OneToOne(mappedBy = "speedLimit")
    private Vehicle vehicle;

    public SpeedLimit(Long id, int speed, int engineSpeed,Vehicle vehicle) {
        this.id = id;
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

    public SpeedLimit() {

    }

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

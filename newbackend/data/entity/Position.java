package org.but.eloryksauthorization.newbackend.data.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private Long id;

    @Column(name = "speed")
    private int speed;

    @Column(name = "heading")
    private int heading;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @OneToOne(mappedBy = "position")
    private Vehicle vehicle;

    public Position(Long id, int speed, int heading, double latitude, double longitude, Vehicle vehicle,LocalDateTime timestamp) {
        this.id = id;
        this.speed = speed;
        this.heading = heading;
        this.latitude = latitude;
        this.longitude = longitude;
        this.vehicle=vehicle;
        this.timestamp=timestamp;
    }

    public Position() {}

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
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

    public int getHeading() {
        return heading;
    }

    public void setHeading(int heading) {
        this.heading = heading;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

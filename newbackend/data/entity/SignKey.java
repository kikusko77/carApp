package org.but.eloryksauthorization.newbackend.data.entity;
import javax.persistence.*;

@Entity
@Table(name = "sign_key")
public class SignKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sign_key_id")
    private Long id;

    @Column(name = "key_type")
    private int keyType;

    @Column(name = "cord_x")
    private String cordX;

    @Column(name = "cord_y")
    private String cordY;
    @OneToOne(mappedBy = "signKey")
    private Vehicle vehicle;

    public SignKey(Long id, int keyType, String cordX, String cordY,Vehicle vehicle) {
        this.id = id;
        this.keyType = keyType;
        this.cordX = cordX;
        this.cordY = cordY;
        this.vehicle=vehicle;

    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public SignKey() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

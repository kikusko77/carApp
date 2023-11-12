package org.but.eloryksauthorization.newbackend.data.entity;
import javax.persistence.*;

@Entity
@Table(name = "encryption_key")
public class EncryptionKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "encryption_key_id")
    private Long encryptionKeyId;

    @Column(name = "key_type")
    private int keyType;

    @Column(name = "cord_x")
    private String cordX;

    @Column(name = "cord_y")
    private String cordY;
    @OneToOne(mappedBy = "encryptionKey")
    private Vehicle vehicle;

    public EncryptionKey(Long encryptionKeyId, int keyType, String cordX, String cordY,Vehicle vehicle) {
        this.encryptionKeyId = encryptionKeyId;
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

    public EncryptionKey() {}

    public Long getEncryptionKeyId() {
        return encryptionKeyId;
    }

    public void setEncryptionKeyId(Long id) {
        this.encryptionKeyId = id;
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

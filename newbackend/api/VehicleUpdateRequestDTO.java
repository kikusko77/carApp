package org.but.eloryksauthorization.newbackend.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class VehicleUpdateRequestDTO {

    @JsonProperty("Vehicle")
    private List<VehicleUpdateDTO> vehicle;

    public List<VehicleUpdateDTO> getVehicle() {
        return vehicle;
    }

    public void setVehicle(List<VehicleUpdateDTO> vehicle) {
        this.vehicle = vehicle;
    }
}

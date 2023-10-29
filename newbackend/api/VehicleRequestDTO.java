package org.but.eloryksauthorization.newbackend.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class VehicleRequestDTO {

    @JsonProperty("Vehicle")
    private List<VehicleDTO> vehicle;

    public List<VehicleDTO> getVehicle() {
        return vehicle;
    }

    public void setVehicle(List<VehicleDTO> vehicle) {
        this.vehicle = vehicle;
    }
}

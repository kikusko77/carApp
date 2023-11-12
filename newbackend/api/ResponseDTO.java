package org.but.eloryksauthorization.newbackend.api;
import java.util.List;

public class ResponseDTO {
    private ResponseStatusDTO responseStatus;
    private List<ResponseVehicleDTO> responseVehicle;

    public ResponseDTO() {}
    public ResponseStatusDTO getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatusDTO responseStatus) {
        this.responseStatus = responseStatus;
    }

    public List<ResponseVehicleDTO> getResponseVehicle() {
        return responseVehicle;
    }

    public void setResponseVehicle(List<ResponseVehicleDTO> responseVehicle) {
        this.responseVehicle = responseVehicle;
    }
}

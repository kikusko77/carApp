package org.but.eloryksauthorization.newbackend.rest;

import org.but.eloryksauthorization.newbackend.api.ResponseDTO;
import org.but.eloryksauthorization.newbackend.api.VehicleDTO;
import org.but.eloryksauthorization.newbackend.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/its/vehicle")
public class VehicleAuthorizationRequestRestController2 {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/all")
    public ResponseEntity<Map<String, List<VehicleDTO>>> getAllVehicles() {
        Map<String, List<VehicleDTO>> response = new HashMap<>();
        List<VehicleDTO> vehicleDTOs = vehicleService.getAllVehicles();
        response.put("Vehicle", vehicleDTOs);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable Long id) {
        VehicleDTO vehicleDTO = vehicleService.findById(id);
        return new ResponseEntity<>(vehicleDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createVehicle(@RequestBody VehicleDTO vehicleDTO) {
        ResponseDTO response = vehicleService.createVehicle(vehicleDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateVehicle(@RequestBody VehicleDTO vehicleDTO) {
        ResponseDTO response = vehicleService.updateVehicle(vehicleDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteVehicle(@PathVariable Long id) {
        ResponseDTO response = vehicleService.deleteVehicleById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

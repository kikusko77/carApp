package org.but.eloryksauthorization.newbackend.rest;

import org.but.eloryksauthorization.newbackend.api.*;
import org.but.eloryksauthorization.newbackend.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/its/vehicle")
public class VehicleAuthorizationRequestRestController2 {
    @Autowired
    private VehicleService vehicleService;



    @GetMapping(value = "/all", produces = "application/json",headers = "Accept=application/json")
    public ResponseEntity<Map<String, List<VehicleDTO>>> getAllVehicles(@RequestParam(required = false) String timestamp,
                                                                        @RequestParam(required = false) String responsible,
                                                                        @RequestHeader(name="Accept") String acceptHeader)
    {
        Map<String, List<VehicleDTO>> response = new HashMap<>();
        List<VehicleDTO> vehicleDTOs = vehicleService.getAllVehicles();
        response.put("Vehicle", vehicleDTOs);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json",headers = "Accept=application/json")
    public ResponseEntity<Map<String, List<VehicleDTO>>> getVehicleById(@PathVariable Long id,
                                                                        @RequestParam(required = false) String timestamp,
                                                                        @RequestParam(required = false) String responsible,
                                                                        @RequestHeader(name="Accept") String acceptHeader)
    {
        VehicleDTO vehicleDTO = vehicleService.findById(id);
        Map<String, List<VehicleDTO>> response = new HashMap<>();
        response.put("Vehicle", Arrays.asList(vehicleDTO));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(headers = "Accept=application/json",consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponsePostDeleteDTO> createVehicle(@RequestParam(required = false) String timestamp,
                                                        @RequestParam(required = false) String responsible,
                                                        @RequestBody VehicleRequestDTO vehicleRequestDTO,
                                                        @RequestHeader(name="Accept") String acceptHeader)
    {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneOffset.UTC);
        LocalDateTime localDateTime = LocalDateTime.parse(timestamp, formatter);
        ResponsePostDeleteDTO response = vehicleService.createVehicle(vehicleRequestDTO, responsible, localDateTime);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(headers = "Accept=application/json",consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponsePutDTO> updateVehicle(@RequestParam(required = false) String timestamp,
                                                        @RequestParam(required = false) String responsible,
                                                        @RequestBody VehicleUpdateRequestDTO vehicleUpdateRequestDTO,
                                                        @RequestHeader(name="Accept") String acceptHeader)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneOffset.UTC);
        LocalDateTime localDateTime = LocalDateTime.parse(timestamp, formatter);
        ResponsePutDTO response = vehicleService.updateVehicle(vehicleUpdateRequestDTO, responsible, localDateTime);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(headers = "Accept=application/json", produces = "application/json")
    public ResponseEntity<ResponsePostDeleteDTO> deleteVehiclesById(
            @RequestParam(required = false) String timestamp,
            @RequestParam(required = false) String responsible,
            @RequestParam(name = "vehicles", required = false) List<Long> vehiclesList,
            @RequestHeader(name="Accept") String acceptHeader)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneOffset.UTC);
        LocalDateTime localDateTime = LocalDateTime.parse(timestamp, formatter);
        ResponsePostDeleteDTO response = vehicleService.deleteVehiclesById(vehiclesList, responsible, localDateTime);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

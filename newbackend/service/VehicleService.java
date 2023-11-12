package org.but.eloryksauthorization.newbackend.service;

import org.but.eloryksauthorization.newbackend.api.*;
import org.but.eloryksauthorization.newbackend.data.entity.Vehicle;
import org.but.eloryksauthorization.newbackend.data.repository.VehicleRepository;
import org.but.eloryksauthorization.newbackend.mappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class VehicleService {

    private VehicleRepository vehicleRepository;
    private VehicleMapper vehicleMapper;
    private PositionService positionService;
    private EncryptionKeyService encryptionKeyService;
    private SignKeyService signKeyService;
    private SpeedLimitService speedLimitService;
    private PositionMapper positionMapper;
    private EncryptionKeyMapper encryptionKeyMapper;
    private SignKeyMapper signKeyMapper;
    private SpeedLimitMapper speedLimitMapper;
    @Autowired
    public VehicleService(VehicleRepository vehicleRepository,
                          VehicleMapper vehicleMapper,
                          PositionService positionService,
                          EncryptionKeyService encryptionKeyService,
                          SignKeyService signKeyService,
                          SpeedLimitService speedLimitService,
                          PositionMapper positionMapper,
                          EncryptionKeyMapper encryptionKeyMapper,
                          SignKeyMapper signKeyMapper,
                          SpeedLimitMapper speedLimitMapper) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
        this.positionService = positionService;
        this.encryptionKeyService = encryptionKeyService;
        this.signKeyService = signKeyService;
        this.speedLimitService = speedLimitService;
        this.positionMapper = positionMapper;
        this.encryptionKeyMapper = encryptionKeyMapper;
        this.signKeyMapper = signKeyMapper;
        this.speedLimitMapper = speedLimitMapper;
    }

    public boolean existsById(Long id) {
        return vehicleRepository.existsById(id);
    }

    public List<VehicleDTO> findAll() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicleMapper.toDTOList(vehicles);
    }

    public VehicleDTO findById(Long id) {
        return vehicleRepository.findById(id)
                .map(vehicleMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle with ID " + id + " not found."));
    }

    public VehicleDTO save(VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleMapper.toEntity(vehicleDTO);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        savedVehicle.getPosition().setTimestamp(LocalDateTime.now(Clock.systemUTC()));
        savedVehicle.getEncryptionKey().setCordX(vehicleDTO.getEncryptionKey().getCordX());
        savedVehicle.getEncryptionKey().setCordY(vehicleDTO.getEncryptionKey().getCordY());
        savedVehicle.getSignKey().setCordX(vehicleDTO.getSignKey().getCordX());
        savedVehicle.getSignKey().setCordY(vehicleDTO.getSignKey().getCordY());
        return vehicleMapper.toDTO(savedVehicle);
    }

    public VehicleDTO update(VehicleDTO vehicleDTO) {
        if (vehicleRepository.existsById(vehicleDTO.getVehicleId())) {
            Vehicle vehicle = vehicleMapper.toEntity(vehicleDTO);
            Vehicle updatedVehicle = vehicleRepository.save(vehicle);
            updatedVehicle.getPosition().setTimestamp(LocalDateTime.now(Clock.systemUTC()));
            updatedVehicle.getEncryptionKey().setCordX(vehicleDTO.getEncryptionKey().getCordX());
            updatedVehicle.getEncryptionKey().setCordY(vehicleDTO.getEncryptionKey().getCordY());
            updatedVehicle.getSignKey().setCordX(vehicleDTO.getSignKey().getCordX());
            updatedVehicle.getSignKey().setCordY(vehicleDTO.getSignKey().getCordY());
            return vehicleMapper.toDTO(updatedVehicle);
        } else {
            throw new EntityNotFoundException("Vehicle with ID " + vehicleDTO.getVehicleId() + " not found.");
        }
    }

    public void deleteById(Long id) {
        if (vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Vehicle with ID " + id + " not found.");
        }
    }

    @Transactional
    public List<VehicleDTO> getAllVehicles() {
        List<VehicleDTO> vehicleDTOs = findAll();


        return vehicleDTOs;
    }

    @Transactional
    public ResponseDTO createVehicle(VehicleDTO vehicleDTO) {
        ResponseDTO response = new ResponseDTO();
        ResponseStatusDTO responseStatus = new ResponseStatusDTO();
        ResponseVehicleDTO responseVehicleDTO = new ResponseVehicleDTO();
        try {
            VehicleDTO savedVehicleDTO = save(vehicleDTO);
            responseStatus.setErrorCode(0);
            responseStatus.setMessage("Vehicle created successfully");
            responseVehicleDTO.setStationId(savedVehicleDTO.getVehicleId());
            responseVehicleDTO.setErrorCode(0);
            responseVehicleDTO.setMessage("Vehicle created successfully");
        } catch (Exception e) {
            responseStatus.setErrorCode(1);
            responseStatus.setMessage(e.getMessage());
            responseVehicleDTO.setStationId(null);
            responseVehicleDTO.setErrorCode(1);
            responseVehicleDTO.setMessage("Cannot create vehicle");
        }
        List<ResponseVehicleDTO> responseVehicleList = new ArrayList<>();
        responseVehicleList.add(responseVehicleDTO);
        response.setResponseStatus(responseStatus);
        response.setResponseVehicle(responseVehicleList);

        return response;
    }

    @Transactional
    public ResponseDTO updateVehicle(VehicleDTO vehicleDTO) {
        ResponseDTO response = new ResponseDTO();
        ResponseStatusDTO responseStatus = new ResponseStatusDTO();
        ResponseVehicleDTO responseVehicleDTO = new ResponseVehicleDTO();
        try {
            VehicleDTO updatedVehicleDTO = update(vehicleDTO);
            responseStatus.setErrorCode(0);
            responseStatus.setMessage("Vehicle updated successfully");

            SpeedLimitDTO speedLimitDTO = new SpeedLimitDTO();
            speedLimitDTO.setSpeed(speedLimitDTO.getSpeed());
            speedLimitDTO.setEngineSpeed(speedLimitDTO.getEngineSpeed());
            responseVehicleDTO.setSpeedLimit(speedLimitDTO);
            responseVehicleDTO.setSpeedLimitEncrypted("string"); //this needs to be solved
            responseVehicleDTO.setStationId(updatedVehicleDTO.getVehicleId());
            responseVehicleDTO.setErrorCode(0);
            responseVehicleDTO.setMessage("Vehicle updated successfully");
        } catch (Exception e) {
            responseStatus.setErrorCode(2);
            responseStatus.setMessage(e.getMessage());
            responseVehicleDTO.setStationId(null);
            responseVehicleDTO.setErrorCode(1);
            responseVehicleDTO.setMessage("Cannot update vehicle");
        }
        List<ResponseVehicleDTO> responseVehicleList = new ArrayList<>();
        responseVehicleList.add(responseVehicleDTO);
        response.setResponseStatus(responseStatus);
        response.setResponseVehicle(responseVehicleList);

        return response;
    }

    @Transactional
    public ResponseDTO deleteVehicleById(Long vehicleId) {
        ResponseDTO response = new ResponseDTO();
        ResponseStatusDTO responseStatus = new ResponseStatusDTO();
        ResponseVehicleDTO responseVehicleDTO = new ResponseVehicleDTO();
        try {
            Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
            if(vehicle != null) {
                vehicleRepository.delete(vehicle);
                // Setting up the success response using DTOs
                responseStatus.setErrorCode(0);
                responseStatus.setMessage("Vehicle deleted successfully");
                responseVehicleDTO.setMessage("Vehicle deleted successfully");
                responseVehicleDTO.setErrorCode(0);
                responseVehicleDTO.setStationId(vehicleId);
            } else {
                throw new Exception("Vehicle not found.");
            }
        } catch (Exception e) {
            responseStatus.setErrorCode(2);
            responseStatus.setMessage(e.getMessage());
            responseVehicleDTO.setStationId(null);
            responseVehicleDTO.setErrorCode(2);
            responseVehicleDTO.setMessage("Cannot delete vehicle");
        }
        List<ResponseVehicleDTO> responseVehicleList = new ArrayList<>();
        responseVehicleList.add(responseVehicleDTO);
        response.setResponseStatus(responseStatus);
        response.setResponseVehicle(responseVehicleList);

        return response;
    }
}

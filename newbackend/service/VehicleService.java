package org.but.eloryksauthorization.newbackend.service;

import org.but.eloryksauthorization.newbackend.api.*;
import org.but.eloryksauthorization.newbackend.data.entity.*;
import org.but.eloryksauthorization.newbackend.data.repository.VehicleRepository;
import org.but.eloryksauthorization.newbackend.mappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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

    @Transactional
    public List<VehicleDTO> findAll()
    {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicleMapper.toDTOList(vehicles);
    }

    @Transactional
    public VehicleDTO findById(Long id)
    {
        return vehicleRepository.findById(id)
                .map(vehicleMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle with ID " + id + " not found."));
    }

    @Transactional
    public List<VehicleDTO> save(VehicleRequestDTO vehicleRequestDTO, String responsible, LocalDateTime timestamp)
    {
        if(vehicleRequestDTO.getVehicle() == null || vehicleRequestDTO.getVehicle().isEmpty())
        {
            throw new IllegalArgumentException("Vehicle list in the request is either null or empty");
        }

        List<VehicleDTO> savedVehicles = new ArrayList<>();

        for (VehicleDTO vehicleDTO : vehicleRequestDTO.getVehicle())
        {
            EncryptionKeyDTO savedEncryptionKeyDTO = encryptionKeyService.save(vehicleDTO.getEncryptionKey());
            vehicleDTO.getEncryptionKey().setEncryptionKeyId(savedEncryptionKeyDTO.getEncryptionKeyId());

            SignKeyDTO savedSignKeyDTO = signKeyService.save(vehicleDTO.getSignKey());
            vehicleDTO.getSignKey().setSignKeyId(savedSignKeyDTO.getSignKeyId());

            PositionDTO savedPositionDTO = positionService.save(vehicleDTO.getPosition());
            vehicleDTO.getPosition().setPositionId(savedPositionDTO.getPositionId());

            SpeedLimitDTO defaultSpeedLimit = new SpeedLimitDTO();
            defaultSpeedLimit.setSpeedLimitId(vehicleDTO.getStationId());
            SpeedLimitDTO actualSpeedLimit = speedLimitService.save(defaultSpeedLimit);
            vehicleDTO.setSpeedLimit(actualSpeedLimit);

            Vehicle vehicle = vehicleMapper.toEntity(vehicleDTO);
            Vehicle savedVehicle = vehicleRepository.save(vehicle);
            savedVehicle.getPosition().setTimestamp(timestamp);
            savedVehicles.add(vehicleMapper.toDTO(savedVehicle));
        }

        return savedVehicles;
    }

    @Transactional
    public List<VehicleDTO> update(VehicleUpdateRequestDTO vehicleUpdateRequestDTO, String responsible, LocalDateTime timestamp)
    {
        if(vehicleUpdateRequestDTO.getVehicle() == null || vehicleUpdateRequestDTO.getVehicle().isEmpty())
        {
            throw new IllegalArgumentException("Vehicle list in the request is either NULL or empty");
        }

        List<VehicleDTO> updatedVehicles = new ArrayList<>();

        for (VehicleUpdateDTO updateVehicleDTO : vehicleUpdateRequestDTO.getVehicle())
        {
            if (updateVehicleDTO == null || updateVehicleDTO.getStationId() == null)
            {
                throw new IllegalArgumentException("VehicleUpdateDTO or StationId must not be null");
            }

            Vehicle existingVehicle = vehicleRepository.findById(updateVehicleDTO.getStationId())
                    .orElseThrow(() -> new EntityNotFoundException("Vehicle with ID " + updateVehicleDTO.getStationId() + " not found"));

            if (updateVehicleDTO.getSpeedLimit() != null) {
                updateVehicleDTO.getSpeedLimit().setSpeedLimitId(updateVehicleDTO.getStationId());
            }

            if (updateVehicleDTO.getPosition() != null) {
                updateVehicleDTO.getPosition().setPositionId(updateVehicleDTO.getStationId());
            }

            SpeedLimitDTO updatedSpeedLimitDTO = speedLimitService.update(updateVehicleDTO.getSpeedLimit());
            speedLimitMapper.updateEntityFromDTO(updatedSpeedLimitDTO, existingVehicle.getSpeedLimit());
            PositionDTO updatedPositionDTO = positionService.update(updateVehicleDTO.getPosition());
            positionMapper.updateEntityFromDTO(updatedPositionDTO, existingVehicle.getPosition());

            Vehicle updatedVehicle = vehicleRepository.save(existingVehicle);
            updatedVehicle.getPosition().setTimestamp(timestamp);
            updatedVehicles.add(vehicleMapper.toDTO(updatedVehicle));
        }

        return updatedVehicles;
    }

    @Transactional
    public List<VehicleDTO> getAllVehicles()
    {
        List<VehicleDTO> vehicleDTOs = findAll();
        return vehicleDTOs;
    }

    @Transactional
    public ResponsePostDeleteDTO createVehicle(VehicleRequestDTO vehicleRequestDTO, String responsible, LocalDateTime timestamp)
    {
        ResponsePostDeleteDTO response = new ResponsePostDeleteDTO();
        ResponseStatusDTO responseStatus = new ResponseStatusDTO();
        List<ResponseCreateDeleteDTO> responseVehicleList = new ArrayList<>();

        try {
            List<VehicleDTO> savedVehicles = save(vehicleRequestDTO, responsible, timestamp);
            for (VehicleDTO savedVehicleDTO : savedVehicles)
            {
                ResponseCreateDeleteDTO responseVehicleDTO = new ResponseCreateDeleteDTO();
                responseVehicleDTO.setStationId(savedVehicleDTO.getStationId());
                responseVehicleDTO.setErrorCode(0);
                responseVehicleDTO.setMessage("Vehicle created successfully");
                responseVehicleList.add(responseVehicleDTO);
            }
            responseStatus.setErrorCode(0);
            responseStatus.setMessage("Vehicles created successfully");
        } catch (Exception e)
        {
            responseStatus.setErrorCode(1);
            responseStatus.setMessage(e.getMessage());
            ResponseCreateDeleteDTO responseVehicleDTO = new ResponseCreateDeleteDTO();
            responseVehicleDTO.setStationId(null);
            responseVehicleDTO.setErrorCode(1);
            responseVehicleDTO.setMessage("Cannot create vehicles");
            responseVehicleList.add(responseVehicleDTO);
        }

        response.setResponseStatus(responseStatus);
        response.setResponseVehicle(responseVehicleList);
        return response;
    }

    @Transactional
    public ResponsePutDTO updateVehicle(VehicleUpdateRequestDTO vehicleUpdateRequestDTO, String responsible, LocalDateTime timestamp)
    {
        ResponsePutDTO response = new ResponsePutDTO();
        ResponseStatusDTO responseStatus = new ResponseStatusDTO();
        List<ResponseUpdateDTO> responseVehicleList = new ArrayList<>();

        try {
            List<VehicleDTO> updatedVehicles = update(vehicleUpdateRequestDTO, responsible, timestamp);
            for (VehicleDTO updatedVehicleDTO : updatedVehicles)
            {
                ResponseUpdateDTO responseUpdateDTO = new ResponseUpdateDTO();
                SpeedLimitDTO speedLimitDTO = new SpeedLimitDTO();
                speedLimitDTO.setSpeed(speedLimitDTO.getSpeed());
                speedLimitDTO.setEngineSpeed(speedLimitDTO.getEngineSpeed());
                responseUpdateDTO.setSpeedLimit(speedLimitDTO);
                responseUpdateDTO.setSpeedLimitEncrypted("string");
                responseUpdateDTO.setStationId(updatedVehicleDTO.getStationId());
                responseUpdateDTO.setErrorCode(0);
                responseUpdateDTO.setMessage("Vehicle updated successfully");
                responseVehicleList.add(responseUpdateDTO);
            }
            responseStatus.setErrorCode(0);
            responseStatus.setMessage("Vehicles updated successfully");
        } catch (Exception e)
        {
            responseStatus.setErrorCode(2);
            responseStatus.setMessage(e.getMessage());
            ResponseUpdateDTO responseUpdateDTO = new ResponseUpdateDTO();
            responseUpdateDTO.setStationId(null);
            responseUpdateDTO.setErrorCode(1);
            responseUpdateDTO.setMessage("Cannot update vehicles");
            responseVehicleList.add(responseUpdateDTO);
        }

        response.setResponseStatus(responseStatus);
        response.setResponseVehicle(responseVehicleList);
        return response;
    }

    @Transactional
    public ResponsePostDeleteDTO deleteVehiclesById(List<Long> stationIds, String responsible, LocalDateTime timestamp)
    {
        ResponsePostDeleteDTO response = new ResponsePostDeleteDTO();
        ResponseStatusDTO responseStatus = new ResponseStatusDTO();
        List<ResponseCreateDeleteDTO> responseVehicles = new ArrayList<>();

        for (Long stationId : stationIds)
        {
            ResponseCreateDeleteDTO responseVehicleDTO = new ResponseCreateDeleteDTO();
            try {
                Vehicle vehicle = vehicleRepository.findById(stationId).orElse(null);
                if(vehicle != null) {
                    vehicle.getPosition().setTimestamp(timestamp);
                    vehicleRepository.delete(vehicle);
                    responseVehicleDTO.setStationId(stationId);
                    responseVehicleDTO.setErrorCode(0);
                    responseVehicleDTO.setMessage("Vehicle deleted successfully");
                } else {
                    throw new Exception("Vehicle not found.");
                }
            } catch (Exception e) {
                responseVehicleDTO.setStationId(stationId);
                responseVehicleDTO.setErrorCode(2);
                responseVehicleDTO.setMessage("Cannot delete vehicle: " + e.getMessage());
            }
            responseVehicles.add(responseVehicleDTO);
        }

        responseStatus.setErrorCode(0);
        responseStatus.setMessage("Vehicle deleted successfully");
        response.setResponseStatus(responseStatus);
        response.setResponseVehicle(responseVehicles);

        return response;
    }
}
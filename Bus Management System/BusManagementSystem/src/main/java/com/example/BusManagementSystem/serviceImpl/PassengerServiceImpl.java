package com.example.BusManagementSystem.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BusManagementSystem.dto.RequestDTOs.PassengerRequestDTO;
import com.example.BusManagementSystem.dto.ResponseDTOs.PassengerResponseDTO;
import com.example.BusManagementSystem.model.Passenger;
import com.example.BusManagementSystem.repository.PassengrRepository;
import com.example.BusManagementSystem.service.PassengerService;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengrRepository passengerRepository;

    @Override
    public PassengerResponseDTO createPassenger(PassengerRequestDTO requestDTO) {
        if (passengerRepository.existsByEmail(requestDTO.getEmail())) {
            throw new RuntimeException("Email already exists: " + requestDTO.getEmail());
        }
        if (passengerRepository.existsByIdNumber(requestDTO.getIdNumber())) {
            throw new RuntimeException("ID number already exists: " + requestDTO.getIdNumber());
        }

        Passenger passenger = new Passenger();
        passenger.setFirstName(requestDTO.getFirstName());
        passenger.setLastName(requestDTO.getLastName());
        passenger.setEmail(requestDTO.getEmail());
        passenger.setPhoneNumber(requestDTO.getPhoneNumber());
        passenger.setIdNumber(requestDTO.getIdNumber());

        Passenger savedPassenger = passengerRepository.save(passenger);
        return mapToResponseDTO(savedPassenger);
    }

    @Override
    public PassengerResponseDTO getPassengerById(Long id) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found with id: " + id));
        return mapToResponseDTO(passenger);
    }

    @Override
    public List<PassengerResponseDTO> getAllPassengers() {
        return passengerRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PassengerResponseDTO updatePassenger(Long id, PassengerRequestDTO requestDTO) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found with id: " + id));

        passenger.setFirstName(requestDTO.getFirstName());
        passenger.setLastName(requestDTO.getLastName());
        passenger.setEmail(requestDTO.getEmail());
        passenger.setPhoneNumber(requestDTO.getPhoneNumber());
        passenger.setIdNumber(requestDTO.getIdNumber());

        Passenger updatedPassenger = passengerRepository.save(passenger);
        return mapToResponseDTO(updatedPassenger);
    }

    @Override
    public void deletePassenger(Long id) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found with id: " + id));
        passengerRepository.delete(passenger);
    }

    private PassengerResponseDTO mapToResponseDTO(Passenger passenger) {
        PassengerResponseDTO dto = new PassengerResponseDTO();
        dto.setId(passenger.getId());
        dto.setFirstName(passenger.getFirstName());
        dto.setLastName(passenger.getLastName());
        dto.setEmail(passenger.getEmail());
        dto.setPhoneNumber(passenger.getPhoneNumber());
        dto.setIdNumber(passenger.getIdNumber());
        return dto;
    }
}

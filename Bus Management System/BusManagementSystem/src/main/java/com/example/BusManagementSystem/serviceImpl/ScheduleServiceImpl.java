package com.example.BusManagementSystem.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BusManagementSystem.dto.RequestDTOs.ScheduleRequestDTO;
import com.example.BusManagementSystem.dto.ResponseDTOs.ScheduleResponseDTO;
import com.example.BusManagementSystem.model.Bus;
import com.example.BusManagementSystem.model.Driver;
import com.example.BusManagementSystem.model.Route;
import com.example.BusManagementSystem.model.Schedule;
import com.example.BusManagementSystem.repository.BusRepository;
import com.example.BusManagementSystem.repository.DriverRepository;
import com.example.BusManagementSystem.repository.RouteRepository;
import com.example.BusManagementSystem.repository.ScheduleRepository;
import com.example.BusManagementSystem.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public ScheduleResponseDTO createSchedule(ScheduleRequestDTO requestDTO) {
        Bus bus = busRepository.findById(requestDTO.getBusId())
                .orElseThrow(() -> new RuntimeException("Bus not found with id: " + requestDTO.getBusId()));

        Driver driver = driverRepository.findById(requestDTO.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found with id: " + requestDTO.getDriverId()));

        Route route = routeRepository.findById(requestDTO.getRouteId())
                .orElseThrow(() -> new RuntimeException("Route not found with id: " + requestDTO.getRouteId()));

        Schedule schedule = new Schedule();
        schedule.setBus(bus);
        schedule.setDriver(driver);
        schedule.setRoute(route);
        schedule.setDepartureDate(requestDTO.getDepartureDate());
        schedule.setDepartureTime(requestDTO.getDepartureTime());
        schedule.setArrivalTime(requestDTO.getArrivalTime());
        schedule.setStatus(requestDTO.getStatus());
        schedule.setAvailableSeats(requestDTO.getAvailableSeats());

        Schedule savedSchedule = scheduleRepository.save(schedule);
        return mapToResponseDTO(savedSchedule);
    }

    @Override
    public ScheduleResponseDTO getScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + id));
        return mapToResponseDTO(schedule);
    }

    @Override
    public List<ScheduleResponseDTO> getAllSchedules() {
        return scheduleRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ScheduleResponseDTO> getSchedulesByBusId(Long busId) {
        return scheduleRepository.findByBusId(busId)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ScheduleResponseDTO> getSchedulesByDriverId(Long driverId) {
        return scheduleRepository.findByDriverId(driverId)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ScheduleResponseDTO> getSchedulesByRouteId(Long routeId) {
        return scheduleRepository.findByRouteId(routeId)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleResponseDTO updateSchedule(Long id, ScheduleRequestDTO requestDTO) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + id));

        Bus bus = busRepository.findById(requestDTO.getBusId())
                .orElseThrow(() -> new RuntimeException("Bus not found with id: " + requestDTO.getBusId()));

        Driver driver = driverRepository.findById(requestDTO.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found with id: " + requestDTO.getDriverId()));

        Route route = routeRepository.findById(requestDTO.getRouteId())
                .orElseThrow(() -> new RuntimeException("Route not found with id: " + requestDTO.getRouteId()));

        schedule.setBus(bus);
        schedule.setDriver(driver);
        schedule.setRoute(route);
        schedule.setDepartureDate(requestDTO.getDepartureDate());
        schedule.setDepartureTime(requestDTO.getDepartureTime());
        schedule.setArrivalTime(requestDTO.getArrivalTime());
        schedule.setStatus(requestDTO.getStatus());
        schedule.setAvailableSeats(requestDTO.getAvailableSeats());

        Schedule updatedSchedule = scheduleRepository.save(schedule);
        return mapToResponseDTO(updatedSchedule);
    }

    @Override
    public void deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + id));
        scheduleRepository.delete(schedule);
    }

    private ScheduleResponseDTO mapToResponseDTO(Schedule schedule) {
        ScheduleResponseDTO dto = new ScheduleResponseDTO();
        dto.setId(schedule.getId());
        dto.setBusId(schedule.getBus().getId());
        dto.setBusNumber(schedule.getBus().getBusNumber());
        dto.setDriverId(schedule.getDriver().getId());
        dto.setDriverName(schedule.getDriver().getFirstName() + " " + schedule.getDriver().getLastName());
        dto.setRouteId(schedule.getRoute().getId());
        dto.setRouteName(schedule.getRoute().getRouteName());
        dto.setOrigin(schedule.getRoute().getOrigin());
        dto.setDestination(schedule.getRoute().getDestination());
        dto.setDepartureDate(schedule.getDepartureDate());
        dto.setDepartureTime(schedule.getDepartureTime());
        dto.setArrivalTime(schedule.getArrivalTime());
        dto.setStatus(schedule.getStatus());
        dto.setAvailableSeats(schedule.getAvailableSeats());
        return dto;
    }
}

package com.Mud.MudameB.infrastructure.service;

import com.Mud.MudameB.Domain.Entity.ClientEntity;
import com.Mud.MudameB.Domain.Entity.DriverEntity;
import com.Mud.MudameB.Domain.Entity.ReservationEntity;
import com.Mud.MudameB.Domain.Entity.TruckEntity;
import com.Mud.MudameB.Domain.repositories.ClientRepository;
import com.Mud.MudameB.Domain.repositories.DriverRepository;
import com.Mud.MudameB.Domain.repositories.ReservationRepository;
import com.Mud.MudameB.Domain.repositories.TruckRepository;
import com.Mud.MudameB.Utils.exceptions.BadRequestException;
import com.Mud.MudameB.Utils.messages.ErrorMessages;
import com.Mud.MudameB.api.dto.request.ClientReq;
import com.Mud.MudameB.api.dto.request.ReservationReq;
import com.Mud.MudameB.api.dto.response.*;
import com.Mud.MudameB.infrastructure.abstract_services.IReservationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservationService implements IReservationService {

    @Autowired
    private final ReservationRepository reservationRepository;

    @Autowired
    private final ClientRepository clientRepository;

    @Autowired
    private final DriverRepository driverRepository;

    private final TruckRepository truckRepository;

    @Autowired
    private ClientService clientService;


    @Autowired
    private DriverService driverService;


    @Override
    public List<ReservationResp> search(String name) {
        return List.of();
    }

    @Override
    public ReservationResp create(ReservationReq request) {

        // Obtener cliente
        ClientEntity client = this.clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("client")));

        // Obtener driver
        DriverEntity driver = this.driverRepository.findById(request.getDriverdI())
                .orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("driver")));

        TruckEntity truck = this.truckRepository.findById(request.getTruckId())
                .orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("truck")));


        ReservationEntity reservation = this.requestToEntity(request);

        reservation.setClient(client);
        reservation.setDriver(driver);
        reservation.setTruck(truck);


        return this.entityToResponseReservation(this.reservationRepository.save(reservation));



    }

    @Override
    public ReservationResp get(Long aLong) {
        return null;
    }

    @Override
    public ReservationResp update(ReservationReq request, Long aLong) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public Page<ReservationResp> getAll(int page, int size) {
        return null;
    }



    public ReservationResp entityToResponseReservation(ReservationEntity entity) {

        BasicClient client = new BasicClient();
        BeanUtils.copyProperties(entity.getClient(), client);

        DriverResp driver = new DriverResp();
        BeanUtils.copyProperties(entity.getDriver(), driver);

        // Construir y retornar un nuevo objeto ReservationToClient usando un builder
        return ReservationResp.builder()
                .id(entity.getId())
                .dateTime(entity.getDateTime())
                .origin(entity.getOrigin())
                .destiny(entity.getDestiny())
                .driver(driver)
                .build();
    }


    private ReservationEntity requestToEntity(ReservationReq request) {

        // Buscar las entidades relacionadas utilizando sus IDs
        ClientEntity client = clientService.findById(request.getClientId());
        //TruckEntity truck = truckService.findById(request.getTruckId());
        DriverEntity driver = driverService.findById(request.getDriverdI());

        // Crear una nueva instancia de ReservationEntity utilizando el constructor del patrón builder
        return ReservationEntity.builder()
                .dateTime(request.getDateTime())
                .origin(request.getOrigin())
                .destiny(request.getDestiny())
                .client(client)
                //.truck(truck)
                .driver(driver)
                .build();
    }


}









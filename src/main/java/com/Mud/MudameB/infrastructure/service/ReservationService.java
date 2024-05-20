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
import com.Mud.MudameB.api.dto.request.ReservationReq;
import com.Mud.MudameB.api.dto.response.*;
import com.Mud.MudameB.infrastructure.abstract_services.IReservationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private TruckService truckService;

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
        // Obtener driver
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
        ReservationEntity reservation = this.find(aLong);

        // Obtener cliente
        ClientEntity client = this.clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("client")));

        // Obtener driver
        DriverEntity driver = this.driverRepository.findById(request.getDriverdI())
                .orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("driver")));
        // Obtener driver
        TruckEntity truck = this.truckRepository.findById(request.getTruckId())
                .orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("truck")));

        reservation = this.requestToEntity(request);

        reservation.setClient(client);
        reservation.setDriver(driver);
        reservation.setTruck(truck);

        return this.entityToResponseReservation(this.reservationRepository.save(reservation));

    }

    @Override
    public void delete(Long aLong) {
        this.reservationRepository.delete(this.find(aLong));
    }

    @Override
    public Page<ReservationResp> getAll(int page, int size) {
        if (page < 0)
            page = 0;

        PageRequest pagination = PageRequest.of(page, size);

        return this.reservationRepository.findAll(pagination)
                .map(this::entityToResponseReservation);
    }

    public ReservationResp entityToResponseReservation(ReservationEntity entity) {

        BasicClient client = new BasicClient();
        BeanUtils.copyProperties(entity.getClient(), client);

        DriverResp driver = new DriverResp();
        BeanUtils.copyProperties(entity.getDriver(), driver);

        TruckResp truck = new TruckResp();
        BeanUtils.copyProperties(entity.getDriver(), truck);

        // Construir y retornar un nuevo objeto ReservationToClient usando un builder
        return ReservationResp.builder()
                .id(entity.getId())
                .dateTime(entity.getDateTime())
                .origin(entity.getOrigin())
                .destiny(entity.getDestiny())
                .driver(driver)
                .client(client)
                .truck(truck)
                .build();
    }

    private ReservationEntity requestToEntity(ReservationReq request) {

        // Buscar las entidades relacionadas utilizando sus IDs
        ClientEntity client = clientService.findById(request.getClientId());
        // TruckEntity truck = truckService.findById(request.getTruckId());
        DriverEntity driver = driverService.findById(request.getDriverdI());

        TruckEntity truck = truckService.findById(request.getTruckId());

        // Crear una nueva instancia de ReservationEntity utilizando el constructor del
        // patrón builder
        return ReservationEntity.builder()
                .dateTime(request.getDateTime())
                .origin(request.getOrigin())
                .destiny(request.getDestiny())
                .client(client)
                .truck(truck)
                .driver(driver)
                .price(request.getPrice())
                .build();
    }

    private ReservationEntity find(Long id) {
        return this.reservationRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("Servicio")));
    }

}

package com.Mud.MudameB.infrastructure.service;

import com.Mud.MudameB.Domain.Entity.ClientEntity;
import com.Mud.MudameB.Domain.Entity.DriverEntity;
import com.Mud.MudameB.Domain.Entity.ReservationEntity;
import com.Mud.MudameB.Domain.Entity.TruckEntity;
import com.Mud.MudameB.Domain.repositories.ClientRepository;
import com.Mud.MudameB.Domain.repositories.DriverRepository;
import com.Mud.MudameB.Domain.repositories.ReservationRepository;
import com.Mud.MudameB.Domain.repositories.TruckRepository;
import com.Mud.MudameB.api.dto.request.ClientReq;
import com.Mud.MudameB.api.dto.request.ReservationReq;
import com.Mud.MudameB.api.dto.response.*;
import com.Mud.MudameB.infrastructure.abstract_services.IReservationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private final TruckRepository truckRepository;




    @Override
    public List<ReservationResp> search(String name) {
        return List.of();
    }

    @Override
    public ReservationResp create(ReservationReq request) {
        return null;
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



    public ReservationResp entityToResponse(ReservationEntity entity) {
        // Crear una nueva instancia de ClientResp, que será la versión simplificada del objeto Truck de la entidad
        BasicClient client = new BasicClient();
        BeanUtils.copyProperties(entity.getClient(),client);
        // Crear una nueva instancia de TruckResp, que será la versión simplificada del objeto Truck de la entidad
        TruckResp truck = new TruckResp();
        // Copiar las propiedades del objeto Truck de la entidad a la instancia de TruckResp
        BeanUtils.copyProperties(entity.getTruck(), truck);
        // Crear una nueva instancia de DriverResp, que será la versión simplificada del objeto Driver de la entidad
        DriverResp driver = new DriverResp();
        // Copiar las propiedades del objeto Driver de la entidad a la instancia de DriverResp
        BeanUtils.copyProperties(entity.getDriver(), driver);



        // Construir y retornar un nuevo objeto ReservationToClient usando un builder
        return ReservationResp.builder()
                .id(entity.getId())          // Asignar el ID de la entidad a la respuesta
                .dateTime(entity.getDateTime())  // Asignar la fecha y hora de la entidad a la respuesta
                .origin(entity.getOrigin())      // Asignar el origen de la entidad a la respuesta
                .destiny(entity.getDestiny())// Asignar el destino de la entidad a la respuesta
                .truck(truck)
                .driver(driver)
                .client(client)
                .build();
    }

    /*
    private ReservationEntity requestToEntity(ReservationReq request) {
        // Buscar las entidades relacionadas utilizando sus IDs
       // ClientEntity client = clientService.findById(request.getClientId());
        //TruckEntity truck = Trucks.findById(request.getTruckId());
       // DriverEntity driver = driverService.findById(request.getDriverdI());
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
    //BASARSE EN APPOINTMENT
*/
}

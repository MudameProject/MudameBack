package com.Mud.MudameB.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientReq {
    @NotBlank(message = "el nombre es requerido")
    private String name;
    @NotBlank(message = "el appelido es requerido")
    private String lastName;
    @Email(message = "el email no es valido")
    @Size(min = 5, max = 100, message = "el email debe tener entre 5 y 100 caracteres")
    private String email;
    @Size(min = 10, max = 20, message = "el telefono debe tener entre 10 y 20 caracteres")
    private Integer phoneNumber;
    @NotBlank(message = "el nombre es requerido")
    private String address;
    @NotNull(message = "el código postal es requerido")
    private Integer zipCode;
}

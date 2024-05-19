package com.Mud.MudameB.api.dto.request;

import jakarta.validation.constraints.*;
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
    @Min(value = 1000000000, message = "El número de teléfono debe tener exactamente 10 dígitos")
    @Max(value = 9999999999L, message = "El número de teléfono debe tener exactamente 10 dígitos")
    @Digits(integer = 10, fraction = 0, message = "El número de teléfono debe contener exactamente 10 dígitos")
    private Long phoneNumber;
    private String address;
    @NotNull(message = "el código postal es requerido")
    private Integer zipCode;
}

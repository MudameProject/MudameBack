package com.Mud.MudameB.api.dto.request;

import com.Mud.MudameB.Utils.enums.Auxiliar;
import com.Mud.MudameB.Utils.enums.LicenseType;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DriverReq {
    @NotBlank(message = "se nesecita el nombre")
    private String name;
    @NotBlank(message = "se nesecita el apellido")
    private String lastName;
    @Min(value = 1000000000, message = "El número de teléfono debe tener exactamente 10 dígitos")
    @Max(value = 9999999999L, message = "El número de teléfono debe tener exactamente 10 dígitos")
    private Integer phoneNumber;
    @NotNull(message = "El tipo de licencia no puede ser nulo")
    private LicenseType licenseType;
    @NotBlank(message = "el numero de licencia es requerido")
    private String license;
    private Auxiliar auxiliar;
}

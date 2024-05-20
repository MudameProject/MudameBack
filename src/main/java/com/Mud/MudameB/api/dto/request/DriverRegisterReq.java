package com.Mud.MudameB.api.dto.request;

import com.Mud.MudameB.Utils.enums.Auxiliar;
import com.Mud.MudameB.Utils.enums.LicenseType;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class DriverRegisterReq extends RegisterReq{
    @NotBlank(message = "se nesecita el nombre")
    private String name;
    @NotBlank(message = "se nesecita el apellido")
    private String lastName;
    private Integer phoneNumber;
    @NotBlank(message = "el tipo de licencia es requerido")
    private LicenseType licenseType;
    @NotBlank(message = "el numero de licencia es requerido")
    private String license;
    private Auxiliar auxiliar;
}

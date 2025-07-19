package boss.team.patientservice.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Builder @Data
public class PatientRequestDTO {

    @NotBlank(message = "name cannot be blank")
    @Size(max = 100, message = "name cannot exceed 100 characters")
    private String name;


    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "address cannot be blank")
    private String address;
    @NotBlank(message = "Date of birth cannot be empty")
    private String dateOfBirth;
}

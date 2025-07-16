package boss.team.patientservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Builder @Data
public class PatientResponseDTO {
    private String id;
    private String name;
    private String email;
    private String address;
    private String dateOfBirth;
}

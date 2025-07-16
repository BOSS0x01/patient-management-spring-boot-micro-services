package boss.team.patientservice.mappers;

import boss.team.patientservice.dtos.PatientResponseDTO;
import boss.team.patientservice.entities.Patient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PatientMapper {

    public PatientResponseDTO toPatientResponseDTO(Patient patient) {
        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
        BeanUtils.copyProperties(patient, patientResponseDTO);

        patientResponseDTO.setId(patient.getId().toString());
        patientResponseDTO.setDateOfBirth(patient.getDateOfBirth().toString());
        return patientResponseDTO;
    }

    public Patient toPatient(PatientResponseDTO patientResponseDTO) {
        Patient patient = new Patient();
        BeanUtils.copyProperties(patientResponseDTO, patient);
        return patient;
    }
}

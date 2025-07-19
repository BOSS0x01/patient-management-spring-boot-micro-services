package boss.team.patientservice.mappers;

import boss.team.patientservice.dtos.PatientRequestDTO;
import boss.team.patientservice.dtos.PatientResponseDTO;
import boss.team.patientservice.entities.Patient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PatientMapper {

    public PatientResponseDTO toPatientResponseDTO(Patient patient) {
        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
        BeanUtils.copyProperties(patient, patientResponseDTO);

        patientResponseDTO.setId(patient.getId().toString());
        patientResponseDTO.setDateOfBirth(patient.getDateOfBirth().toString());
        return patientResponseDTO;
    }

    public Patient toPatient(PatientRequestDTO patientRequestDTO) {
        Patient patient = new Patient();
        BeanUtils.copyProperties(patientRequestDTO, patient);
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        return patient;
    }

}

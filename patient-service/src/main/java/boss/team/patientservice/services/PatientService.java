package boss.team.patientservice.services;

import boss.team.patientservice.dtos.PatientResponseDTO;
import boss.team.patientservice.entities.Patient;
import boss.team.patientservice.mappers.PatientMapper;
import boss.team.patientservice.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientService {
    PatientRepository patientRepository;
    PatientMapper patientMapper;

    public List<PatientResponseDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(patientMapper::toPatientResponseDTO).toList();
    }
}

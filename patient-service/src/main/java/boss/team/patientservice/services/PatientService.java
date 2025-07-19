package boss.team.patientservice.services;

import boss.team.patientservice.dtos.PatientRequestDTO;
import boss.team.patientservice.dtos.PatientResponseDTO;
import boss.team.patientservice.entities.Patient;
import boss.team.patientservice.exception.EmailAlreadyExistsException;
import boss.team.patientservice.exception.PatientNotFoundException;
import boss.team.patientservice.mappers.PatientMapper;
import boss.team.patientservice.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PatientService {
    PatientRepository patientRepository;
    PatientMapper patientMapper;

    public List<PatientResponseDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(patientMapper::toPatientResponseDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) throws EmailAlreadyExistsException {
        isEmailUsed(patientRequestDTO.getEmail());
        Patient newPatient = patientRepository.save(patientMapper.toPatient(patientRequestDTO));
        newPatient.setRegistrationDate(LocalDate.now());
        return patientMapper.toPatientResponseDTO(newPatient);
    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) throws EmailAlreadyExistsException, PatientNotFoundException {
        Patient patient = doesPatientExist(id);

        if (!patient.getEmail().equals(patientRequestDTO.getEmail())) {
            isEmailUsed(patientRequestDTO.getEmail());
            patient.setEmail(patientRequestDTO.getEmail());
        }

        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        return patientMapper.toPatientResponseDTO(patientRepository.save(patient));
    }

    public void deletePatient(UUID id) throws PatientNotFoundException {
        patientRepository.delete(doesPatientExist(id));
    }

    private Patient doesPatientExist(UUID id) {
        return patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not found with this id : "));
    }

    private void isEmailUsed(String email) {
        if (patientRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException(email + " has already been used");
        }
    }
}

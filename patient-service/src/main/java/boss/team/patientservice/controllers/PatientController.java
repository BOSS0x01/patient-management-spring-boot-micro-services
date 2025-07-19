package boss.team.patientservice.controllers;

import boss.team.patientservice.dtos.PatientRequestDTO;
import boss.team.patientservice.dtos.PatientResponseDTO;
import boss.team.patientservice.exception.EmailAlreadyExistsException;
import boss.team.patientservice.exception.PatientNotFoundException;
import boss.team.patientservice.services.PatientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/patients")
@AllArgsConstructor
public class PatientController {
    PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO) throws EmailAlreadyExistsException {
        return ResponseEntity.ok(patientService.createPatient(patientRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id, @Valid @RequestBody PatientRequestDTO patientRequestDTO) {
        return  ResponseEntity.ok(patientService.updatePatient(id, patientRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) throws PatientNotFoundException {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }


}

package com.bartek.clinicadministrationapi.controllers
import com.bartek.clinicadministrationapi.domain.dtos.PatientDTO
import com.bartek.clinicadministrationapi.services.PatientService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class PatientController(val service: PatientService) {

    @GetMapping("/patients/{id}")
    fun getPatientById(@PathVariable id: Long): ResponseEntity<PatientDTO> {
        return service.getPatientById(id)
    }

    @PostMapping("/patients")
    fun addPatient(@RequestBody patientDTO: PatientDTO): ResponseEntity<PatientDTO> {

        return service.addPatient(patientDTO)
    }

    @DeleteMapping("/patients/{id}")
    fun removePatientById(@PathVariable id: Long): ResponseEntity<PatientDTO>{
        return service.deletePatientById(id)

    }

    @PutMapping("/patients")
    fun updatePatient(@RequestBody patientDTO: PatientDTO): ResponseEntity<PatientDTO>? {

        return service.updatePatient(patientDTO)

    }

}


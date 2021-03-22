package com.bartek.clinicadministrationapi.controllers

import com.bartek.clinicadministrationapi.domain.dtos.PatientDTO
import com.bartek.clinicadministrationapi.services.PatientService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class PatientController(val service: PatientService) {

    @GetMapping("/patients/{id}")
    fun getPatientById(@PathVariable id: Long): ResponseEntity<PatientDTO> {
        return service.getPatientById(id)
    }

    @GetMapping("/patients")
    fun getAllPatients(): ResponseEntity<Set<PatientDTO>> {

        return service.getAllPatients()
    }

    @PostMapping("/patients")
    fun addPatient(@RequestBody @Valid patientDTO: PatientDTO): ResponseEntity<PatientDTO> {

        return service.addPatient(patientDTO)
    }

    @DeleteMapping("/patients/{id}")
    fun removePatientById(@PathVariable id: Long): ResponseEntity<PatientDTO> {
        return service.deletePatientById(id)
    }

    @PutMapping("/patients")
    fun updatePatient(@RequestBody patientDTO: PatientDTO): ResponseEntity<PatientDTO>? {

        return service.updatePatient(patientDTO)

    }
}


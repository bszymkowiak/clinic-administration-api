package com.bartek.clinicadministrationapi.controllers

import com.bartek.clinicadministrationapi.domain.dtos.PatientDTO
import com.bartek.clinicadministrationapi.services.PatientService
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class PatientController(val service: PatientService) {

    @GetMapping("/patients/{id}")
    fun getPatientById(@PathVariable id: Long): ResponseEntity<PatientDTO> {
        return service.getPatientById(id)
            .map { patientDTO -> ResponseEntity(patientDTO, HttpStatus.OK) }
            .orElseGet { ResponseEntity(HttpStatus.NOT_FOUND) }
    }

    @GetMapping("/patients")
    fun getAllPatients(): ResponseEntity<Set<PatientDTO>> {

        return service.getAllPatients()
            .map { patientDTO -> ResponseEntity(patientDTO, HttpStatus.OK) }
            .orElseGet { ResponseEntity(HttpStatus.NOT_FOUND) }
    }

    @PostMapping("/patients")
    fun addPatient(@RequestBody @Valid patientDTO: PatientDTO): ResponseEntity<PatientDTO> {

        return service.addPatient(patientDTO)
            .map { patientDTO -> ResponseEntity(patientDTO, HttpStatus.OK) }
            .orElseGet { ResponseEntity(HttpStatus.BAD_REQUEST) }
    }

    @DeleteMapping("/patients/{id}")
    fun deletePatientById(@PathVariable id: Long): ResponseEntity<PatientDTO> {
        return service.deletePatientById(id)
            .map { patientDTO -> ResponseEntity(patientDTO, HttpStatus.OK) }
            .orElseGet { ResponseEntity(HttpStatus.NOT_FOUND) }
    }

    @PutMapping("/patients")
    fun updatePatient(@RequestBody @Valid patientDTO: PatientDTO): ResponseEntity<PatientDTO>? {

        return service.updatePatient(patientDTO)
            ?.map { patientDTO -> ResponseEntity(patientDTO, HttpStatus.OK) }
            ?.orElseGet { ResponseEntity(HttpStatus.NOT_FOUND) }
    }

    @ExceptionHandler(EmptyResultDataAccessException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleEmptyResultDataAccessException(exception: EmptyResultDataAccessException): ResponseEntity<String> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(exception.message)
    }
}


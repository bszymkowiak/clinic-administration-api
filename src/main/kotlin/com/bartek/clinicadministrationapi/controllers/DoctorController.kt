package com.bartek.clinicadministrationapi.controllers

import com.bartek.clinicadministrationapi.domain.dtos.DoctorDTO
import com.bartek.clinicadministrationapi.services.DoctorService
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class DoctorController(
    val service: DoctorService
) {

    @GetMapping("/doctors/{id}")
    fun getDoctorById(@PathVariable id: Long): ResponseEntity<DoctorDTO> {
        return service.getDoctorById(id)
            .map { doctorDTO -> ResponseEntity(doctorDTO, HttpStatus.OK) }
            .orElseGet { ResponseEntity(HttpStatus.NOT_FOUND) }
    }

    @GetMapping("/doctors")
    fun getAllDoctors(): ResponseEntity<Set<DoctorDTO>> {
        return service.getAllDoctors()
            .map { set -> ResponseEntity(set, HttpStatus.OK) }
            .orElseGet { ResponseEntity(HttpStatus.NOT_FOUND) }
    }

    @PostMapping("/doctors")
    fun addDoctor(@RequestBody @Valid doctorDTO: DoctorDTO): ResponseEntity<DoctorDTO> {
        return service.addDoctor(doctorDTO)
            .map { doctorDTO -> ResponseEntity(doctorDTO, HttpStatus.OK) }
            .orElseGet { ResponseEntity(HttpStatus.BAD_REQUEST) }
    }

    @DeleteMapping("/doctors/{id}")
    fun deleteDoctor(@PathVariable id: Long): ResponseEntity<DoctorDTO> {

        return service.deleteDoctorById(id)
            .map { doctorDTO -> ResponseEntity(doctorDTO, HttpStatus.OK) }
            .orElseGet { ResponseEntity(HttpStatus.NOT_FOUND) }
    }

    @PutMapping("/doctors")
    fun updateDoctor(@RequestBody @Valid doctorDTO: DoctorDTO): ResponseEntity<DoctorDTO>? {

        return service.updateDoctor(doctorDTO)
            ?.map { doctorDTO -> ResponseEntity(doctorDTO, HttpStatus.OK) }
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
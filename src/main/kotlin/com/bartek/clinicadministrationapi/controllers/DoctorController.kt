package com.bartek.clinicadministrationapi.controllers

import com.bartek.clinicadministrationapi.domain.dtos.DoctorDTO
import com.bartek.clinicadministrationapi.services.DoctorService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class DoctorController(
    val service: DoctorService
) {

    @GetMapping("/doctors/{id}")
    fun getDoctorById(@PathVariable id: Long): ResponseEntity<DoctorDTO> {
        return service.findDoctorById(id)
    }

    @GetMapping("/doctors")
    fun getAllDoctors(): ResponseEntity<Set<DoctorDTO>> {
        return service.getDoctors()
    }

    @PostMapping("/doctors")
    fun addDoctor(@RequestBody doctorDTO: DoctorDTO): ResponseEntity<DoctorDTO> {
        return service.addDoctorToDb(doctorDTO)
    }

    @DeleteMapping("/doctors/{id}")
    fun deleteDoctor(@PathVariable id: Long): ResponseEntity<DoctorDTO> {
        return service.deleteDoctorById(id)
    }

    @PutMapping("/doctors")
    fun updateDoctor(@RequestBody doctorDTO: DoctorDTO): ResponseEntity<DoctorDTO>? {

        return service.updateDoctor(doctorDTO)

    }

}
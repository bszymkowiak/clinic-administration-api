package com.bartek.clinicadministrationapi.controllers

import com.bartek.clinicadministrationapi.domain.dtos.VisitDTO
import com.bartek.clinicadministrationapi.services.VisitService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class VisitController(val service: VisitService) {

    @PostMapping("/visits")
    fun addVisit(@RequestBody @Valid visitDTO: VisitDTO): ResponseEntity<VisitDTO>? {
        return service.addVisit(visitDTO)
            ?.map { visitDTO -> ResponseEntity(visitDTO, HttpStatus.OK) }
            ?.orElseGet { ResponseEntity(HttpStatus.BAD_REQUEST) }
    }

    @GetMapping("/visits/{id}")
    fun getVisitsByPatientId(@PathVariable id: Long): ResponseEntity<Set<VisitDTO>> {
        return service.getAllVisitsByPatientId(id)
            .map { visitDTO -> ResponseEntity(visitDTO, HttpStatus.OK) }
            .orElseGet { ResponseEntity(HttpStatus.NOT_FOUND) }
    }

    @GetMapping("/visits")
    fun getAllVisits(): ResponseEntity<Set<VisitDTO>> {
        return service.getAllVisits()
            .map { visitDTO -> ResponseEntity(visitDTO, HttpStatus.OK) }
            .orElseGet { ResponseEntity(HttpStatus.NOT_FOUND) }
    }

    @DeleteMapping("/visits/{id}")
    fun deleteVisitById(@PathVariable id: Long): ResponseEntity<VisitDTO> {
        return service.deleteVisitById(id)
            .map { visitDTO -> ResponseEntity(visitDTO, HttpStatus.OK) }
            .orElseGet { ResponseEntity(HttpStatus.NOT_FOUND) }
    }

    @PutMapping("/visits")
    fun updateVisit(@RequestBody visitDTO: VisitDTO): ResponseEntity<VisitDTO>? {
        return service.updateVisit(visitDTO)
            ?.map { visitDTO -> ResponseEntity(visitDTO, HttpStatus.OK) }
            ?.orElseGet { ResponseEntity(HttpStatus.NOT_FOUND) }
    }
}
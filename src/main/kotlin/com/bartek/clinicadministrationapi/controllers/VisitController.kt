package com.bartek.clinicadministrationapi.controllers

import com.bartek.clinicadministrationapi.domain.dtos.VisitDTO
import com.bartek.clinicadministrationapi.services.VisitService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class VisitController(val service: VisitService) {

    @PostMapping("/visits")
    fun addVisitToDb(@RequestBody visitDTO: VisitDTO): ResponseEntity<VisitDTO> {
        return service.addVisit(visitDTO)
    }

    @GetMapping("/visits/{id}")
    fun getVisitsByPatientId(@PathVariable id: Long): ResponseEntity<Set<VisitDTO>> {
        return service.getAllVisitsByPatientId(id)
    }

    @GetMapping("/visits")
    fun getAllVisits(): ResponseEntity<Set<VisitDTO>> {
        return service.getAllVisits()
    }

    @DeleteMapping("/visits/{id}")
    fun deleteVisitById(@PathVariable id: Long): ResponseEntity<VisitDTO> {
        return service.deleteVisitById(id)
    }

    @PutMapping("/visits")
    fun updateVisit(@RequestBody visitDTO: VisitDTO): ResponseEntity<VisitDTO>? {
        return service.updateVisit(visitDTO)
    }
}
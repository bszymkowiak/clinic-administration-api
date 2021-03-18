package com.bartek.clinicadministrationapi.services

import com.bartek.clinicadministrationapi.domain.dtos.VisitDTO
import com.bartek.clinicadministrationapi.mappers.VisitMapper
import com.bartek.clinicadministrationapi.repositories.VisitRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class VisitService(
    val repository: VisitRepository,
    val visitMapper: VisitMapper
) {

    fun addVisit(visitDTO: VisitDTO): ResponseEntity<VisitDTO> {
        repository.save(visitMapper.mapDTOToDAO(visitDTO))
        return ResponseEntity(HttpStatus.OK)
    }

    fun getAllVisitsByPatientId(id: Long): ResponseEntity<Set<VisitDTO>> {
        val set: Set<VisitDTO> = repository
            .findAll()
            .map { visitDAO -> visitMapper.mapDAOToDTO(visitDAO) }
            .filter { visit -> visit.patient.id == id }
            .toSet()

        return ResponseEntity(set, HttpStatus.OK)

    }

    fun deleteVisitById(id: Long): ResponseEntity<VisitDTO> {
        repository.deleteById(id)
        return ResponseEntity(HttpStatus.OK)
    }

    fun updateVisit(visitDTO: VisitDTO): ResponseEntity<VisitDTO>? {
        return visitDTO.id?.let {
            repository.findById(it)
                .map { repository.save(visitMapper.mapDTOToDAO(visitDTO)) }
                .map { visitDAO -> visitMapper.mapDAOToDTO(visitDAO) }
                .map { visitDTO -> ResponseEntity(visitDTO, HttpStatus.OK) }
                .orElseGet { ResponseEntity(HttpStatus.NOT_FOUND) }
        }
    }

    fun getAllVisits(): ResponseEntity<Set<VisitDTO>> {
        val set: Set<VisitDTO> = repository.findAll()
            .map { visitDAO -> visitMapper.mapDAOToDTO(visitDAO) }
            .toSet()

        return ResponseEntity(set, HttpStatus.OK)
    }
}
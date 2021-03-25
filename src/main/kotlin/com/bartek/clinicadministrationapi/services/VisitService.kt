package com.bartek.clinicadministrationapi.services

import com.bartek.clinicadministrationapi.domain.dtos.VisitDTO
import com.bartek.clinicadministrationapi.mappers.VisitMapper
import com.bartek.clinicadministrationapi.repositories.VisitRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class VisitService(
    val visitRepository: VisitRepository,
    val visitMapper: VisitMapper
) {

    fun addVisit(visitDTO: VisitDTO): ResponseEntity<VisitDTO> {

        val isTrue: VisitDTO? = visitDTO.doctor.id?.let {
            visitRepository.findVisitsByDateAndDateTimeAndDoctorId(visitDTO.date, visitDTO.dateTime, it)
        }?.let { visitMapper.mapDAOToDTO(it) }

        return if (isTrue != null) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            visitRepository.save(visitMapper.mapDTOToDAO(visitDTO))
            ResponseEntity(HttpStatus.OK)
        }
    }

    fun getAllVisitsByPatientId(id: Long): ResponseEntity<Set<VisitDTO>> {
        val set: Set<VisitDTO> = visitRepository.findVisitsByPatientId(id)
            .map { visitDAO -> visitMapper.mapDAOToDTO(visitDAO) }
            .toSet()

        return ResponseEntity(set, HttpStatus.OK)

    }

    fun deleteVisitById(id: Long): ResponseEntity<VisitDTO> {
        visitRepository.deleteById(id)
        return ResponseEntity(HttpStatus.OK)
    }

    fun updateVisit(visitDTO: VisitDTO): ResponseEntity<VisitDTO>? {

        val isTrue: VisitDTO? =
            visitDTO.doctor.id?.let {
                visitRepository.findVisitsByDateAndDateTimeAndDoctorId(visitDTO.date, visitDTO.dateTime, it)
                    ?.let { visitMapper.mapDAOToDTO(it) }
            }

        if (isTrue != null) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        } else {
            visitDTO.id?.let {
                visitRepository.findById(it)
                    .map { visitRepository.save(visitMapper.mapDTOToDAO(visitDTO)) }
            }
            return ResponseEntity(HttpStatus.OK)
        }

    }

    fun getAllVisits(): ResponseEntity<Set<VisitDTO>> {
        val set: Set<VisitDTO> = visitRepository.findAll()
            .map { visitDAO -> visitMapper.mapDAOToDTO(visitDAO) }
            .toSet()

        return ResponseEntity(set, HttpStatus.OK)
    }
}
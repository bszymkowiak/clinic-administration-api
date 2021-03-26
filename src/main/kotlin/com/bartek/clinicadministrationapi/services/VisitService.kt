package com.bartek.clinicadministrationapi.services

import com.bartek.clinicadministrationapi.domain.dtos.VisitDTO
import com.bartek.clinicadministrationapi.mappers.VisitMapper
import com.bartek.clinicadministrationapi.repositories.VisitRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class VisitService(
    val visitRepository: VisitRepository,
    val visitMapper: VisitMapper
) {

    fun addVisit(visitDTO: VisitDTO): ResponseEntity<VisitDTO> {

        val visitDb: VisitDTO? = visitDTO.doctor.id?.let {
            visitRepository.findVisitsByDateAndDateTimeAndDoctorId(visitDTO.date, visitDTO.dateTime, it)
        }?.let { visitMapper.mapDAOToDTO(it) }

        if (visitDb != null) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        } else {
            visitRepository.save(visitMapper.mapDTOToDAO(visitDTO))
            return ResponseEntity(HttpStatus.OK)
        }
    }

    fun getAllVisitsByPatientId(id: Long): Optional<Set<VisitDTO>> {

        val optVisit = Optional.of(visitRepository.findVisitsByPatientId(id)
            .map { visitDAO -> visitMapper.mapDAOToDTO(visitDAO) }
            .toSet())

        if (optVisit.isPresent) {
            return optVisit
        } else {
            throw EmptyResultDataAccessException("Visit not found by patient id", 0)
        }
    }

    fun deleteVisitById(id: Long): Optional<VisitDTO> {

        val visitOpt = visitRepository.findById(id)
            .map { visitDAO -> visitMapper.mapDAOToDTO(visitDAO) }

        if (visitOpt.isPresent) {
            visitRepository.deleteById(id)
            return visitOpt
        } else {
            throw EmptyResultDataAccessException("Visit not found by this id", 0)
        }
    }

    fun updateVisit(visitDTO: VisitDTO): Optional<VisitDTO>? {

        val visitDb = visitDTO.doctor.id?.let {
            visitRepository.findVisitsByDateAndDateTimeAndDoctorId(visitDTO.date, visitDTO.dateTime, it)
        }?.let { visitMapper.mapDAOToDTO(it) }?.let { Optional.of(it) }

        if (visitDb != null) {
            visitDTO.id?.let {
                visitRepository.findById(it)
                    .map { visitRepository.save(visitMapper.mapDTOToDAO(visitDTO)) }
            }
        }
        return visitDb

    }

    fun getAllVisits(): Optional<Set<VisitDTO>> {

        val optSet = Optional.of(visitRepository.findAll()
            .map { visitDAO -> visitMapper.mapDAOToDTO(visitDAO) }
            .toSet())

        return optSet
    }
}
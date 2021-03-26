package com.bartek.clinicadministrationapi.services

import com.bartek.clinicadministrationapi.domain.dtos.VisitDTO
import com.bartek.clinicadministrationapi.mappers.VisitMapper
import com.bartek.clinicadministrationapi.repositories.VisitRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class VisitService(
    val visitRepository: VisitRepository,
    val visitMapper: VisitMapper
) {

    fun addVisit(visitDTO: VisitDTO): Optional<VisitDTO>? {

        val optVisit = visitDTO.id?.let {
            visitRepository.findVisitsByDateAndDateTimeAndDoctorId(
                visitDTO.date, visitDTO.dateTime, it
            )
        }
            ?.map { visitDAO -> visitMapper.mapDAOToDTO(visitDAO) }

        if (optVisit == null) {
            visitRepository.save(visitMapper.mapDTOToDAO(visitDTO))
        }

        return optVisit
    }

    fun getAllVisitsByPatientId(id: Long): Optional<Set<VisitDTO>> {

        return Optional.of(visitRepository.findVisitsByPatientId(id)
            .map { visitDAO -> visitMapper.mapDAOToDTO(visitDAO) }
            .toSet())
    }

    fun deleteVisitById(id: Long): Optional<VisitDTO> {

        val visitOpt = visitRepository.findById(id)
            .map { visitDAO -> visitMapper.mapDAOToDTO(visitDAO) }

        if (visitOpt.isPresent) {
            visitRepository.deleteById(id)
        }

        return visitOpt
    }

    fun updateVisit(visitDTO: VisitDTO): Optional<VisitDTO>? {

        val optVisit = visitDTO.id?.let {
            visitRepository.findVisitsByDateAndDateTimeAndDoctorId(
                visitDTO.date, visitDTO.dateTime, it
            )
        }
            ?.map { visitDAO -> visitMapper.mapDAOToDTO(visitDAO) }

        if (optVisit != null) {
            visitDTO.id?.let {
                visitRepository.findById(it)
                    .map { visitRepository.save(visitMapper.mapDTOToDAO(visitDTO)) }
            }
        }
        return optVisit
    }

    fun getAllVisits(): Optional<Set<VisitDTO>> {

        return Optional.of(visitRepository.findAll()
            .map { visitDAO -> visitMapper.mapDAOToDTO(visitDAO) }
            .toSet())
    }
}
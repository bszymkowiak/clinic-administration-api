package com.bartek.clinicadministrationapi.services

import com.bartek.clinicadministrationapi.domain.dtos.PatientDTO
import com.bartek.clinicadministrationapi.mappers.PatientMapper
import com.bartek.clinicadministrationapi.repositories.PatientRepository
import com.bartek.clinicadministrationapi.repositories.VisitRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class PatientService(
    val patientRepository: PatientRepository,
    val patientMapper: PatientMapper,
    val visitRepository: VisitRepository
) {

    fun getPatientById(id: Long): Optional<PatientDTO> {

        return patientRepository.findById(id)
            .map { patientDAO -> patientMapper.mapDAOToDTO(patientDAO) }
    }

    fun addPatient(patientDTO: PatientDTO): Optional<PatientDTO> {

        val opt = Optional.of(patientRepository.save(patientMapper.mapDTOToDAO(patientDTO)))

        return opt
            .map { patientDAO -> patientMapper.mapDAOToDTO(patientDAO) }
    }

    fun deletePatientById(id: Long): Optional<PatientDTO> {

        val patientOpt = patientRepository.findById(id)
            .map { patientDAO -> patientMapper.mapDAOToDTO(patientDAO) }

        if (!patientOpt.equals(null)) {
            visitRepository.deleteVisitsByPatientId(id)
            patientRepository.deleteById(id)
        }

        return patientOpt
    }

    fun updatePatient(patientDTO: PatientDTO): Optional<PatientDTO>? {

        val patientOpt = patientDTO.id?.let {
            patientRepository.findById(it)
                .map { patientDAO -> patientMapper.mapDAOToDTO(patientDAO) }
        }

        if (patientOpt != null) {
            patientRepository.save(patientMapper.mapDTOToDAO(patientDTO))
        }

        return patientOpt
    }

    fun getAllPatients(): Optional<Set<PatientDTO>> {

        return Optional.of(patientRepository
            .findAll()
            .map { patientDAO -> patientMapper.mapDAOToDTO(patientDAO) }
            .toSet())
    }
}
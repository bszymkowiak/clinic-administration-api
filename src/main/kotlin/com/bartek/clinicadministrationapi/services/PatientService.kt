package com.bartek.clinicadministrationapi.services

import com.bartek.clinicadministrationapi.domain.dtos.PatientDTO
import com.bartek.clinicadministrationapi.mappers.PatientMapper
import com.bartek.clinicadministrationapi.repositories.PatientRepository
import com.bartek.clinicadministrationapi.repositories.VisitRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class PatientService(
    val patientRepository: PatientRepository,
    val patientMapper: PatientMapper,
    val visitRepository: VisitRepository
) {

    fun getPatientById(id: Long): ResponseEntity<PatientDTO> {
        return patientRepository.findById(id)
            .map { patientDAO -> patientMapper.mapDAOToDTO(patientDAO) }
            .map { patientDTO -> ResponseEntity(patientDTO, HttpStatus.OK) }
            .orElseGet { ResponseEntity(HttpStatus.NOT_FOUND) }
    }

    fun addPatient(patientDTO: PatientDTO): ResponseEntity<PatientDTO> {

        patientRepository.save(patientMapper.mapDTOToDAO(patientDTO))

        return ResponseEntity(HttpStatus.OK)
    }

    fun deletePatientById(id: Long): ResponseEntity<PatientDTO> {

        return if (patientRepository.findById(id).equals(null)) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            visitRepository.deleteVisitsByPatientId(id)
            patientRepository.deleteById(id)
            ResponseEntity(HttpStatus.OK)
        }
    }

    fun updatePatient(patientDTO: PatientDTO): ResponseEntity<PatientDTO>? {

        return patientDTO.id?.let {
            patientRepository.findById(it)
                .map { patientRepository.save(patientMapper.mapDTOToDAO(patientDTO)) }
                .map { patientDAO -> patientMapper.mapDAOToDTO(patientDAO) }
                .map { patientDTO -> ResponseEntity(patientDTO, HttpStatus.OK) }
                .orElseGet { ResponseEntity(HttpStatus.NOT_FOUND) }
        }
    }

    fun getAllPatients(): ResponseEntity<Set<PatientDTO>> {
        val set: Set<PatientDTO> = patientRepository
            .findAll()
            .map { patientDAO -> patientMapper.mapDAOToDTO(patientDAO) }
            .toSet()

        return ResponseEntity(set, HttpStatus.OK)
    }
}
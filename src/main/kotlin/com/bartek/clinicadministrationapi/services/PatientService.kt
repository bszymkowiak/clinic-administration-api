package com.bartek.clinicadministrationapi.services

import com.bartek.clinicadministrationapi.domain.dtos.PatientDTO
import com.bartek.clinicadministrationapi.mappers.PatientMapper
import com.bartek.clinicadministrationapi.repositories.PatientRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class PatientService(
    val repository: PatientRepository,
    val patientMapper: PatientMapper
) {

    fun getPatientById(id: Long): ResponseEntity<PatientDTO> {
        return repository.findById(id)
            .map { patientDAO -> patientMapper.mapDAOToDTO(patientDAO) }
            .map { patientDTO -> ResponseEntity(patientDTO, HttpStatus.OK) }
            .orElseGet { ResponseEntity(HttpStatus.NOT_FOUND) }
    }

    fun addPatient(patientDTO: PatientDTO): ResponseEntity<PatientDTO> {

        repository.save(patientMapper.mapDTOToDAO(patientDTO))

        return ResponseEntity(HttpStatus.OK)

    }

    fun deletePatientById(id: Long): ResponseEntity<PatientDTO> {

        repository.deleteById(id)

        return ResponseEntity(HttpStatus.OK)
    }

    fun updatePatient(patientDTO: PatientDTO): ResponseEntity<PatientDTO>? {

        return patientDTO.id?.let {
            repository.findById(it)
                .map { repository.save(patientMapper.mapDTOToDAO(patientDTO)) }
                .map { patientDAO -> patientMapper.mapDAOToDTO(patientDAO) }
                .map { patientDTO -> ResponseEntity(patientDTO, HttpStatus.OK) }
                .orElseGet { ResponseEntity(HttpStatus.NOT_FOUND) }
        }

    }

}
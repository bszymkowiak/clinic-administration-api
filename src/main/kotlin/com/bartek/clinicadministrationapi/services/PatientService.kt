package com.bartek.clinicadministrationapi.services

import com.bartek.clinicadministrationapi.domain.dtos.PatientDTO
import com.bartek.clinicadministrationapi.domain.dtos.VisitDTO
import com.bartek.clinicadministrationapi.mappers.PatientMapper
import com.bartek.clinicadministrationapi.mappers.VisitMapper
import com.bartek.clinicadministrationapi.repositories.PatientRepository
import com.bartek.clinicadministrationapi.repositories.VisitRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class PatientService(
    val patientRepository: PatientRepository,
    val patientMapper: PatientMapper,
    val visitRepository: VisitRepository,
    val visitMapper: VisitMapper
) {

    fun getPatientById(id: Long): ResponseEntity<PatientDTO> {
        return patientRepository.findById(id)
            .map { patientDAO -> patientMapper.mapDAOToDTO(patientDAO) }
            .map { patientDTO -> ResponseEntity(patientDTO, HttpStatus.OK) }
            .orElseGet { ResponseEntity(HttpStatus.NOT_FOUND) }
    }

    fun addPatient(patientDTO: PatientDTO): ResponseEntity<PatientDTO> {

        if (patientDTO.firstName != null && patientDTO.lastName != null && patientDTO.address != null) {
            patientRepository.save(patientMapper.mapDTOToDAO(patientDTO))
        } else {
            return ResponseEntity(patientDTO, HttpStatus.BAD_REQUEST)
        }

        return ResponseEntity(HttpStatus.OK)
    }

    fun deletePatientById(id: Long): ResponseEntity<PatientDTO> {

        val set: Set<VisitDTO> = visitRepository
            .findAll()
            .map { visitDAO -> visitMapper.mapDAOToDTO(visitDAO) }
            .filter { visitDTO -> visitDTO.patient.id == id }
            .toSet()

        set
            .map { visitDTO -> visitDTO.id }
            .map { visitId -> visitId?.let { visitRepository.deleteById(it) } }

        if (patientRepository.findById(id).equals(null)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            patientRepository.deleteById(id)
        }

        return ResponseEntity(HttpStatus.OK)
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
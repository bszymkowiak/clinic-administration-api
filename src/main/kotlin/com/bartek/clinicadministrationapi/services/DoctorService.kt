package com.bartek.clinicadministrationapi.services

import com.bartek.clinicadministrationapi.domain.dtos.DoctorDTO
import com.bartek.clinicadministrationapi.mappers.DoctorMapper
import com.bartek.clinicadministrationapi.repositories.DoctorRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class DoctorService(
    val repository: DoctorRepository,
    val doctorMapper: DoctorMapper
) {

    fun findDoctorById(id: Long): ResponseEntity<DoctorDTO> {

        return repository.findById(id)
            .map { doctorDAO -> doctorMapper.mapDAOToDTO(doctorDAO) }
            .map { doctorDTO -> ResponseEntity(doctorDTO, HttpStatus.OK) }
            .orElseGet { ResponseEntity(HttpStatus.NOT_FOUND) }
    }

    fun addDoctorToDb(doctorDTO: DoctorDTO): ResponseEntity<DoctorDTO> {

        repository.save(doctorMapper.mapDTOToDAO(doctorDTO))

        return ResponseEntity(HttpStatus.OK)
    }

    fun deleteDoctorById(id: Long): ResponseEntity<DoctorDTO> {

        repository.deleteById(id)

        return ResponseEntity(HttpStatus.OK)
    }

    fun updateDoctor(doctorDTO: DoctorDTO): ResponseEntity<DoctorDTO>? {

        return doctorDTO.id?.let {
            repository.findById(it)
                .map { repository.save(doctorMapper.mapDTOToDAO(doctorDTO)) }
                .map { doctorDAO -> doctorMapper.mapDAOToDTO(doctorDAO) }
                .map { dostorDTO -> ResponseEntity(doctorDTO, HttpStatus.OK) }
                .orElseGet { ResponseEntity(HttpStatus.NOT_FOUND) }
        }
    }


}
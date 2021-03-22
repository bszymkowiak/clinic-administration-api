package com.bartek.clinicadministrationapi.services

import com.bartek.clinicadministrationapi.domain.dtos.DoctorDTO
import com.bartek.clinicadministrationapi.domain.dtos.VisitDTO
import com.bartek.clinicadministrationapi.mappers.DoctorMapper
import com.bartek.clinicadministrationapi.mappers.VisitMapper
import com.bartek.clinicadministrationapi.repositories.DoctorRepository
import com.bartek.clinicadministrationapi.repositories.VisitRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class DoctorService(
    val doctorRepository: DoctorRepository,
    val doctorMapper: DoctorMapper,
    val visitRepository: VisitRepository
) {

    fun findDoctorById(id: Long): ResponseEntity<DoctorDTO> {

        return doctorRepository.findById(id)
            .map { doctorDAO -> doctorMapper.mapDAOToDTO(doctorDAO) }
            .map { doctorDTO -> ResponseEntity(doctorDTO, HttpStatus.OK) }
            .orElseGet { ResponseEntity(HttpStatus.NOT_FOUND) }
    }

    fun addDoctorToDb(doctorDTO: DoctorDTO): ResponseEntity<DoctorDTO> {
        doctorRepository.save(doctorMapper.mapDTOToDAO(doctorDTO))

        return ResponseEntity(HttpStatus.OK)
    }

    fun deleteDoctorById(id: Long): ResponseEntity<DoctorDTO> {

        return if (doctorRepository.findById(id).equals(null)) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            visitRepository.deleteVisitsByDoctorId(id)
            doctorRepository.deleteById(id)
            ResponseEntity(HttpStatus.OK)
        }
    }

    fun updateDoctor(doctorDTO: DoctorDTO): ResponseEntity<DoctorDTO>? {

        return doctorDTO.id?.let {
            doctorRepository.findById(it)
                .map { doctorRepository.save(doctorMapper.mapDTOToDAO(doctorDTO)) }
                .map { doctorDAO -> doctorMapper.mapDAOToDTO(doctorDAO) }
                .map { dostorDTO -> ResponseEntity(doctorDTO, HttpStatus.OK) }
                .orElseGet { ResponseEntity(HttpStatus.NOT_FOUND) }
        }
    }

    fun getDoctors(): ResponseEntity<Set<DoctorDTO>> {
        val set: Set<DoctorDTO> = doctorRepository
            .findAll()
            .map { doctorDAO -> doctorMapper.mapDAOToDTO(doctorDAO) }
            .toSet()

        return ResponseEntity(set, HttpStatus.OK)
    }
}
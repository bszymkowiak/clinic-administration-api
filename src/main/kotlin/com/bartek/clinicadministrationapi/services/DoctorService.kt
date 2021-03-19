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
    val visitRepository: VisitRepository,
    val visitMapper: VisitMapper
) {

    fun findDoctorById(id: Long): ResponseEntity<DoctorDTO> {

        return doctorRepository.findById(id)
            .map { doctorDAO -> doctorMapper.mapDAOToDTO(doctorDAO) }
            .map { doctorDTO -> ResponseEntity(doctorDTO, HttpStatus.OK) }
            .orElseGet { ResponseEntity(HttpStatus.NOT_FOUND) }
    }

    fun addDoctorToDb(doctorDTO: DoctorDTO): ResponseEntity<DoctorDTO> {

        if (doctorDTO.firstName != null && doctorDTO.lastName != null && doctorDTO.specialisation != null) {
            doctorRepository.save(doctorMapper.mapDTOToDAO(doctorDTO))
        } else {
            return ResponseEntity(doctorDTO, HttpStatus.BAD_REQUEST)
        }

        return ResponseEntity(HttpStatus.OK)
    }

    fun deleteDoctorById(id: Long): ResponseEntity<DoctorDTO> {

        val set: Set<VisitDTO> = visitRepository
            .findAll()
            .map { visitDAO -> visitMapper.mapDAOToDTO(visitDAO) }
            .filter { visitDTO -> visitDTO.doctor.id == id }
            .toSet()

        set
            .map { visitDTO -> visitDTO.id }
            .map { visitId -> visitId?.let { visitRepository.deleteById(it) } }

        if (doctorRepository.findById(id).equals(null)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            doctorRepository.deleteById(id)
        }

        return ResponseEntity(HttpStatus.OK)
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
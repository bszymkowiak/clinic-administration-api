package com.bartek.clinicadministrationapi.services

import com.bartek.clinicadministrationapi.domain.daos.DoctorDAO
import com.bartek.clinicadministrationapi.domain.dtos.DoctorDTO
import com.bartek.clinicadministrationapi.mappers.DoctorMapper
import com.bartek.clinicadministrationapi.repositories.DoctorRepository
import com.bartek.clinicadministrationapi.repositories.VisitRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import java.util.*

@Service
class DoctorService(
    val doctorRepository: DoctorRepository,
    val doctorMapper: DoctorMapper,
    val visitRepository: VisitRepository
) {

    fun getDoctorById(id: Long): Optional<DoctorDTO> {

        val doctorOpt = doctorRepository.findById(id)
            .map { doctorDAO -> doctorMapper.mapDAOToDTO(doctorDAO) }

        if (doctorOpt.isPresent) {
            return doctorOpt
        } else {
            throw EmptyResultDataAccessException("Doctor not found by this id", 0)
        }
    }

    fun addDoctor(doctorDTO: DoctorDTO): Optional<DoctorDTO> {

        val opt = Optional.of(doctorRepository.save(doctorMapper.mapDTOToDAO(doctorDTO)))

        return opt
            .map { doctorDAO -> doctorMapper.mapDAOToDTO(doctorDAO) }
    }

    fun deleteDoctorById(id: Long): Optional<DoctorDTO> {

        val doctorOpt = doctorRepository.findById(id)
            .map { doctorDAO -> doctorMapper.mapDAOToDTO(doctorDAO) }

        if (doctorOpt.isPresent) {
            visitRepository.deleteVisitsByDoctorId(id)
            doctorRepository.deleteById(id)
        } else {
            throw EmptyResultDataAccessException("Doctor not found by this id", 0)
        }

        return doctorOpt
    }

    fun updateDoctor(doctorDTO: DoctorDTO): Optional<DoctorDTO>? {

        val doctorOpt = doctorDTO.id?.let {
            doctorRepository.findById(it)
                .map { doctorDAO -> doctorMapper.mapDAOToDTO(doctorDAO) }
        }

        if (doctorOpt != null) {
            doctorRepository.save(doctorMapper.mapDTOToDAO(doctorDTO))
        } else {
            throw EmptyResultDataAccessException("Doctor not found by this id", 0)
        }

        return doctorOpt
    }

    fun getAllDoctors(): Optional<Set<DoctorDTO>> {

        val setOpt = Optional.of(doctorRepository.findAll()
            .map { doctorDAO -> doctorMapper.mapDAOToDTO(doctorDAO) }
            .toSet())

            return setOpt
    }
}
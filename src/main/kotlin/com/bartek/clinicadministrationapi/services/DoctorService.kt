package com.bartek.clinicadministrationapi.services

import com.bartek.clinicadministrationapi.domain.dtos.DoctorDTO
import com.bartek.clinicadministrationapi.mappers.DoctorMapper
import com.bartek.clinicadministrationapi.repositories.DoctorRepository
import com.bartek.clinicadministrationapi.repositories.VisitRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class DoctorService(
    val doctorRepository: DoctorRepository,
    val doctorMapper: DoctorMapper,
    val visitRepository: VisitRepository
) {

    fun getDoctorById(id: Long): Optional<DoctorDTO> {

        return doctorRepository.findById(id)
            .map { doctorDAO -> doctorMapper.mapDAOToDTO(doctorDAO) }
    }

    fun addDoctor(doctorDTO: DoctorDTO): Optional<DoctorDTO> {

        val opt = Optional.of(doctorRepository.save(doctorMapper.mapDTOToDAO(doctorDTO)))

        return opt
            .map { doctorDAO -> doctorMapper.mapDAOToDTO(doctorDAO) }
    }

    fun deleteDoctorById(id: Long): Optional<DoctorDTO> {

        val doctorOpt = doctorRepository.findById(id)
            .map { doctorDAO -> doctorMapper.mapDAOToDTO(doctorDAO) }

        if (!doctorOpt.equals(null)) {
            visitRepository.deleteVisitsByDoctorId(id)
            doctorRepository.deleteById(id)
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
        }

        return doctorOpt
    }

    fun getAllDoctors(): Optional<Set<DoctorDTO>> {

        return Optional.of(doctorRepository.findAll()
            .map { doctorDAO -> doctorMapper.mapDAOToDTO(doctorDAO) }
            .toSet())
    }
}
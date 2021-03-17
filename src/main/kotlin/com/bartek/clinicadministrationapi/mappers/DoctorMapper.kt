package com.bartek.clinicadministrationapi.mappers

import com.bartek.clinicadministrationapi.domain.daos.DoctorDAO
import com.bartek.clinicadministrationapi.domain.dtos.DoctorDTO
import org.springframework.stereotype.Component

@Component
class DoctorMapper {

    fun mapDAOToDTO(doctorDAO: DoctorDAO): DoctorDTO {
        return DoctorDTO(
            doctorDAO.id,
            doctorDAO.firstName,
            doctorDAO.lastName,
            doctorDAO.specialisation
        )
    }

    fun mapDTOToDAO(doctorDTO: DoctorDTO): DoctorDAO {
        return DoctorDAO(
            doctorDTO.id,
            doctorDTO.firstName,
            doctorDTO.lastName,
            doctorDTO.specialisation
        )
    }
}
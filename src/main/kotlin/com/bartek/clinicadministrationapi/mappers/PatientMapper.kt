package com.bartek.clinicadministrationapi.mappers

import com.bartek.clinicadministrationapi.domain.daos.PatientDAO
import com.bartek.clinicadministrationapi.domain.dtos.PatientDTO
import org.springframework.stereotype.Component

@Component
class PatientMapper {

    fun mapDAOToDTO(patientDAO: PatientDAO): PatientDTO {
        return PatientDTO(
            patientDAO.id,
            patientDAO.firstName,
            patientDAO.lastName,
            patientDAO.address
        )
    }

    fun mapDTOToDAO(patientDTO: PatientDTO): PatientDAO {
        return PatientDAO(
            patientDTO.id,
            patientDTO.firstName,
            patientDTO.lastName,
            patientDTO.address
        )
    }
}
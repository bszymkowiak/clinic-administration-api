package com.bartek.clinicadministrationapi.mappers

import com.bartek.clinicadministrationapi.domain.daos.VisitDAO
import com.bartek.clinicadministrationapi.domain.dtos.VisitDTO
import org.springframework.stereotype.Component

@Component
class VisitMapper {

    fun mapDAOToDTO(visitDAO: VisitDAO): VisitDTO {

        return VisitDTO(
            visitDAO.id,
            visitDAO.date,
            visitDAO.dateTime,
            visitDAO.place,
            visitDAO.patient,
            visitDAO.doctor
        )

    }

    fun mapDTOToDAO(visitDTO: VisitDTO): VisitDAO {

        return VisitDAO(
            visitDTO.id,
            visitDTO.date,
            visitDTO.dateTime,
            visitDTO.place,
            visitDTO.patient,
            visitDTO.doctor
        )

    }

}
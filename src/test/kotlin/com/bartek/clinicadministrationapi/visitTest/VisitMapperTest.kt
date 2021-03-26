package com.bartek.clinicadministrationapi.visitTest

import com.bartek.clinicadministrationapi.domain.daos.DoctorDAO
import com.bartek.clinicadministrationapi.domain.daos.PatientDAO
import com.bartek.clinicadministrationapi.domain.daos.VisitDAO
import com.bartek.clinicadministrationapi.domain.dtos.VisitDTO
import com.bartek.clinicadministrationapi.mappers.VisitMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.sql.Date
import java.sql.Time

class VisitMapperTest {

    val visitMapper = VisitMapper()

    @Test
    fun `mapDAOToDTO returns VisitDTO`() {

        val patientTestDAO = PatientDAO(1, "Test", "Patient", "Test")
        val doctorTestDAO = DoctorDAO(1, "Test", "Doctor", "Test")
        val visitTestDTO = VisitDTO(
            1, Date(2021, 6, 15), Time(10, 0, 0), "Poznan",
            patientTestDAO, doctorTestDAO
        )
        val visitTestDAO = VisitDAO(
            1, Date(2021, 6, 15), Time(10, 0, 0), "Poznan",
            patientTestDAO, doctorTestDAO
        )

        assertEquals(visitTestDTO, visitMapper.mapDAOToDTO(visitTestDAO))
    }

    @Test
    fun `mapDTOToDAO returns VisitDAO`() {

        val patientTestDAO = PatientDAO(1, "Test", "Patient", "Test")
        val doctorTestDAO = DoctorDAO(1, "Test", "Doctor", "Test")
        val visitTestDTO = VisitDTO(
            1, Date(2021, 6, 15), Time(10, 0, 0), "Poznan",
            patientTestDAO, doctorTestDAO
        )
        val visitTestDAO = VisitDAO(
            1, Date(2021, 6, 15), Time(10, 0, 0), "Poznan",
            patientTestDAO, doctorTestDAO
        )

        assertEquals(visitTestDAO, visitMapper.mapDTOToDAO(visitTestDTO))
    }
}
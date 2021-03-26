package com.bartek.clinicadministrationapi.patientTest

import com.bartek.clinicadministrationapi.domain.daos.PatientDAO
import com.bartek.clinicadministrationapi.domain.dtos.PatientDTO
import com.bartek.clinicadministrationapi.mappers.PatientMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PatientMapperTest {

    val patientMapper = PatientMapper()

    @Test
    fun `mapDAOtoDTO returns PatientDTO`() {

        val patientTestDAO = PatientDAO(1, "Test", "Test", "Test")
        val patientTestDTO = PatientDTO(1, "Test", "Test", "Test")

        assertEquals(patientTestDTO, patientMapper.mapDAOToDTO(patientTestDAO))
    }

    @Test
    fun `mapDTOtoDAO returns PatientDAO`() {

        val patientTestDAO = PatientDAO(1, "Test", "Test", "Test")
        val patientTestDTO = PatientDTO(1, "Test", "Test", "Test")

        assertEquals(patientTestDAO, patientMapper.mapDTOToDAO(patientTestDTO))
    }

}
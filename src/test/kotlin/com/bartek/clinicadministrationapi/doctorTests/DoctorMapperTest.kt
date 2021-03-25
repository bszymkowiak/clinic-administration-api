package com.bartek.clinicadministrationapi.doctorTests

import com.bartek.clinicadministrationapi.domain.daos.DoctorDAO
import com.bartek.clinicadministrationapi.domain.dtos.DoctorDTO
import com.bartek.clinicadministrationapi.mappers.DoctorMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DoctorMapperTest {

    val doctorMapper = DoctorMapper()

    @Test
    fun `mapDAOtoDTO returns DoctorDTO`() {

        val doctorTestDAO = DoctorDAO(1, "Test", "Test", "Test")
        val doctorTestDTO = DoctorDTO(1, "Test", "Test", "Test")

        assertEquals(doctorTestDTO, doctorMapper.mapDAOToDTO(doctorTestDAO))
    }

    @Test
    fun `mapDAOtoDTO returns null`() {

        val doctorTestDAO = null

        assertEquals(null, doctorTestDAO?.let { doctorMapper.mapDAOToDTO(it) })
    }

    @Test
    fun `mapDTOtoDAO returns DoctorDAO`() {

        val doctorTestDAO = DoctorDAO(1, "Test", "Test", "Test")
        val doctorTestDTO = DoctorDTO(1, "Test", "Test", "Test")

        assertEquals(doctorTestDAO, doctorMapper.mapDTOToDAO(doctorTestDTO))
    }

    @Test
    fun `mapDTOtoDAO returns null`() {

        val doctorTestDTO = null

        assertEquals(null, doctorTestDTO?.let { doctorMapper.mapDTOToDAO(it) })
    }




}
package com.bartek.clinicadministrationapi.doctorTests

import com.bartek.clinicadministrationapi.domain.daos.DoctorDAO
import com.bartek.clinicadministrationapi.domain.dtos.DoctorDTO
import com.bartek.clinicadministrationapi.mappers.DoctorMapper
import com.bartek.clinicadministrationapi.repositories.DoctorRepository
import com.bartek.clinicadministrationapi.repositories.VisitRepository
import com.bartek.clinicadministrationapi.services.DoctorService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

class DoctorServiceTest {

    val repository = mockk<DoctorRepository>()
    val doctorMapper = mockk<DoctorMapper>()
    val visitRepository = mockk<VisitRepository>()

    val service = DoctorService(repository, doctorMapper, visitRepository)

    @Test
    fun `getDoctorById returns Optional`() {

        val doctorTestDAO = Optional.of(DoctorDAO(1, "Test", "Doctor", "Get"))
        val doctorTestDTO = Optional.of(DoctorDTO(1, "Test", "Doctor", "Get"))

        every { doctorMapper.mapDAOToDTO(doctorTestDAO.get()) } returns doctorTestDTO.get()
        every { repository.findById(1) } returns doctorTestDAO

        assertEquals(doctorTestDTO, service.getDoctorById(1))
        verify { repository.findById(1) }
    }

    @Test
    fun `addDoctor returns Optional`() {

        val doctorTestDAO = DoctorDAO(1, "Test", "Doctor", "Add")
        val doctorTestDTO = DoctorDTO(1, "Test", "Doctor", "Add")

        every { doctorMapper.mapDTOToDAO(doctorTestDTO) } returns doctorTestDAO
        every { repository.save(doctorTestDAO) } returns doctorTestDAO
        every { doctorMapper.mapDAOToDTO(doctorTestDAO) } returns doctorTestDTO

        assertEquals(Optional.of(doctorTestDTO), service.addDoctor(doctorTestDTO))
        verify { repository.save(doctorTestDAO) }
    }

    @Test
    fun `deleteDoctorById returns Optional`() {

        val doctorTestDAO = Optional.of(DoctorDAO(1, "Test", "Doctor", "Get"))
        val doctorTestDTO = Optional.of(DoctorDTO(1, "Test", "Doctor", "Get"))

        every { repository.findById(1) } returns doctorTestDAO
        every { doctorMapper.mapDAOToDTO(doctorTestDAO.get()) } returns doctorTestDTO.get()
        every { repository.deleteById(1) } returns Unit
        every { visitRepository.deleteVisitsByDoctorId(1) } returns Unit

        assertEquals(doctorTestDTO, service.deleteDoctorById(1))
        verify { repository.findById(1) }
    }

    @Test
    fun `updateDoctor returns Optional`() {

        val doctorTestDTO = Optional.of(DoctorDTO(1, "Test", "Doctor", "Add"))
        val doctorTestDAO = Optional.of(DoctorDAO(1, "Test", "Doctor", "Add"))

        every { repository.findById(1) } returns doctorTestDAO
        every { doctorMapper.mapDTOToDAO(doctorTestDTO.get()) } returns doctorTestDAO.get()
        every { doctorMapper.mapDAOToDTO(doctorTestDAO.get()) } returns doctorTestDTO.get()
        every { repository.save(doctorTestDAO.get()) } returns doctorTestDAO.get()

        assertEquals(doctorTestDTO, service.updateDoctor(doctorTestDTO.get()))
        verify { repository.save(doctorTestDAO.get()) }
    }


    @Test
    fun `getAllDoctors returns Optional`() {

        val doctorTestDTO = Optional.of(DoctorDTO(1, "Test", "Doctor", "Add"))
        val doctorTestDAO = Optional.of(DoctorDAO(1, "Test", "Doctor", "Add"))
        val setTest: Optional<Set<DoctorDAO>> = Optional.of(setOf())

        every { repository.findAll() } returns setTest.get()
        every { doctorMapper.mapDAOToDTO(doctorTestDAO.get()) } returns doctorTestDTO.get()

        assertEquals(setTest, service.getAllDoctors())
        verify { repository.findAll() }

    }
}
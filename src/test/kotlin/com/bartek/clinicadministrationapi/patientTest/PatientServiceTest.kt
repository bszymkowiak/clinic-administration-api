package com.bartek.clinicadministrationapi.patientTest

import com.bartek.clinicadministrationapi.domain.daos.PatientDAO
import com.bartek.clinicadministrationapi.domain.dtos.PatientDTO
import com.bartek.clinicadministrationapi.mappers.PatientMapper
import com.bartek.clinicadministrationapi.repositories.PatientRepository
import com.bartek.clinicadministrationapi.repositories.VisitRepository
import com.bartek.clinicadministrationapi.services.PatientService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

class PatientServiceTest {

    val patientRepository = mockk<PatientRepository>()
    val patientMapper = mockk<PatientMapper>()
    val visitRepository = mockk<VisitRepository>()
    val service = PatientService(patientRepository, patientMapper, visitRepository)

    @Test
    fun `getPatientById returns Optional`() {

        val patientTestDAO = Optional.of(PatientDAO(1, "Test", "Patient", "Get"))
        val patientTestDTO = Optional.of(PatientDTO(1, "Test", "Patient", "Get"))

        every { patientMapper.mapDAOToDTO(patientTestDAO.get()) } returns patientTestDTO.get()
        every { patientRepository.findById(1) } returns patientTestDAO

        Assertions.assertEquals(patientTestDTO, service.getPatientById(1))
        verify { patientRepository.findById(1) }
    }

    @Test
    fun `addPatient returns Optional`() {

        val patientTestDAO = PatientDAO(1, "Test", "Patient", "Add")
        val patientTestDTO = PatientDTO(1, "Test", "Patient", "Add")

        every { patientMapper.mapDAOToDTO(patientTestDAO) } returns patientTestDTO
        every { patientRepository.save(patientTestDAO) } returns patientTestDAO
        every { patientMapper.mapDTOToDAO(patientTestDTO) } returns patientTestDAO

        Assertions.assertEquals(Optional.of(patientTestDTO), service.addPatient(patientTestDTO))
        verify { patientRepository.save(patientTestDAO) }
    }

    @Test
    fun `deletePatientById returns Optional`() {

        val patientTestDAO = Optional.of(PatientDAO(1, "Test", "Patient", "Delete"))
        val patientTestDTO = Optional.of(PatientDTO(1, "Test", "Patient", "Delete"))

        every { patientRepository.findById(1) } returns patientTestDAO
        every { patientMapper.mapDAOToDTO(patientTestDAO.get()) } returns patientTestDTO.get()
        every { patientRepository.deleteById(1) } returns Unit
        every { visitRepository.deleteVisitsByPatientId(1) } returns Unit

        Assertions.assertEquals(patientTestDTO, service.deletePatientById(1))
        verify { patientRepository.findById(1) }
    }

    @Test
    fun `updatePatient returns Optional`() {

        val patientTestDAO = Optional.of(PatientDAO(1, "Test", "Patient", "Update"))
        val patientTestDTO = Optional.of(PatientDTO(1, "Test", "Patient", "Update"))

        every { patientRepository.findById(1) } returns patientTestDAO
        every { patientMapper.mapDTOToDAO(patientTestDTO.get()) } returns patientTestDAO.get()
        every { patientMapper.mapDAOToDTO(patientTestDAO.get()) } returns patientTestDTO.get()
        every { patientRepository.save(patientTestDAO.get()) } returns patientTestDAO.get()

        Assertions.assertEquals(patientTestDTO, service.updatePatient(patientTestDTO.get()))
        verify { patientRepository.save(patientTestDAO.get()) }
    }

    @Test
    fun `getAllPatients returns Optional`() {

        val patientTestDAO = Optional.of(PatientDAO(1, "Test", "Patient", "GetAll"))
        val patientTestDTO = Optional.of(PatientDTO(1, "Test", "Patient", "GetAll"))
        val setTest: Optional<Set<PatientDAO>> = Optional.of(setOf())

        every { patientRepository.findAll() } returns setTest.get()
        every { patientMapper.mapDAOToDTO(patientTestDAO.get()) } returns patientTestDTO.get()

        Assertions.assertEquals(setTest, service.getAllPatients())
        verify { patientRepository.findAll() }
    }
}
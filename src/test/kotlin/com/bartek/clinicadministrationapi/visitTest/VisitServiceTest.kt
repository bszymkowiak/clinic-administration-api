package com.bartek.clinicadministrationapi.visitTest

import com.bartek.clinicadministrationapi.domain.daos.DoctorDAO
import com.bartek.clinicadministrationapi.domain.daos.PatientDAO
import com.bartek.clinicadministrationapi.domain.daos.VisitDAO
import com.bartek.clinicadministrationapi.domain.dtos.VisitDTO
import com.bartek.clinicadministrationapi.mappers.VisitMapper
import com.bartek.clinicadministrationapi.repositories.VisitRepository
import com.bartek.clinicadministrationapi.services.VisitService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.sql.Date
import java.sql.Time
import java.time.LocalDateTime
import java.util.*

class VisitServiceTest {

    val visitRepository = mockk<VisitRepository>()
    val visitMapper = mockk<VisitMapper>()
    val service = VisitService(visitRepository, visitMapper)

    @Test
    fun `addVisit returns Optional`() {

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

        every {
            visitTestDTO.doctor.id?.let {
                visitRepository.findVisitsByDateAndDateTimeAndDoctorId(
                    visitTestDTO.date, visitTestDTO.dateTime,
                    it
                )
            }
        } returns Optional.of(visitTestDAO)

        every { visitMapper.mapDAOToDTO(visitTestDAO) } returns visitTestDTO
        every { visitRepository.save(visitTestDAO) } returns visitTestDAO

        assertEquals(service.addVisit(visitTestDTO), Optional.of(visitTestDTO))
        verify {
            visitTestDTO.doctor.id?.let {
                visitRepository.findVisitsByDateAndDateTimeAndDoctorId(
                    visitTestDTO.date, visitTestDTO.dateTime,
                    it
                )
            }
        }
    }

    @Test
    fun `getAllVisitsByPatientId returns Optional`() {

        val setTest: Set<VisitDAO> = setOf()
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

        every { visitRepository.findVisitsByPatientId(1) } returns setTest
        every { visitMapper.mapDAOToDTO(visitTestDAO) } returns visitTestDTO

        assertEquals(Optional.of(setTest), service.getAllVisitsByPatientId(1))
        verify { visitRepository.findVisitsByPatientId(1) }
    }

    @Test
    fun `deleteVisitByID returns Optional`() {

        val patientTestDAO = PatientDAO(1, "Test", "Patient", "Test")
        val doctorTestDAO = DoctorDAO(1, "Test", "Doctor", "Test")
        val visitTestDAO = VisitDAO(
            1, Date(2021, 6, 15), Time(10, 0, 0), "Poznan",
            patientTestDAO, doctorTestDAO
        )
        val visitTestDTO = VisitDTO(
            1, Date(2021, 6, 15), Time(10, 0, 0), "Poznan",
            patientTestDAO, doctorTestDAO
        )

        every { visitRepository.findById(1) } returns Optional.of(visitTestDAO)
        every { visitMapper.mapDAOToDTO(visitTestDAO) } returns visitTestDTO
        every { visitRepository.deleteById(1) } returns Unit

        assertEquals(service.deleteVisitById(1), Optional.of(visitTestDTO))
        verify { visitRepository.findById(1) }
    }

    @Test
    fun `updateVisit returns Optional`() {

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

        every {
            visitTestDTO.doctor.id?.let {
                visitRepository.findVisitsByDateAndDateTimeAndDoctorId(
                    visitTestDTO.date, visitTestDTO.dateTime,
                    it
                )
            }
        } returns Optional.of(visitTestDAO)
        every { visitMapper.mapDAOToDTO(visitTestDAO) } returns visitTestDTO
        every { visitRepository.findById(1) } returns Optional.of(visitTestDAO)
        every { visitMapper.mapDTOToDAO(visitTestDTO) } returns visitTestDAO
        every { visitRepository.save(visitTestDAO) } returns visitTestDAO

        assertEquals(Optional.of(visitTestDTO), service.updateVisit(visitTestDTO))
        verify {
            visitTestDTO.doctor.id?.let {
                visitRepository.findVisitsByDateAndDateTimeAndDoctorId(
                    visitTestDTO.date, visitTestDTO.dateTime,
                    it
                )
            }
        }
    }

    @Test
    fun `getAllVisits returns Optional`() {

        val patientTestDAO = PatientDAO(1, "Test", "Patient", "Test")
        val doctorTestDAO = DoctorDAO(1, "Test", "Doctor", "Test")
        val setTest: Set<VisitDAO> = setOf()
        val visitTestDTO = VisitDTO(
            1, Date(2021, 6, 15), Time(10, 0, 0), "Poznan",
            patientTestDAO, doctorTestDAO
        )
        val visitTestDAO = VisitDAO(
            1, Date(2021, 6, 15), Time(10, 0, 0), "Poznan",
            patientTestDAO, doctorTestDAO
        )

        every { visitRepository.findAll() } returns setTest
        every { visitMapper.mapDAOToDTO(visitTestDAO) } returns visitTestDTO

        assertEquals(Optional.of(setTest), service.getAllVisits())
        verify { visitRepository.findAll() }
    }
}
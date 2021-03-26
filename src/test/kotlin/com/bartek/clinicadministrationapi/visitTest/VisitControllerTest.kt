package com.bartek.clinicadministrationapi.visitTest

import com.bartek.clinicadministrationapi.controllers.VisitController
import com.bartek.clinicadministrationapi.domain.daos.DoctorDAO
import com.bartek.clinicadministrationapi.domain.daos.PatientDAO
import com.bartek.clinicadministrationapi.domain.dtos.VisitDTO
import com.bartek.clinicadministrationapi.services.VisitService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.sql.Date
import java.sql.Time
import java.util.*

class VisitControllerTest {

    val service = mockk<VisitService>()
    val controller = VisitController(service)

    @Test
    fun `addVisit returns HttpStatusOK`() {

        val patientTestDAO = PatientDAO(1, "Test", "Patient", "Test")
        val doctorTestDAO = DoctorDAO(1, "Test", "Doctor", "Test")
        val visitTest = VisitDTO(
            1, Date.valueOf("2021-03-25"), Time.valueOf("10:00:00"), "Poznan",
            patientTestDAO, doctorTestDAO
        )

        every { service.addVisit(visitTest) } returns ResponseEntity(visitTest, HttpStatus.OK)

        assertEquals(controller.addVisit(visitTest), ResponseEntity(visitTest, HttpStatus.OK))
        verify { service.addVisit(visitTest) }
    }

    @Test
    fun `getVisitByPatientId returns HttpStatusOK`() {

        val setTest: Optional<Set<VisitDTO>> = Optional.of(setOf())

        every { service.getAllVisitsByPatientId(1) } returns setTest

        assertEquals(controller.getVisitsByPatientId(1), ResponseEntity(setTest.get(), HttpStatus.OK))
        verify { service.getAllVisitsByPatientId(1) }
    }

    @Test
    fun `getAllVisits returns HttpStatusOK`() {

        val setTest: Optional<Set<VisitDTO>> = Optional.of(setOf())

        every { service.getAllVisits() } returns setTest

        assertEquals(controller.getAllVisits(), ResponseEntity(setTest.get(), HttpStatus.OK))
        verify { service.getAllVisits() }
    }

    @Test
    fun `deleteVisitById returns HttpStatusNotFound`() {

        every { service.deleteVisitById(1) } returns Optional.empty()

        assertEquals(controller.deleteVisitById(1), ResponseEntity(null, HttpStatus.NOT_FOUND))
        verify { service.deleteVisitById(1) }
    }

    @Test
    fun `deleteVisitById returns HttpStatusOK`() {

        val patientTestDAO = PatientDAO(1, "Test", "Patient", "Test")
        val doctorTestDAO = DoctorDAO(1, "Test", "Doctor", "Test")
        val visitTest = VisitDTO(
            1, Date.valueOf("2021-03-25"), Time.valueOf("10:00:00"), "Poznan",
            patientTestDAO, doctorTestDAO
        )

        every { service.deleteVisitById(1) } returns Optional.of(visitTest)

        assertEquals(controller.deleteVisitById(1), ResponseEntity(visitTest, HttpStatus.OK))
        verify { service.deleteVisitById(1) }
    }

    @Test
    fun `updateVisit returns HttpStatusOK`() {

        val patientTestDAO = PatientDAO(1, "Test", "Patient", "Test")
        val doctorTestDAO = DoctorDAO(1, "Test", "Doctor", "Test")
        val visitTest = VisitDTO(
            1, Date.valueOf("2021-03-25"), Time.valueOf("10:00:00"), "Poznan",
            patientTestDAO, doctorTestDAO
        )

        every { service.updateVisit(visitTest) } returns Optional.of(visitTest)

        assertEquals(controller.updateVisit(visitTest), ResponseEntity(visitTest, HttpStatus.OK))
        verify { service.updateVisit(visitTest) }
    }

    @Test
    fun `updateVisit returns HttpStatusNotFound`() {

        val patientTestDAO = PatientDAO(1, "Test", "Patient", "Test")
        val doctorTestDAO = DoctorDAO(1, "Test", "Doctor", "Test")
        val visitTest = VisitDTO(
            1, Date.valueOf("2021-03-25"), Time.valueOf("10:00:00"), "Poznan",
            patientTestDAO, doctorTestDAO
        )

        every { service.updateVisit(visitTest) } returns Optional.empty()

        assertEquals(controller.updateVisit(visitTest), ResponseEntity(null, HttpStatus.NOT_FOUND))
        verify { service.updateVisit(visitTest) }
    }
}
package com.bartek.clinicadministrationapi.patientTest

import com.bartek.clinicadministrationapi.controllers.PatientController
import com.bartek.clinicadministrationapi.domain.dtos.PatientDTO
import com.bartek.clinicadministrationapi.services.PatientService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.*

class PatientControllerTest {

    val service = mockk<PatientService>()
    val controller = PatientController(service)

    @Test
    fun `getPatientById returns HttpStatusOK`() {

        val patientTest = Optional.of(PatientDTO(1, "test", "doctor", "test"))

        every { service.getPatientById(1) } returns patientTest

        Assertions.assertEquals(controller.getPatientById(1), ResponseEntity(patientTest.get(), HttpStatus.OK))
        verify { service.getPatientById(1) }
    }

    @Test
    fun `getPatientById returns HttpStatusNotFound`() {

        val patientTest = Optional.empty<PatientDTO>()

        every { service.getPatientById(1) } returns patientTest

        Assertions.assertEquals(controller.getPatientById(1), ResponseEntity(null, HttpStatus.NOT_FOUND))
        verify { service.getPatientById(1) }
    }

    @Test
    fun `getAllPatients returns HttpStatusOK`() {

        val patientTest: Optional<Set<PatientDTO>> = Optional.of(setOf())

        every { service.getAllPatients() } returns patientTest

        Assertions.assertEquals(controller.getAllPatients(), ResponseEntity(patientTest.get(), HttpStatus.OK))
        verify { service.getAllPatients() }
    }

    @Test
    fun `addPatient returns HttpStatusOK`() {

        val patientTest = Optional.of(PatientDTO(1, "test", "doctor", "test"))

        every { service.addPatient(patientTest.get()) } returns patientTest

        Assertions.assertEquals(controller.addPatient(patientTest.get()), ResponseEntity(patientTest.get(), HttpStatus.OK))
        verify { service.addPatient(patientTest.get()) }
    }

    @Test
    fun `addPatientreturns HttpStatusBadRequest`() {

        val patientTest = Optional.of(PatientDTO(1, null, "doctor", "test"))

        every { service.addPatient(patientTest.get()) } returns patientTest

        Assertions.assertEquals(controller.addPatient(patientTest.get()), ResponseEntity(patientTest.get(), HttpStatus.OK))
        verify { service.addPatient(patientTest.get()) }
    }

    @Test
    fun `deletePatient returns HttpStatusOK`() {

        val patientTest = Optional.of(PatientDTO(1, "test", "doctor", "test"))

        every { patientTest.get().id?.let { service.deletePatientById(it) } } returns patientTest

        Assertions.assertEquals(ResponseEntity(patientTest.get(), HttpStatus.OK),
            patientTest.get().id?.let { controller.deletePatientById(it) })
        verify { patientTest.get().id?.let { service.deletePatientById(it) } }
    }

    @Test
    fun `updatePatient returns HttpStatusOK`() {

        val patientTest = Optional.of(PatientDTO(1, "test", "doctor", "test"))

        every { service.updatePatient(patientTest.get()) } returns patientTest

        Assertions.assertEquals(
            ResponseEntity(patientTest.get(), HttpStatus.OK),
            controller.updatePatient(patientTest.get())
        )
        verify { service.updatePatient(patientTest.get())}
    }
}
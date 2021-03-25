package com.bartek.clinicadministrationapi.doctorTests

import com.bartek.clinicadministrationapi.controllers.DoctorController
import com.bartek.clinicadministrationapi.domain.dtos.DoctorDTO
import com.bartek.clinicadministrationapi.services.DoctorService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.*

class DoctorControllerTest {

    val service = mockk<DoctorService>()
    val controller = DoctorController(service)

    @Test
    fun `getDoctorById returns HttpStatusOK`() {

        val doctorTest = Optional.of(DoctorDTO(1, "test", "doctor", "test"))

        every { service.getDoctorById(1) } returns doctorTest

        assertEquals(controller.getDoctorById(1), ResponseEntity(doctorTest.get(), HttpStatus.OK))
        verify { service.getDoctorById(1) }
    }

    @Test
    fun `getDoctorById returns HttpStatusNotFound`() {

        val doctorTest = Optional.empty<DoctorDTO>()

        every { service.getDoctorById(1) } returns doctorTest

        assertEquals(controller.getDoctorById(1), ResponseEntity(null, HttpStatus.NOT_FOUND))
        verify { service.getDoctorById(1) }
    }

    @Test
    fun `getAllDoctors returns HttpStatusOK`() {

        val doctorTest: Optional<Set<DoctorDTO>> = Optional.of(setOf())

        every { service.getAllDoctors() } returns doctorTest

        assertEquals(controller.getAllDoctors(), ResponseEntity(doctorTest.get(), HttpStatus.OK))
        verify { service.getAllDoctors() }
    }

    @Test
    fun `addDoctor returns HttpStatusOK`() {

        val doctorTest = Optional.of(DoctorDTO(1, "test", "doctor", "test"))

        every { service.addDoctor(doctorTest.get()) } returns doctorTest

        assertEquals(controller.addDoctor(doctorTest.get()), ResponseEntity(doctorTest.get(), HttpStatus.OK))
        verify { service.addDoctor(doctorTest.get()) }
    }

    @Test
    fun `addDoctor returns HttpStatusBadRequest`() {

        val doctorTest = Optional.of(DoctorDTO(1, null, "doctor", "test"))

        every { service.addDoctor(doctorTest.get()) } returns doctorTest

        assertEquals(controller.addDoctor(doctorTest.get()), ResponseEntity(doctorTest.get(), HttpStatus.OK))
        verify { service.addDoctor(doctorTest.get()) }
    }

    @Test
    fun `deleteDoctor returns HttpStatusOK`() {

        val doctorTest = Optional.of(DoctorDTO(1, "test", "doctor", "test"))

        every { doctorTest.get().id?.let { service.deleteDoctorById(it) } } returns doctorTest

        assertEquals(ResponseEntity(doctorTest.get(), HttpStatus.OK),
            doctorTest.get().id?.let { controller.deleteDoctor(it) })
        verify { doctorTest.get().id?.let { service.deleteDoctorById(it) } }
    }

    @Test
    fun `updateDoctor returns HttpStatusOK`() {

        val doctorTest = Optional.of(DoctorDTO(1, "test", "doctor", "test"))

        every { service.updateDoctor(doctorTest.get()) } returns doctorTest

        assertEquals(ResponseEntity(doctorTest.get(), HttpStatus.OK), controller.updateDoctor(doctorTest.get()))
        verify { service.updateDoctor(doctorTest.get())}
    }
}
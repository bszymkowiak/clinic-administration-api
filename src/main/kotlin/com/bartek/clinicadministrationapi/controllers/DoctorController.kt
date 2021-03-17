package com.bartek.clinicadministrationapi.controllers

import com.bartek.clinicadministrationapi.mappers.DoctorMapper
import com.bartek.clinicadministrationapi.services.DoctorService
import org.springframework.web.bind.annotation.RestController

@RestController
class DoctorController (
    val service: DoctorService,
    val doctorMapper: DoctorMapper) {

}
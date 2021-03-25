package com.bartek.clinicadministrationapi.domain.dtos

import com.bartek.clinicadministrationapi.domain.daos.DoctorDAO
import com.bartek.clinicadministrationapi.domain.daos.PatientDAO
import java.sql.Date
import java.sql.Time
import javax.validation.constraints.NotNull

data class VisitDTO(

    var id: Long? = null,

    @field:NotNull
    var date: Date,
    @field:NotNull
    var dateTime: Time,
    @field:NotNull
    var place: String,
    @field:NotNull
    var patient: PatientDAO,
    @field:NotNull
    var doctor: DoctorDAO
)

package com.bartek.clinicadministrationapi.domain.dtos

import com.bartek.clinicadministrationapi.domain.daos.DoctorDAO
import com.bartek.clinicadministrationapi.domain.daos.PatientDAO
import org.springframework.format.annotation.DateTimeFormat
import java.sql.Date
import java.sql.Time
import javax.validation.constraints.NotNull

data class VisitDTO(

    var id: Long? = null,

    @NotNull
    var date: Date,
    @NotNull
    var dateTime: Time,
    @NotNull
    var place: String,
    @NotNull
    var patient: PatientDAO,
    @NotNull
    var doctor: DoctorDAO,
)

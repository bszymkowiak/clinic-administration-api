package com.bartek.clinicadministrationapi.domain.dtos

import com.bartek.clinicadministrationapi.domain.daos.DoctorDAO
import com.bartek.clinicadministrationapi.domain.daos.PatientDAO
import java.sql.Date
import java.sql.Time

data class VisitDTO(

    val id: Long? = null,
    val date: Date,
    val dateTime: Time,
    val place: String,
    val patient: PatientDAO,
    val doctor: DoctorDAO,
)

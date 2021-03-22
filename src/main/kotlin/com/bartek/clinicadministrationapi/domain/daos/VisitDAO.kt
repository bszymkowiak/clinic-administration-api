package com.bartek.clinicadministrationapi.domain.daos

import org.springframework.format.annotation.DateTimeFormat
import java.sql.Date
import java.sql.Time
import javax.persistence.*

@Entity
@Table(name = "visit")
data class VisitDAO(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val date: Date,
    val dateTime: Time,
    val place: String,

    @OneToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    val patient: PatientDAO,

    @OneToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    val doctor: DoctorDAO,

    )

package com.bartek.clinicadministrationapi.domain.daos

import java.sql.Date
import java.sql.Time
import javax.persistence.*

@Entity
@Table(name = "visit")
data class VisitDAO(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "date")
    val date: Date,

    @Column(name = "dateTime")
    val dateTime: Time,

    @Column(name = "place")
    val place: String,

    @OneToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    val patient: PatientDAO,

    @OneToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    val doctor: DoctorDAO,

)

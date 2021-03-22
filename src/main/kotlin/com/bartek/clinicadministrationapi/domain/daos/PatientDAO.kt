package com.bartek.clinicadministrationapi.domain.daos

import javax.persistence.*

@Entity
@Table(name = "patient")
data class PatientDAO(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var firstName: String?,
    var lastName: String?,
    var address: String?

)
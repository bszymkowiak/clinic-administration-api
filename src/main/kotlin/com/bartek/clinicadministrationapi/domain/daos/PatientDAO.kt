package com.bartek.clinicadministrationapi.domain.daos

import javax.persistence.*

@Entity
@Table(name = "patient")
data class PatientDAO(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "firstName")
    var firstName: String?,

    @Column(name = "lastName")
    var lastName: String?,

    @Column(name = "address")
    var address: String?

)
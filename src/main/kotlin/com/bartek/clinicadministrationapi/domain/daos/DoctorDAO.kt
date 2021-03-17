package com.bartek.clinicadministrationapi.domain.daos

import javax.persistence.*

@Entity
@Table(name = "doctor")
data class DoctorDAO(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "firstName")
    var firstName: String?,

    @Column(name = "lastName")
    var lastName: String?,

    @Column(name = "specialisation")
    var specialisation: String?

)
package com.bartek.clinicadministrationapi.model.dtos

import javax.persistence.Entity

@Entity
data class PatientDTO (

    val id: Int,
    val firstName: String,
    val lastName: String,
    val address: String
)
package com.bartek.clinicadministrationapi.domain.dtos

data class PatientDTO(
    var id: Long? = null,
    var firstName: String?,
    var lastName: String?,
    var address: String?
)

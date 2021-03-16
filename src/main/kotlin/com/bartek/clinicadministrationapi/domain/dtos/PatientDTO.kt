package com.bartek.clinicadministrationapi.domain.dtos

data class PatientDTO(
    var id: Long?,
    var firstName: String?,
    var lastName: String?,
    var address: String?
)

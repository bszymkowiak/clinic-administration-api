package com.bartek.clinicadministrationapi.domain.dtos

import javax.validation.constraints.NotNull

data class DoctorDTO(
    var id: Long? = null,

    @field:NotNull(message = "Firstname cannot be null.")
    var firstName: String?,
    @field:NotNull(message = "Lastname cannot be null.")
    var lastName: String?,
    @field:NotNull(message = "Specialisation cannot be null.")
    var specialisation: String?

)

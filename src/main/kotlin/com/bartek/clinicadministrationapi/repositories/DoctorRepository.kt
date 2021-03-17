package com.bartek.clinicadministrationapi.repositories
import com.bartek.clinicadministrationapi.domain.daos.DoctorDAO
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DoctorRepository: CrudRepository<DoctorDAO, Long> {
}
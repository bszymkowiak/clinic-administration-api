package com.bartek.clinicadministrationapi.repositories
import com.bartek.clinicadministrationapi.domain.daos.PatientDAO
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PatientRepository : CrudRepository<PatientDAO, Long> {

}
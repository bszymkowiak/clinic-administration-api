package com.bartek.clinicadministrationapi.repositories


import com.bartek.clinicadministrationapi.domain.daos.VisitDAO
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface VisitRepository: CrudRepository<VisitDAO, Long> {


}
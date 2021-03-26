package com.bartek.clinicadministrationapi.repositories

import com.bartek.clinicadministrationapi.domain.daos.VisitDAO
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.sql.Date
import java.sql.Time
import java.util.*

@Repository
interface VisitRepository : CrudRepository<VisitDAO, Long> {

    @Transactional
    fun deleteVisitsByPatientId(id: Long)

    @Transactional
    fun deleteVisitsByDoctorId(id: Long)

    @Transactional
    fun findVisitsByDateAndDateTimeAndDoctorId(date: Date, time: Time, id: Long) : Optional<VisitDAO>

    @Transactional
    fun findVisitsByPatientId(id: Long) : Set<VisitDAO>

}
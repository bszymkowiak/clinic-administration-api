package com.bartek.clinicadministrationapi.services

import com.bartek.clinicadministrationapi.mappers.VisitMapper
import com.bartek.clinicadministrationapi.repositories.VisitRepository
import org.springframework.stereotype.Service

@Service
class VisitService(
    val repository: VisitRepository,
    val visitMapper: VisitMapper){
}
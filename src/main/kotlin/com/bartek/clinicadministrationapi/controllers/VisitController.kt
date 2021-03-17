package com.bartek.clinicadministrationapi.controllers

import com.bartek.clinicadministrationapi.services.VisitService
import org.springframework.web.bind.annotation.RestController

@RestController
class VisitController(val visitService: VisitService) {
}
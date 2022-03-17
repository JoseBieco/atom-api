package api.atomical.family

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("family")
class FamilyController(
    @Autowired
    val service: FamilyService
) {
    /**
     * Get all
     * Get by id
     */
}
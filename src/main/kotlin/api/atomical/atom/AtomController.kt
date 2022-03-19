package api.atomical.atom

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController

@RestController
class AtomController(
    @Autowired
    val service: AtomService
) {
}
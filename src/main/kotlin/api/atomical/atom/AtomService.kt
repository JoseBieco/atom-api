package api.atomical.atom

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AtomService(
    @Autowired
    val db: AtomRepository
) {
}
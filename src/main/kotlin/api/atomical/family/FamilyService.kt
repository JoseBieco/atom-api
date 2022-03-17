package api.atomical.family

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FamilyService(
    @Autowired
    val db: FamilyRepository
) {
}
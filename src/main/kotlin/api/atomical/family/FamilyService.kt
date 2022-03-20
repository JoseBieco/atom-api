package api.atomical.family

import api.atomical.family.dto.CreateFamilyDto
import api.atomical.family.dto.UpdateFamilyDto
import api.atomical.family.dto.UpdateFamilyNameDto
import api.atomical.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime

@Service
class FamilyService(
    @Autowired
    val db: FamilyRepository
) {

    /**
     * Create a new entity on database
     */
    fun create(family: CreateFamilyDto): Family {
       return db.findByName(family.name)
           ?.run { throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "This family is already registered.") }
           ?: run { db.save(Family(family)) }

    }

    /**
     * Get all families from database that are active
     */
    fun getAll(pageable: PageRequest): Page<Family> {
        return db.getAll(pageable)
    }

    /**
     * Find on database the entity if the passed id
     */
    fun getById(familyId: Long): Family {
        return db.findById(familyId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Family not found.")
        }
    }

    /**
     * Alter family deletedAt attribute
     * Checks if the familyId param exists, if not, throws bad request
     * @param familyId Represents the family unique key
     */
    fun softDelete(familyId: Long): Family {
        return db.findById(familyId)
            .orElseThrow {
                ResponseStatusException(HttpStatus.NOT_FOUND, "Family not found.")
            }.apply { deletedAt = LocalDateTime.now() }.run { db.save(this) }
    }

    /**
     * Remove family from database
     * @param familyId Family unique identifier
     */
    fun remove(familyId: Long) {
        // Validate if the familyId exists in database
        return db.findById(familyId)
            .orElseThrow{
                ResponseStatusException(HttpStatus.NOT_FOUND, "Family not found.")
            }.run { db.delete(this) }
    }

    /**
     * Change the family name, but first, validate the id and check if the name is not registered
     * @param familyId Family unique identifier
     * @param familyDto Model representing the new name
     * @return The updated family
     */
    fun changeName(familyId: Long, familyDto: UpdateFamilyNameDto): Family {
        val family = db.findById(familyId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Family not found.")
        }

        return familyDto.run { db.findByName(this.name) }
            ?.run { throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "This family is already registered.") }
            ?: run {
                family.name = familyDto.name
                family.updatedAt = LocalDateTime.now()
            }.run { db.save(family) }
    }

    /**
     * Update the family entity data
     * @param familyId Family unique identifier
     * @param familyDto Model representing the new name
     * @return The updated entity
     */
    fun update(familyId: Long, familyDto: UpdateFamilyDto): Family {
        return db.findById(familyId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Family not found.")
        }.apply {
            description = familyDto.description
            updatedAt = LocalDateTime.now()
        }.run { db.save(this) }
    }

    /**
     * Use to save a family entity in others services
     * @param family Updated family
     * @return Updated family
     */
    fun save(family: Family): Family {
        return db.save(family)
    }
}
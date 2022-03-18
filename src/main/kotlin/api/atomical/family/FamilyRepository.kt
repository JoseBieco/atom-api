package api.atomical.family

import api.atomical.user.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


interface FamilyRepository: JpaRepository<Family, Long> {

    /**
     * Get an entity with the passed name
     */
    @Query("SELECT u FROM Family u WHERE u.name = :name")
    fun findByName(name: String): Family?

    /**
     * Get all users pageable
     * @param pageRequest Pageable
     * @return Page of users
     */
    @Query("SELECT u FROM Family u WHERE u.deletedAt IS NULL")
    fun getAll(pageRequest: Pageable): Page<Family>
}
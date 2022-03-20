package api.atomical.atom

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AtomRepository: JpaRepository<Atom, Long> {

    /**
     * Get all atoms pageable
     * @param pageRequest Pageable
     * @return Page of atoms
     */
    @Query("SELECT u FROM Atom u WHERE u.deletedAt IS NULL")
    fun getAll(pageRequest: Pageable): Page<Atom>
}
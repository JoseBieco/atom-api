package api.atomical.image

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ImageRepository: JpaRepository<Image, Long> {

    /**
     * Get all images pageable
     * @param pageRequest Pageable
     * @return Page of images
     */
    @Query("SELECT u FROM Image u WHERE u.deletedAt IS NULL")
    fun getAll(pageRequest: Pageable): Page<Image>
}
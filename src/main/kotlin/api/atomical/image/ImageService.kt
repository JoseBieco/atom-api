package api.atomical.image

import api.atomical.image.dto.ImageDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime

@Service
class ImageService(
    @Autowired
    val db: ImageRepository
) {

    /**
     * Create a new image
     * @param image New image data
     */
    fun create(image: ImageDto): Image {
        return db.save(Image(image))
    }

    /**
     * Find image on database by id
     * @param imageId Image unique identifier
     * @return Image from requested id
     */
    fun findById(imageId: Long): Image {
        return db.findById(imageId).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found.") }
    }

    /**
     * Get all images from database where deletedAt ar null
     * @param pageRequest Represents the page to be selected
     * @return Page of images
     */
    fun getAll(pageRequest: PageRequest): Page<Image> {
        return db.getAll(pageRequest)
    }

    /**
     * Soft delete image from database
     * @param imageId Image unique identifier
     * @return Updated image
     */
    fun delete(imageId: Long): Image {
        return findById(imageId)
            .apply {
                deletedAt = LocalDateTime.now()
                updatedAt = LocalDateTime.now()
            }.run { db.save(this)}
    }

    /**
     * Remove image from database
     * @param imageId Image unique identifier
     */
    fun remove(imageId: Long) {
        return findById(imageId).run { db.delete(this) }
    }

    /**
     * Update on database the requested image id
     * @param imageId Image unique identifier
     * @param image Updated image data
     */
    fun update(imageId: Long, image: ImageDto): Image {
        return findById(imageId).apply {
            title = image.title
            url = image.url
            updatedAt = LocalDateTime.now()
        }
    }
}
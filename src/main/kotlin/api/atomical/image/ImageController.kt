package api.atomical.image

import api.atomical.image.dto.ImageDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("images")
class ImageController(
    @Autowired
    val service: ImageService
) {

    /**
     * Insert a new image into database route
     */
    @PostMapping
    fun create(@RequestBody image: ImageDto): Image {
        return service.create(image)
    }

    /**
     * Get all images from database route
     */
    @GetMapping
    fun getAll(
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "linesPerPage", defaultValue = "5") linesPerPage: Int,
    ): Page<Image> {
        val pageRequest: PageRequest = PageRequest.of(page, linesPerPage)
        return service.getAll(pageRequest)
    }

    /**
     * Find image by id route
     */
    @GetMapping("/{imageId}")
    fun findById(@PathVariable imageId: Long): Image {
        return service.findById(imageId)
    }

    /**
     * Soft delete image route
     */
    @DeleteMapping("/{imageId}")
    fun delete(@PathVariable imageId: Long): Image {
        return service.delete(imageId)
    }

    /**
     * Remove image from database route
     */
    @DeleteMapping("/{imageId}/remove")
    fun remove(@PathVariable imageId: Long) {
        return service.remove(imageId)
    }

    /**
     * Update image route
     */
    @PutMapping("/{imageId}")
    fun update(@PathVariable imageId: Long, @RequestBody image: ImageDto): Image {
        return service.update(imageId, image)
    }
}
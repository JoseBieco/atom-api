package api.atomical

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AtomicalApplication

/**
 * Application start point
 */
fun main(args: Array<String>) {
	runApplication<AtomicalApplication>(*args)
}

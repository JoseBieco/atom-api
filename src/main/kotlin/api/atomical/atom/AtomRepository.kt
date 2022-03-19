package api.atomical.atom

import org.springframework.data.jpa.repository.JpaRepository

interface AtomRepository: JpaRepository<Atom, Long> {
}
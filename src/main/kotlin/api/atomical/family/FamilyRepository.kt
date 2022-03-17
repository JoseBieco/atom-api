package api.atomical.family

import org.springframework.data.jpa.repository.JpaRepository

interface FamilyRepository: JpaRepository<Family, Long> {
}
//package midashnt.service.babysmarthomekt.app.mapper
//
//import midashnt.service.babysmarthomekt.app.dto.Baby
//import org.springframework.data.jpa.repository.JpaRepository
//import org.springframework.stereotype.Repository
//
//@Repository
//interface BabyRepository: JpaRepository<Baby, Long> {
//
//    override fun findAll(): List<Baby>
//
//    override fun getById(id: Long): Baby
//
//    override fun <S : Baby?> save(entity: S): S
//}
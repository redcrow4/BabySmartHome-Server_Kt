//package midashnt.service.babysmarthomekt.util
//
//import midashnt.service.babysmarthomekt.app.dto.Baby
//import midashnt.service.babysmarthomekt.app.mapper.BabyMapper
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.stereotype.Component
//import javax.annotation.PostConstruct
//
//@Component
//class StartConfig(@Autowired val babyMapper: BabyMapper) {
//
//    @PostConstruct
//    fun startJpa() {
//
//        babyMapper.createBaby(insertData())
//    }
//
//    fun insertData(): Baby {
//        val baby: Baby = Baby()
//        baby.babyNickName = "아기_1"
//        println("baby= $baby")
//        return baby
//    }
//}
package midashnt.service.babysmarthomekt.app.mapper

import midashnt.service.babysmarthomekt.app.dto.Baby
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface BabyMapper {

    fun createBaby(baby: Baby)

    fun getBabyByBabyIndex(babyIndex: Int): Baby

    fun getBabyListByLightIndex(lightIndex: Int): List<Baby>

    fun updateBaby(baby: Baby): Baby

    fun deleteBabyByBabyIndex(babyIndex: Int)
}
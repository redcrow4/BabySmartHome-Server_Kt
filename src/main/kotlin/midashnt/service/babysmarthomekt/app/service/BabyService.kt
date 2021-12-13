package midashnt.service.babysmarthomekt.app.service

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import midashnt.service.babysmarthomekt.app.dto.Baby
import midashnt.service.babysmarthomekt.app.mapper.BabyMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BabyService @Autowired constructor(val babyMapper: BabyMapper) {

    fun createBaby(baby: Baby): Unit = babyMapper.createBaby(baby)

    fun getBabyByBabyIndex(babyIndex: Int): Baby = babyMapper.getBabyByBabyIndex(babyIndex)

    fun getBabyListByLightIndex(lightIndex: Int): List<Baby> = babyMapper.getBabyListByLightIndex(lightIndex)

    fun getBabyAndHistoryByIndex(babyIndex: Int): String {
        val baby: Baby = babyMapper.getBabyByBabyIndex(babyIndex)
//        val historyList: List<History> = historyMapper.getHistoryListByBabyIndex(
//            mapOf(
//                KEY_BABY_INDEX to babyIndex,
//                "dataCnt"      to 5
//            )
//        )

        val jsonObject = JsonObject()
        val gson = GsonBuilder().serializeNulls().create()

        jsonObject.add("baby", gson.toJsonTree(baby))
//        jsonObject.add("historyList", gson.toJsonTree(historyList))
//        if (historyList.isNotEmpty()) {
//            jsonObject
//                .getAsJsonObject("baby")
//                .addProperty(
//                    "monitoring",
//                    if (null != historyList[historyList.size - 1].getSleepEnd()) 0 else 1
//                )
//        }

        return jsonObject.toString()
    }

    fun updateBaby(baby: Baby) = babyMapper.updateBaby(baby)

//    fun saveBabyImage(file: MultipartFile, babyIndex: Int): Any? {
//        val dbBaby: Baby = babyMapper.getBabyByBabyIndex(babyIndex)
//        return if (null != dbBaby) {
//            val uploadUrl: String = s3Service.upload(file, "$babyIndex.png")
//            val baby = Baby(babyIndex, uploadUrl)
//            babyMapper.updateBabyImagePath(baby)
//            baby
//        } else {
//            throw BabyNotFoundException(babyIndex)
//        }
//
//    }

    fun deleteBabyByBabyIndex(babyIndex: Int) = babyMapper.deleteBabyByBabyIndex(babyIndex)

}
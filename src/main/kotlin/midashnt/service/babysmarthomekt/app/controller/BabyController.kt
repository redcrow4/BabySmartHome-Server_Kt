package midashnt.service.babysmarthomekt.app.controller

import com.google.gson.GsonBuilder
import midashnt.service.babysmarthomekt.app.constants.PARAM_babyIndex
import midashnt.service.babysmarthomekt.app.constants.URL_SERVICE_BABIES
import midashnt.service.babysmarthomekt.app.constants.URL_VERSION_TWO
import midashnt.service.babysmarthomekt.app.dto.*
import midashnt.service.babysmarthomekt.app.service.BabyService
import midashnt.service.babysmarthomekt.app.util.ValidationUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

@ResponseStatus(HttpStatus.OK)
@RestController
@RequestMapping(
    value = [URL_VERSION_TWO + URL_SERVICE_BABIES],
    consumes = [MediaType.APPLICATION_JSON_VALUE],
    produces = [MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"]
)
class BabyController @Autowired constructor(private val babyService: BabyService) {

    @PostMapping // 아기 등록
    @ResponseStatus(HttpStatus.CREATED)
    fun createBaby(@RequestBody @Validated babyCreate: BabyCreate): String {
        println("baby=$babyCreate")
//        babyValidation(
//            mapOf<String, Any>(
//                KEY_LIGHT_INDEX      to baby.lightIndex,
//                KEY_BABY_NICK_NAME   to baby.babyNickName as Any,
//                KEY_BABY_BIRTHDAY    to baby.babyBirthday as Any,
//                KEY_BABY_GENDER_CODE to baby.babyGenderCode
//            )
//        )
//
//        baby.babyDeleteState = BABY_DELETE_STATE_ACTIVE
//        babyService.createBaby(baby)

        return GsonBuilder().serializeNulls().create().toJsonTree(babyCreate).asJsonObject.toString()
    }

    @GetMapping(PARAM_babyIndex) // 아기 조회(babyIndex)
    fun getBabyByBabyIndex(@PathVariable(KEY_BABY_INDEX) babyIndex: Int): Baby =
        babyService.getBabyByBabyIndex(babyIndex)

    @GetMapping("/lights/{{lightIndex}}") // 아기 조회(lightIndex)
    fun getBabyListByLightIndex(@PathVariable lightIndex: Int): List<Baby> =
        babyService.getBabyListByLightIndex(lightIndex)

    // 아기 조회(아기), 히스토리 조회(해당 아기, 5건)
    @GetMapping("$PARAM_babyIndex/histories")
    fun getBabyAndHistoryByIndex(@PathVariable(KEY_BABY_INDEX) babyIndex: Int): String {
        babyValidation(
            mapOf(KEY_LIGHT_INDEX to babyIndex)
        )

        return babyService.getBabyAndHistoryByIndex(babyIndex)
    }

    @PatchMapping(PARAM_babyIndex) // 아기 변경(이름, 생일, 성별)
    fun updateBaby(@PathVariable(KEY_BABY_INDEX) babyIndex: Int, @RequestBody baby: Baby): String? {
        val babyValidation = mutableMapOf<String, Any>()
        babyValidation[KEY_BABY_INDEX] = babyIndex
        if (null != baby.babyNickName) babyValidation[KEY_BABY_NICK_NAME] = baby.babyNickName!!
        if (null != baby.babyBirthday) babyValidation[KEY_BABY_BIRTHDAY] = baby.babyBirthday!!
        if (0 != baby.babyGenderCode) babyValidation[KEY_BABY_GENDER_CODE] = baby.babyGenderCode
        babyValidation(babyValidation)

        baby.babyIndex = babyIndex
        babyService.updateBaby(baby)

        return GsonBuilder().create()
            .toJsonTree(baby).asJsonObject.toString()
    }

    @PatchMapping(PARAM_babyIndex, consumes = ["*"]) // 아기 변경(이미지)
    @Throws(IOException::class)
    fun saveBabyImage(@PathVariable(KEY_BABY_INDEX) babyIndex: Int,
                      @RequestBody file: MultipartFile): String? {
        babyValidation(
            mapOf<String, Any>(
                KEY_BABY_INDEX to babyIndex,
                "file" to file as Any
            )
        )

//        return GsonBuilder().create()
//            .toJsonTree(babyService.saveBabyImage(file, babyIndex)).asJsonObject.toString()
        return null
    }

    @DeleteMapping(PARAM_babyIndex) // 아기 삭제
    fun deleteBabyByBabyIndex(@PathVariable(KEY_BABY_INDEX) babyIndex: Int) {
        babyValidation(mapOf(KEY_BABY_INDEX to babyIndex))
        babyService.deleteBabyByBabyIndex(babyIndex)
    }

    private fun babyValidation(validationValue: Map<String, Any>) {
        validationValue.forEach { (k, v) ->
            if (KEY_BABY_INDEX == k || KEY_LIGHT_INDEX == k) {
                ValidationUtil.index(v as Int)
            } else if (KEY_BABY_NICK_NAME == k) {
                ValidationUtil.babyNickName(v.toString())
            } else if (KEY_BABY_BIRTHDAY == k) {
                ValidationUtil.babyBirthday(v.toString())
            } else if (KEY_BABY_GENDER_CODE == k) {
                ValidationUtil.babyGenderCode(v as Int)
            } else if ("file" == k) {
                ValidationUtil.file(v as MultipartFile)
            } else {
//                LoggerAspect.LOGGER_API.error("Baby Controller validation miss= {}", k )
            }
        }
    }

}
//package midashnt.service.babysmarthomekt.app.controller
//
//import com.google.gson.Gson
//import com.google.gson.GsonBuilder
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.http.HttpStatus
//import org.springframework.web.bind.annotation.*
//
//class LightController @Autowired constructor(lightService: LightService) {
//
//    private val lightService: LightService
//
//    init {
//        this.lightService = lightService
//    }
//
//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping // 수유등 등록
//    fun createLight(@RequestBody deviceToken: DeviceToken): String {
//        lightValidation(object : HashMap<String, Any>() {
//            init {
//                put(DeviceToken.KEY_DEVICE_TOKEN, deviceToken.getDeviceToken())
//                put(DeviceToken.KEY_LIGHT_MAC, deviceToken.getLightMac())
//            }
//        })
//        var light = Light()
//        light.setLightMac(deviceToken.getLightMac())
//        light = lightService.createLight(light, deviceToken)
//        return GsonBuilder().serializeNulls().create()
//            .toJsonTree(light).asJsonObject.toString()
//    }
//
//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping(value = ["/{lightIndex}/change/{lightMac}"]) // 수유등 교체
//    fun changeLight(
//        @PathVariable(Light.KEY_LIGHT_INDEX) lightIndex: Int,
//        @PathVariable(Light.KEY_LIGHT_MAC) lightMac: String?
//    ): String {
//        lightValidation(object : HashMap<String?, Any?>() {
//            init {
//                put(Light.KEY_LIGHT_INDEX, lightIndex)
//                put(Light.KEY_LIGHT_MAC, lightMac)
//            }
//        })
//        val light = Light()
//        light.setLightMac(lightMac)
//        return GsonBuilder().create()
//            .toJsonTree(lightService.changeLight(light, lightIndex)).asJsonObject.toString()
//    }
//
//    @GetMapping(value = [PARAM_lightIndex]) // 수유등 조회
//    fun getLightByIndex(@PathVariable(Light.KEY_LIGHT_INDEX) lightIndex: Int): String {
//        lightValidation(object : HashMap<String?, Any?>() {
//            init {
//                put(Light.KEY_LIGHT_INDEX, lightIndex)
//            }
//        })
//        ValidationUtil.index(lightIndex)
//        return Gson().toJsonTree(lightService.getLightByIndex(lightIndex)).toString()
//    }
//
//    @PutMapping(value = ["/{lightMac}/led"]) // 수유등 조명 상태 변경
//    fun updateLightLed(
//        @PathVariable(Light.KEY_LIGHT_MAC) lightMac: String?,
//        @RequestBody lightLed: ShadowLight.ShadowLightLed
//    ) {
//        lightValidation(object : HashMap<String?, Any?>() {
//            init {
//                put(Light.KEY_LIGHT_MAC, lightMac)
//                put("color", lightLed.getColor())
//                put("level", lightLed.getLevel())
//            }
//        })
//        lightLed.setLightMac(lightMac)
//        lightService.updateLightLed(lightLed)
//    }
//
//    @PutMapping(value = ["/{lightMac}/led-schedule"]) // 수유등 조명 스케쥴 상태 변경
//    fun updateLedSchedule(
//        @PathVariable(Light.KEY_LIGHT_MAC) lightMac: String?,
//        @RequestBody lightLedSchedule: ShadowLight.ShadowLightLedSchedule
//    ) {
//        lightValidation(object : HashMap<String?, Any?>() {
//            init {
//                put(Light.KEY_LIGHT_MAC, lightMac)
//                put("color", lightLedSchedule.getColor())
//                put("level", lightLedSchedule.getLevel())
//                put("time", lightLedSchedule.getTime())
//            }
//        })
//        lightLedSchedule.setLightMac(lightMac)
//        lightService.updateLightLEDSchedule(lightLedSchedule)
//    }
//
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    @DeleteMapping(value = ["/{lightIndex}/{lightMac}"])
//    fun deleteLight(
//        @PathVariable(Light.KEY_LIGHT_INDEX) lightIndex: Int,
//        @PathVariable(Light.KEY_LIGHT_MAC) lightMac: String?
//    ) {
//        lightValidation(object : HashMap<String?, Any?>() {
//            init {
//                put(Light.KEY_LIGHT_INDEX, lightIndex)
//                put(Light.KEY_LIGHT_MAC, lightMac)
//            }
//        })
//        ValidationUtil.index(lightIndex)
//        lightService.deleteLight(lightIndex, lightMac)
//    }
//
//    private fun lightValidation(validationValue: Map<String, Any>) {
//        for ((key, value): Map.Entry<String, Any> in validationValue) {
//            if (Light.KEY_LIGHT_INDEX.equals(key)) {
//                ValidationUtil.index(value.toString().toInt())
//            } else if (Light.KEY_LIGHT_MAC.equals(key)) {
//                ValidationUtil.lightMac(value.toString())
//            } else if (DeviceToken.KEY_DEVICE_TOKEN.equals(key)) {
//                ValidationUtil.deviceToken(value.toString())
//            } else if (ShadowLight.ShadowLightLedSchedule.KEY_COLOR.equals(key)) {
//                ValidationUtil.ledColor(value.toString().toInt())
//            } else if (ShadowLight.ShadowLightLedSchedule.KEY_LEVEL.equals(key)) {
//                ValidationUtil.ledLevel(value.toString().toInt())
//            } else if (ShadowLight.ShadowLightLedSchedule.KEY_TIME.equals(key)) {
//                ValidationUtil.time(value.toString().toInt())
//            } else {
//                LoggerAspect.LOGGER_API.error("Light Controller validation miss= {}", key)
//            }
//        }
//    }
//}
package midashnt.service.babysmarthomekt.app.constants

interface CommonConstants

// 로그
const val API_LOG_PROFILE    = "API_LOG"
const val SYSTEM_LOG_PROFILE = "SYSTEM_LOG"
const val CLOUD_LOG_PROFILE  = "CLOUD_LOG"

// 버전
const val URL_VERSION_ONE = "/v1"
const val URL_VERSION_TWO = "/v2"

// 서비스
const val URL_SERVICE_BABIES        = "/babies"
const val URL_SERVICE_DEVICE_TOKENS = "/device-tokens"
const val URL_SERVICE_HISTORIES     = "/histories"
const val URL_SERVICE_LIGHTS        = "/lights"
const val URL_SERVICE_MATS          = "/mats"

const val URL_SERVICE_MIDAS_DEVICES = "/midas/devices"

// 인덱스
const val PARAM_babyIndex    = "/{babyIndex}"
const val PARAM_historyIndex = "/{historyIndex}"
const val PARAM_lightIndex   = "/{lightIndex}"
const val PARAM_lightMac     = "/{lightMac}"
const val PARAM_deviceToken  = "/{deviceToken}"
const val PARAM_matIndex     = "/{matIndex}"
const val PARAM_matMac       = "/{matMac}"
const val PARAM_matSlotIndex = "/{matSlotIndex}"
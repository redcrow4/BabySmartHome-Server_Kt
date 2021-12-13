package midashnt.service.babysmarthomekt.app.util

import midashnt.service.babysmarthomekt.app.dto.BABY_GENDER_CODE_BOY
import midashnt.service.babysmarthomekt.app.dto.BABY_GENDER_CODE_GIRL
import midashnt.service.babysmarthomekt.app.dto.KEY_BABY_BIRTHDAY
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.regex.Pattern


object ValidationUtil {
    private const val REGEX_LIGHT_MAC = "^([0-9A-Fa-f]{2}[:]){5}([0-9A-Fa-f]{2})$"
    private const val REGEX_MAT_MAC = "^([0-9A-Fa-f]{2}[:]){7}([0-9A-Fa-f]{2})$"
    private const val DATE_TIME_FORMAT_STR = "yyyy-MM-dd HH:mm:ss"
    private val DATE_TIME_FORMAT = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_STR)

    // 2000-01-01 ~ 2099-12-31 까지만 허용
    //    private static final String REGEX_BABY_BIRTHDAY = "^(20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$";
    fun index(indexValue: Int) {
        require(0 < indexValue) { "index가 없거나 잘못 됐습니다." }
    }

    fun babyNickName(babyNickName: String) {
        require(babyNickName.length in 1..20) { "babyNickName이 없거나 너무 깁니다." }
    }

    fun babyBirthday(babyBirthday: String) {
        dateValidation(babyBirthday, KEY_BABY_BIRTHDAY)
    }

    fun babyGenderCode(babyGenderCode: Int) {
        require(BABY_GENDER_CODE_BOY == babyGenderCode || BABY_GENDER_CODE_GIRL == babyGenderCode)
        { "babyGenderCode가 없거나 올바르지 않습니다. 예)1:Boy, 2:Girl" }
    }

    fun file(file: MultipartFile?) {
        require(!(null == file || file.isEmpty)) { "아기사진이 없거나 올바르지 않습니다." }
    }

    fun lightMac(lightMac: String?) {
        require(
            Pattern.matches(
                REGEX_LIGHT_MAC,
                lightMac
            )
        ) { "lightMac이 없거나 올바르지 않습니다. 예)00:00:00:00:00:00 ~ FF:FF:FF:FF:FF:FF" }
    }

    fun deviceToken(deviceToken: String) {
        require(!("" == deviceToken || deviceToken.isEmpty())) { "deviceToken이 없습니다." }
    }

    fun ledColor(ledColor: Int) {
        require(ledColor in 0..1) { "ledColor가 없거나 잘못 됐습니다. 예)0 ~ 1" }
    }

    fun ledLevel(ledLevel: Int) {
        require(ledLevel in 0..3) { "ledLevel이 없거나 잘못 됐습니다. 예)0 ~ 3" }
    }

    // todo 2021.11.15 led스케쥴 시간 범위 지정 필요
    fun time(time: Int) {
        require(time in 0..1000) { "time이 없거나 잘못 됐습니다." }
    }

//    fun sleepDate(sleepDate: String) {
//        dateValidation(sleepDate, History.KEY_SLEEP_DATE)
//    }
//
//    fun dateTime(dateTime: String) {
//        dateTimeValidation(dateTime, History.KEY_SLEEP_START + " 또는 " + History.KEY_SLEEP_END)
//    }

    fun matSlotIndex(matSlotIndex: Int) {
        require(matSlotIndex in 0..9) { "matSlotIndex이 없거나 잘못 됐습니다. 예)0 ~ 9" }
    }

    fun matName(matName: String) {
        require(matName.length in 1..20) { "matName이 없거나 너무 깁니다." }
    }

    fun matMac(matMac: String?) {
        require(Pattern.matches(REGEX_MAT_MAC, matMac)) {
            "matMac이 없거나 올바르지 않습니다." +
                    " 예)00:00:00:00:00:00:00:00 ~ FF:FF:FF:FF:FF:FF:FF:FF"
        }
    }

    private fun dateValidation(dateStr: String, valueName: String) {
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE)
        } catch (e: DateTimeParseException) {
            throw IllegalArgumentException("$valueName 가 없거나 날짜 포맷에 맞지 않습니다. 예)yyyy-MM-dd")
        }
    }

    private fun dateTimeValidation(dateTimeStr: String, valueName: String) {
        try {
            LocalDateTime.parse(dateTimeStr, DATE_TIME_FORMAT)
        } catch (e: DateTimeParseException) {
            throw IllegalArgumentException("$valueName 가 없거나 날짜 포맷에 맞지 않습니다. 예)$DATE_TIME_FORMAT_STR")
        }
    }
}
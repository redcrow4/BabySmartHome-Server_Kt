package midashnt.service.babysmarthomekt.app.dto

import com.google.gson.annotations.JsonAdapter
import midashnt.service.babysmarthomekt.app.util.annotation.IntIgnoreZeroAdapter
import javax.validation.constraints.Max
import javax.validation.constraints.Min

data class Baby constructor(
    @JsonAdapter(IntIgnoreZeroAdapter::class)
    @field:Min(1, message = "1보다 커..?")
    var babyIndex      : Int,
    @JsonAdapter(IntIgnoreZeroAdapter::class)
    var lightIndex     : Int,
    var babyNickName   : String?,
    var babyBirthday   : String?,
    @JsonAdapter(IntIgnoreZeroAdapter::class)
    var babyGenderCode : Int,
    var babyImagePath  : String?,
    @JsonAdapter(IntIgnoreZeroAdapter::class)
    var babyDeleteState: Int
)

data class BabyCreate constructor(
    @JsonAdapter(IntIgnoreZeroAdapter::class)
    @field:Min(1, message = "1보다 커..?")
    var lightIndex     : Int,
    @field:Min(1, message = "1보다 커..?")
    @field:Max(20, message = "20보다 작아야..")
    var babyNickName   : String?,
    var babyBirthday   : String?,
    @JsonAdapter(IntIgnoreZeroAdapter::class)
    var babyGenderCode : Int,
    var babyDeleteState: Int = BABY_DELETE_STATE_ACTIVE
)

const val KEY_BABY_INDEX = "babyIndex"
const val KEY_LIGHT_INDEX = "lightIndex"
const val KEY_BABY_NICK_NAME = "babyNickName"
const val KEY_BABY_BIRTHDAY = "babyBirthday"
const val KEY_BABY_GENDER_CODE = "babyGenderCode"
const val KEY_BABY_IMAGE_PATH = "babyImagePath"
const val KEY_BABY_DELETE_STATE = "babyDeleteState"

const val BABY_DELETE_STATE_ACTIVE = 1
const val BABY_DELETE_STATE_INACTIVE = 2

const val BABY_GENDER_CODE_BOY = 1
const val BABY_GENDER_CODE_GIRL = 2

const val COLUMN_NAME_BABY_INDEX        = "BABY_INDEX"
const val COLUMN_NAME_LIGHT_INDEX       = "LIGHT_INDEX"
const val COLUMN_NAME_BABY_BIRTHDAY     = "BABY_BIRTHDAY"
const val COLUMN_NAME_BABY_GENDER_CODE  = "BABY_GENDER_CODE"
const val COLUMN_NAME_BABY_IMAGE_PATH   = "BABY_IMAGE_PATH"
const val COLUMN_NAME_BABY_NICK_NAME    = "BABY_NICK_NAME"
const val COLUMN_NAME_BABY_DELETE_STATE = "BABY_DELETE_STATE"

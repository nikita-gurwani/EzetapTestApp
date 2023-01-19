package com.razorpay.ezetap_rest_sdk

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
data class CustomUIResponse(val customUI: CustomUI?) {
    data class CustomUI(
        @SerializedName("logo-url") val logoUrl: String,
        @SerializedName("heading-text") var headingText: String,
        @SerializedName("uidata") var uiData: List<UiData> = arrayListOf()
    ) : Serializable

    data class UiData(

        @SerializedName("uitype") var uitype: String,
        @SerializedName("value") var value: String,
        @SerializedName("key") var key: String,
        @SerializedName("hint") var hint: String
    ) : Serializable
}
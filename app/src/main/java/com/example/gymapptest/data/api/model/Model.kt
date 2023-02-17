package com.example.gymapptest.data.api.model
import com.google.gson.annotations.SerializedName
data class Model (

    @SerializedName("trainers" ) var trainers : ArrayList<Trainer> = arrayListOf(),
    @SerializedName("tabs"     ) var tabs     : ArrayList<Tab>     = arrayListOf(),
    @SerializedName("lessons"  ) var lessons  : ArrayList<Lesson>  = arrayListOf(),
    @SerializedName("option"   ) var option   : Option?             = Option()

):java.io.Serializable
data class Trainer (

    @SerializedName("id"               ) var id             : String? = null,
    @SerializedName("full_name"        ) var fullName       : String? = null,
    @SerializedName("name"             ) var name           : String? = null,
    @SerializedName("last_name"        ) var lastName       : String? = null,
    @SerializedName("position"         ) var position       : String? = null,
    @SerializedName("image_url"        ) var imageUrl       : String? = null,
    @SerializedName("image_url_small"  ) var imageUrlSmall  : String? = null,
    @SerializedName("image_url_medium" ) var imageUrlMedium : String? = null,
    @SerializedName("description"      ) var description    : String? = null

):java.io.Serializable
data class Tab (

    @SerializedName("id"   ) var id   : Int?    = null,
    @SerializedName("name" ) var name : String? = null

):java.io.Serializable
data class Lesson (

    @SerializedName("name"            ) var name           : String?  = null,
    @SerializedName("description"     ) var description    : String?  = null,
    @SerializedName("place"           ) var place          : String?  = null,
    @SerializedName("coach_id"        ) var coachId        : String?  = null,
    @SerializedName("startTime"       ) var startTime      : String?  = null,
    @SerializedName("endTime"         ) var endTime        : String?  = null,
    @SerializedName("date"            ) var date           : String?  = null,
    @SerializedName("appointment_id"  ) var appointmentId  : String?  = null,
    @SerializedName("service_id"      ) var serviceId      : String?  = null,
    @SerializedName("available_slots" ) var availableSlots : Int?     = null,
    @SerializedName("commercial"      ) var commercial     : Boolean? = null,
    @SerializedName("client_recorded" ) var clientRecorded : Boolean? = null,
    @SerializedName("is_cancelled"    ) var isCancelled    : Boolean? = null,
    @SerializedName("tab"             ) var tab            : String?  = null,
    @SerializedName("color"           ) var color          : String?  = null,
    @SerializedName("tab_id"          ) var tabId          : Int?     = null

):java.io.Serializable
data class Option (

    @SerializedName("club_name"       ) var clubName       : String? = null,
    @SerializedName("link_android"    ) var linkAndroid    : String? = null,
    @SerializedName("link_ios"        ) var linkIos        : String? = null,
    @SerializedName("primary_color"   ) var primaryColor   : String? = null,
    @SerializedName("secondary_color" ) var secondaryColor : String? = null

):java.io.Serializable

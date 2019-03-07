package com.isaacurbna.openlibrary.model

import com.google.gson.annotations.SerializedName

data class APIResponse(
        @SerializedName("docs")
        var docs: List<Doc?>?,
        @SerializedName("numFound")
        var numFound: Int?,
        @SerializedName("start")
        var start: Int?
)
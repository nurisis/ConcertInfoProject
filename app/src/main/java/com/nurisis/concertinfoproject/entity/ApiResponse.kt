package com.nurisis.concertinfoproject.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import org.simpleframework.xml.ElementList

/**
 * Response from public API
 * */
@Root(name = "response", strict = false)
data class ApiResponse(
    @field:Element(name = "msgBody")
    var apiBody: ApiBody = ApiBody()
)

@Root(name = "msgBody", strict = false)
data class ApiBody(
    @field:Element(name = "totalCount") // Total number of performances
    var totalCount:Int =0,
    @field:Element(name = "realmCode") // Type code
    var realmCode:String = "",
    @field:Element(name = "cPage") // Start page
    var cPage:Int =0 ,
    @field:Element(name = "rows") // Number of performances to be received at once
    var rows:Int =0,
    @field:Element(name = "sido") // Country
    var sido:String = "",
    @field:Element(name = "from") // Start date of performances
    var from:String = "",
    @field:Element(name = "sortStdr")
    var sortStdr: Int =0,

    // List of performances
    @field:ElementList(name = "perforList", inline = true, required = false)
    var perforList: List<ConcertItem> = mutableListOf()
)

/**
 * Performance information
 * */
@Entity(tableName = "concert_bookmark")
@Root(name = "perforList", strict = false)
data class ConcertItem(
    @PrimaryKey
    @field:Element(name = "title")
    var title:String = "",

    @field:Element(name = "place")
    var place:String = "",

    @field:Element(name = "thumbnail")
    var thumbnail:String = "",

    @field:Element(name = "startDate")
    var startDate:String = "",

    @field:Element(name = "endDate")
    var endDate:String = "",

    @field:Element(name = "area")
    var area:String = "",

    var created_time:String = ""
)

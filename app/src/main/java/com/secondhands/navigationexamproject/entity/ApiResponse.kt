package com.secondhands.navigationexamproject.entity

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import org.simpleframework.xml.ElementList


@Root(name = "response", strict = false)
data class ApiResponse(
    @field:Element(name = "msgBody")
    var apiBody: ApiBody = ApiBody()
)

@Root(name = "msgBody", strict = false)
data class ApiBody(
    @field:Element(name = "totalCount")
    var totalCount:Int =0,
    @field:Element(name = "realmCode")
    var realmCode:String = "",
    @field:Element(name = "cPage")
    var cPage:Int =0 ,
    @field:Element(name = "rows")
    var rows:Int =0,
    @field:Element(name = "sido")
    var sido:String = "",
    @field:Element(name = "from")
    var from:String = "",
    @field:Element(name = "sortStdr")
    var sortStdr: Int =0,

    @field:ElementList(name = "perforList", inline = true)
    var perforList: List<PerforList> = mutableListOf()
)

@Root(name = "perforList", strict = false)
data class PerforList(
    @field:Element(name = "title")
    var title:String = "",
    @field:Element(name = "place")
    var place:String = ""
)


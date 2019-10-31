package com.secondhands.navigationexamproject.entity

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

//data class ConcertItem(
//    var title:String = "",
//    var startDate:String = "",
//    var endDate:String = "",
//    var place:String = "",
//    var realmName:String = "",
//    var area: String = "",
//    var thumbnail:String = ""
//)

@Root(name = "perforList")
data class ConcertItem(
    @Element(name = "title")
    var title:String = "",

    @Element(name = "startDate")
    var startDate:String = "",

    @Element(name = "endDate")
    var endDate:String = "",

    @Element(name = "place")
    var place:String = "",

    @Element(name = "realmName")
    var realmName:String = "",

    @Element(name = "area")
    var area: String = "",

    @Element(name = "thumbnail")
    var thumbnail:String = ""
)
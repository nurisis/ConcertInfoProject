package com.secondhands.navigationexamproject.entity

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import kotlin.properties.Delegates

@Root(name = "msgBody", strict = false)
data class ApiMsgBody (

//    @Element(name = "totalCount")
    @field:Element(name = "totalCount")
    @param:Element(name = "totalCount")
    val totalCount: Long? = null

//    @get:Element var totalCount: Int = 0

//    @Element(name = "totalCount")
//    var totalCount:Long,

//    @Element(name = "cPage")
//    var cPage: Int = 1,
//
//    @Element(name = "rows")
//    var rows:Int = 0,
//
//    @Element(name = "realmCode")
//    var realmCode:String = "",
//
//    @Element(name = "sido")
//    var sido:String = "",
//
//    @Element(name = "from")
//    var from:String = "",
//
//    @Element(name = "sortStdr")
//    var sortStdr:String = "",

//    @ElementList(name = "perforList")
//    var perforList: List<ConcertItem>
)

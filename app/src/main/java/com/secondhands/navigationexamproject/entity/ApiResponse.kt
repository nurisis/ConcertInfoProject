package com.secondhands.navigationexamproject.entity

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

//data class ApiResponse(
//    var msgBody: ApiMsgBody = ApiMsgBody(),
//    var comMsgHeader:ApiMsgHeader = ApiMsgHeader()
//)

data class ApiMsgHeader(
    var RequestMsgID:String = "",
    var ResponseTime:String = "",
    var ResponseMsgID:Any = "",
    var SuccessYN:String = "",
    var ReturnCode:Int =0,
    var ErrMsg:String = ""
)

@Root
data class ApiResponse(
//    var msgBody: ApiMsgBody = ApiMsgBody(),
//    var comMsgHeader:ApiMsgHeader = ApiMsgHeader()
    @Element
    var totalCount: Int
)
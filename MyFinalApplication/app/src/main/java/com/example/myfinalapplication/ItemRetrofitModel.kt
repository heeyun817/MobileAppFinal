package com.example.myfinalapplication

// Json : JavaScript에서 데이터를 표현하는 방법, 웹에서 데이터를 주고받을 때 주로 사용
// Gson : json을 JAVA의 객체로 역직렬화, 직렬화 해주는 자바 라이브러리
// body.itmes[item.rnum]
data class ItemRetrofitModel(
    var ENTP_NAME: String? = null, //회사명
    var COB_CLASS_NAME: String? = null, //품목구분명
    var ITEM_NAME: String? = null, //품목명
    var VALID_TERM: String? = null, //유효기간
    var PERMIT_KFDA_NAME: String? = null, //신고식약청
    var CLINIC_EXAM_YN:String? = null // 임상시험여부

)

//data class MyItem(val item:ItemRetrofitModel)
data class MyItems(val items:MutableList<ItemRetrofitModel>)
data class MyModel(val body: MyItems)

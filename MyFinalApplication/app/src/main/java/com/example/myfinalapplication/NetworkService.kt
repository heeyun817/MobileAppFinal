package com.example.myfinalapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    // http://apis.data.go.kr/B553748/CertImgListService/
    // getCertImgListService?
    @GET("getEvaluationItemList")
    fun getList(
        @Query("ServiceKey") apikey:String,
        @Query("item_name") q:String, //()안에는 꼭 제공해주는 것과 똑같이 써야 됨(? 뒤에 들어가는 내용이 Query에 들어가는 값과 같아서)
        @Query("pageNo") page: Int,
        @Query("numOfRows") pageSize: Int,
        @Query("type") returnType: String
    ) : Call<MyModel>
}
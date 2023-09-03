package com.example.myfinalapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myfinalapplication.databinding.ItemRetrofitBinding

class MyRetrofitViewHolder(val binding: ItemRetrofitBinding): RecyclerView.ViewHolder(binding.root)

class MyRetrofitAdapter(val context: Context, val datas: MutableList<ItemRetrofitModel>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemCount(): Int{
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyRetrofitViewHolder(ItemRetrofitBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyRetrofitViewHolder).binding

//add......................................
        val model = datas!![position]
        binding.itemName.text = model.ITEM_NAME
        binding.itemEntp.text = "제조 회사 : "+model.ENTP_NAME
        binding.itemValid.text = "유효 기간 : "+model.VALID_TERM
        binding.itemCobclassname.text = "품목 구분 : "+model.COB_CLASS_NAME
        binding.itemKfda.text = "허가/신고기관 : " + model.PERMIT_KFDA_NAME
        binding.itemDoc.text = "임상시험여부(Y/N) : " + model.CLINIC_EXAM_YN

//        Glide.with(context)
//            .load(model.item.imgurl1)
//            .into(binding.itemImage2)
    }
}
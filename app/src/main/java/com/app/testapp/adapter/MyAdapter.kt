package com.app.testapp.adapter

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.testapp.R
import com.app.testapp.adapter.MyChildAdapter
import com.app.testapp.model.datamodel.DataItem
import kotlinx.android.synthetic.main.item_main.view.*
import java.util.*


class MyAdapter(
    var context: Context?,
    private var mList:ArrayList<DataItem>?
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val context = parent.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_main, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }





    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mList?.get(position))


        if (mList?.get(position) is DataItem) {
            val dataItem = mList?.get(position) as DataItem
            if (dataItem.isSelected) {
                holder?.itemView?.constraintBottom?.visibility=View.VISIBLE
                fade(holder?.itemView?.constraintBottom);

            } else {
                holder?.itemView?.constraintBottom?.visibility=View.GONE
                fadeOut(holder?.itemView?.constraintBottom);

            }
        }
    }


    fun fade(view: View) {
        val animation1: Animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        view.startAnimation(animation1)
    }

    fun fadeOut(view: View) {
        val animation1: Animation = AnimationUtils.loadAnimation(context, R.anim.fade_out)
        view.startAnimation(animation1)
    }




    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: DataItem?) {

            itemView?.txtTitle?.text =item?.name
            item?.icon?.let { itemView?.imageMain?.setImageResource(it) }

            itemView?.constraintChild.setOnClickListener {

                mList?.let {

                   it.get(adapterPosition).isSelected= !it.get(adapterPosition).isSelected;

                    notifyItemChanged(adapterPosition)
                }
            }

            var  mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            itemView?.recyclerChild?.layoutManager = mLayoutManager
            var mAdapter = MyChildAdapter(context, item?.childList)
            itemView?.recyclerChild?.adapter = mAdapter

        }

    }
}
package com.app.testapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.testapp.R
import com.app.testapp.model.datamodel.DataItem
import kotlinx.android.synthetic.main.item_main.view.*
import java.util.*


/**
 * Created by android on 15/11/18.
 *
 * this class only for used share key list.
 */
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
                holder.itemView.imageArrow.setImageResource(R.drawable.arrow)
                holder?.itemView?.recyclerChild?.visibility=View.VISIBLE
                holder?.itemView?.viewMain?.visibility=View.VISIBLE

            } else {
                holder.itemView.imageArrow.setImageResource(R.drawable.arrow)
                holder?.itemView?.recyclerChild?.visibility=View.GONE
                holder?.itemView?.viewMain?.visibility=View.GONE

            }
        }
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: DataItem?) {

            itemView?.txtTitle?.text =item?.name
            item?.icon?.let { itemView?.imageMain?.setImageResource(it) }

            itemView?.constraintChild.setOnClickListener {

                mList?.let {

                   it.get(adapterPosition).isSelected= !it.get(adapterPosition).isSelected;
                    notifyDataSetChanged()
                }
            }

            var  mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            itemView?.recyclerChild?.layoutManager = mLayoutManager
            var mAdapter = MyChildAdapter(context, item?.childList)
            itemView?.recyclerChild?.adapter = mAdapter

        }

    }
}
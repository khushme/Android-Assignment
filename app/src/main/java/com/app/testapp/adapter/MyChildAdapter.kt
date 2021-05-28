package com.app.testapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.testapp.R
import com.app.testapp.model.datamodel.ChildData
import kotlinx.android.synthetic.main.item_child_main.view.*
import kotlinx.android.synthetic.main.item_main.view.imageMain
import kotlinx.android.synthetic.main.item_main.view.txtTitle

import java.util.ArrayList

/**
 * Created by android on 15/11/18.
 *
 * this class only for used share key list.
 */
class MyChildAdapter(
    var context: Context?,
    private var mList:ArrayList<ChildData>?
) : RecyclerView.Adapter<MyChildAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val context = parent.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_child_main, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mList?.get(position))

    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ChildData?) {

            itemView?.txtTitle?.text =item?.name



            item?.icon?.let { itemView?.imageMain?.setImageResource(it) }


            if(adapterPosition== mList?.size?.minus(1) ?: 0){
                itemView?.viewBottom?.visibility =View.GONE

            }

        }

    }
}
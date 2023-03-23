package com.example.firstsubmission.UI.adapter

import android.service.autofill.UserData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firstsubmission.R
import com.example.firstsubmission.data.Model.ItemsItem

class GithubAdapter(private val listUserGithub: List<ItemsItem>): RecyclerView.Adapter<GithubAdapter.ViewHolder>(){
    private var onItemClickListener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: (position: Int) -> Unit) {
        onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                listener(position)
            }
        }
    }
    class ViewHolder (itemView:View):RecyclerView.ViewHolder(itemView){
        val imageData : ImageView = itemView.findViewById(R.id.iv_imageData)
        val username : TextView = itemView.findViewById(R.id.tv_username)

        fun bind(data: ItemsItem, listener: OnItemClickListener){
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_users,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount()= listUserGithub.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userData = listUserGithub[position]
        holder.username.text = userData.login
        Glide.with(holder.itemView.context)
            .load(userData.avatarUrl)
            .placeholder(R.drawable.baseline_account_circle_24)
            .into(holder.imageData)

        onItemClickListener?.let { listener ->
            holder.bind(listUserGithub[position], listener)
        }
    }
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}
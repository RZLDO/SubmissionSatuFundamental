package com.example.firstsubmission.UI.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firstsubmission.R
import com.example.firstsubmission.data.Model.FollowersResponseItem
import com.example.firstsubmission.data.Model.ItemsItem

class FollowersAdapter(private val followersData : List<FollowersResponseItem>):
    RecyclerView.Adapter<FollowersAdapter.ViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: (position: Int) -> Unit) {
        onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                listener(position)
            }
        }
    }
    class ViewHolder (view: View):RecyclerView.ViewHolder(view){
        val imagePhoto : ImageView = view.findViewById(R.id.iv_imageFollow)
        val username : TextView = view.findViewById(R.id.tv_username_follow)

        fun bind(data: FollowersResponseItem, listener: OnItemClickListener){
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_followers,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount() = followersData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val followers = followersData[position]
        holder.username.text = followers.login
        Glide.with(holder.itemView.context)
            .load(followers.avatarUrl)
            .placeholder(R.drawable.baseline_account_circle_24)
            .into(holder.imagePhoto)

        onItemClickListener?.let { listener ->
            holder.bind(followersData[position], listener)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}
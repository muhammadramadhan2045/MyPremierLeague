package com.example.premierleagueapp.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.premierleagueapp.core.R
import com.example.premierleagueapp.core.domain.model.Team
import com.example.premierleagueapp.core.databinding.ItemListTeamBinding

class TeamAdapter : RecyclerView.Adapter<TeamAdapter.ListViewHolder>() {


    private var listData= ArrayList<Team>()
    var onItemClick: ((Team) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Team>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_team, parent, false))

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data=listData[position]
        holder.bind(data)
    }
    inner class ListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        private val binding= ItemListTeamBinding.bind(itemView)
        fun bind(data: Team){
            with(binding){
                Glide.with(itemView.context)
                    .load(data.strTeamBadge)
                    .into(ivItemImage)
                tvItemTitle.text=data.strTeam
                tvItemSubtitle.text= StringBuilder().append("Est. ").append(data.intFormedYear)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }

    }

}
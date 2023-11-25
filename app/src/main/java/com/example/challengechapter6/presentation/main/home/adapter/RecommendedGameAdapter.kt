package com.example.challengechapter6.presentation.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challengechapter6.databinding.ListItemGamesBinding
import com.example.data.remote.response.main.GetAllGamesResponse

class RecommendedGameAdapter(private val onClick: OnClickListener): RecyclerView.Adapter<RecommendedGameAdapter.ViewHolder>() {

    private val diffCallBack = object: DiffUtil.ItemCallback<com.example.data.remote.response.main.GetAllGamesResponse>(){
        override fun areItemsTheSame(oldItem: com.example.data.remote.response.main.GetAllGamesResponse, newItem: com.example.data.remote.response.main.GetAllGamesResponse): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: com.example.data.remote.response.main.GetAllGamesResponse, newItem: com.example.data.remote.response.main.GetAllGamesResponse): Boolean {
            return oldItem.id == newItem.id
        }
    }

    private val differ = AsyncListDiffer(this,diffCallBack)
    fun submitData(value: List<com.example.data.remote.response.main.GetAllGamesResponse>?) = differ.submitList(value)

    interface OnClickListener {
        fun onClickItem (data: com.example.data.remote.response.main.GetAllGamesResponse)
    }

    inner class ViewHolder(private val binding: ListItemGamesBinding): RecyclerView.ViewHolder(binding.root){
        fun bind (data: com.example.data.remote.response.main.GetAllGamesResponse){
            Glide.with(binding.root)
                .load(data.thumbnail)
                .into(binding.ivThumbnail)
            binding.tvGameName.text = data.title
            binding.tvGenre.text = data.genre
            binding.root.setOnClickListener {
                onClick.onClickItem(data)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedGameAdapter.ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return ViewHolder(ListItemGamesBinding.inflate(inflate,parent,false))
    }

    override fun onBindViewHolder(holder: RecommendedGameAdapter.ViewHolder, position: Int) {
        val data = differ.currentList[position]
        data.let {
            holder.bind(data)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}
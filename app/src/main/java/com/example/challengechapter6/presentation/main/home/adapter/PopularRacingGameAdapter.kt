package com.example.challengechapter6.presentation.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challengechapter6.data.remote.response.main.GetAllGamesResponse
import com.example.challengechapter6.databinding.ListItemGamesBinding

class PopularRacingGameAdapter(private val onClick: OnClickListener): RecyclerView.Adapter<PopularRacingGameAdapter.ViewHolder>() {

    private val diffCallBack = object: DiffUtil.ItemCallback<GetAllGamesResponse>(){
        override fun areItemsTheSame(oldItem: GetAllGamesResponse, newItem: GetAllGamesResponse): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: GetAllGamesResponse, newItem: GetAllGamesResponse): Boolean {
            return oldItem.id == newItem.id
        }
    }

    private val differ = AsyncListDiffer(this,diffCallBack)
    fun submitData(value: List<GetAllGamesResponse>?) = differ.submitList(value)

    interface OnClickListener {
        fun onClickItem (data: GetAllGamesResponse)
    }

    inner class ViewHolder(private val binding: ListItemGamesBinding): RecyclerView.ViewHolder(binding.root){
        fun bind (data: GetAllGamesResponse){
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return ViewHolder(ListItemGamesBinding.inflate(inflate,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]
        data.let {
            holder.bind(data)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}
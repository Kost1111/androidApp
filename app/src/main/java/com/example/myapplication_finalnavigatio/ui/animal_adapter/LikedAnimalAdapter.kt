package com.example.myapplication_finalnavigatio.ui.animal_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication_finalnavigatio.R
import com.example.myapplication_finalnavigatio.database.LikedAnimalsInfo
import com.example.myapplication_finalnavigatio.databinding.LikedanimalsItemBinding


class LikedAnimalsDiffUtil(
    private val oldList: List<LikedAnimalsInfo>,
    private val newList: List<LikedAnimalsInfo>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}

interface LikedAnimalsItemListener {
    fun getInfoAboutLikedAnimal(id: Int)
    fun removeLikedAnimals(id: Int)
}


class LikedAnimalAdapter(private val likedAnimalsItemListener: LikedAnimalsItemListener) :
    RecyclerView.Adapter<LikedAnimalAdapter.LikedViewHolder>(), View.OnClickListener {

    var data: List<LikedAnimalsInfo> = emptyList()
        set(newValue) {
            val diffUtil = LikedAnimalsDiffUtil(field, newValue)
            val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
            field = newValue
            diffUtilResult.dispatchUpdatesTo(this@LikedAnimalAdapter)
        }

    override fun getItemCount(): Int = data.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LikedanimalsItemBinding.inflate(inflater, parent, false)
        binding.ivRemove.setOnClickListener(this)
        return LikedViewHolder(binding)
    }


    override fun onBindViewHolder(holder: LikedViewHolder, position: Int) {
        val likedAnimals = data[position]

        with(holder.binding) {
            ivRemove.tag = likedAnimals

            tvName.text = likedAnimals.name
            tvAbout.text = likedAnimals.description
            Glide.with(ivAvatar)
                .load(likedAnimals.imgURL)
                .error(R.drawable.error)
                .centerCrop()
                .into(ivAvatar)
        }
    }

    override fun onClick(view: View?) {
        val likedAnimals = view?.tag as LikedAnimalsInfo
        likedAnimalsItemListener.removeLikedAnimals(likedAnimals.id)
    }

    class LikedViewHolder(val binding: LikedanimalsItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}
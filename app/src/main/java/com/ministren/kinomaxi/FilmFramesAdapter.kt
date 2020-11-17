package com.ministren.kinomaxi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ministren.kinomaxi.databinding.ItemFilmFrameBinding

/**
 * Адаптер для списка кадров бильма
 */
class FilmFramesAdapter : RecyclerView.Adapter<FilmFramesAdapter.FilmFrameViewHolder>() {

    private val items = mutableListOf<FilmFrame>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmFrameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFilmFrameBinding.inflate(layoutInflater)
        return FilmFrameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmFrameViewHolder, position: Int) {
        holder.setData(items[position])
    }

    override fun getItemCount(): Int = items.size

    /**
     * Установить новый список кадров [frames] для отображения
     */
    fun setItems(frames: List<FilmFrame>) {
        items.clear()
        items.addAll(frames)
        notifyDataSetChanged()
    }

    /**
     * Класс для отображения элемента списка кадров бильма
     */
    class FilmFrameViewHolder(
        binding: ItemFilmFrameBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val imageView = binding.root

        /**
         * Загрузить изображение для предпросмотра кадра [frame] и отобразить его в текущем элементе списка
         */
        fun setData(frame: FilmFrame) {
            Glide.with(imageView).load(frame.previewUrl).into(imageView)
        }
    }

}
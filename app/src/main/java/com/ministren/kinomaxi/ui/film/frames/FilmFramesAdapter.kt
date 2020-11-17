package com.ministren.kinomaxi.ui.film.frames

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ministren.kinomaxi.databinding.ItemFilmFrameBinding
import com.ministren.kinomaxi.entity.FilmFrame

/**
 * Адаптер для списка кадров бильма
 */
class FilmFramesAdapter : RecyclerView.Adapter<FilmFrameViewHolder>() {

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

}
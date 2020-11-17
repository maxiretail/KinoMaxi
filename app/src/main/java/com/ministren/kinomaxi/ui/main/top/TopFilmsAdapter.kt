package com.ministren.kinomaxi.ui.main.top

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ministren.kinomaxi.databinding.ItemTopFilmBinding
import com.ministren.kinomaxi.entity.Film

/**
 * Адаптер для списка топ фильмов
 */
class TopFilmsAdapter : RecyclerView.Adapter<TopFilmViewHolder>() {

    private val items = mutableListOf<Film>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopFilmViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTopFilmBinding.inflate(layoutInflater)
        return TopFilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopFilmViewHolder, position: Int) {
        holder.setData(items[position].posterUrl)
    }

    override fun getItemCount(): Int = items.size

    /**
     * Установить новый список фильмов [films] для отображения
     */
    fun setItems(films: List<Film>) {
        items.clear()
        items.addAll(films)
        notifyDataSetChanged()
    }

}
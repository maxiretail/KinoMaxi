package com.ministren.kinomaxi.ui.main.top

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ministren.kinomaxi.databinding.ItemTopFilmBinding
import com.ministren.kinomaxi.ui.main.entity.FilmItemViewData

/**
 * Адаптер для списка топ фильмов
 */
class TopFilmsAdapter(
    private val onFilmClick: (filmId: Long) -> Unit,
) : RecyclerView.Adapter<TopFilmViewHolder>() {

    private val items = mutableListOf<FilmItemViewData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopFilmViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTopFilmBinding.inflate(layoutInflater)
        return TopFilmViewHolder(binding, onFilmClick)
    }

    override fun onBindViewHolder(holder: TopFilmViewHolder, position: Int) {
        holder.setData(items[position])
    }

    override fun getItemCount(): Int = items.size

    /**
     * Установить новый список фильмов [films] для отображения
     */
    fun setItems(films: List<FilmItemViewData>) {
        items.clear()
        items.addAll(films)
        notifyDataSetChanged()
    }

}
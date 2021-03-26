package ru.maxi.study.kinomaxi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.maxi.study.kinomaxi.databinding.ItemFilmFrameBinding

/**
 * Адаптер для списка кадров фильма
 */
class FilmFramesAdapter : RecyclerView.Adapter<FilmFrameViewHolder>() {

    private val items = mutableListOf<String>()

    /**
     * Установить новый список кадров [frames] для отображения
     */
    fun setItems(frames: List<String>) {
        items.clear()
        items.addAll(frames)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmFrameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFilmFrameBinding.inflate(layoutInflater)
        return FilmFrameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmFrameViewHolder, position: Int) {
        holder.setImageUrl(items[position])
    }

    override fun getItemCount(): Int = items.size

}
package com.ministren.kinomaxi.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ministren.kinomaxi.MainActivity
import com.ministren.kinomaxi.R
import com.ministren.kinomaxi.databinding.FragmentMainPageBinding
import com.ministren.kinomaxi.di.ViewModelFactory
import com.ministren.kinomaxi.entity.FavFilm
import com.ministren.kinomaxi.entity.Film
import com.ministren.kinomaxi.entity.FilmsTopType
import com.ministren.kinomaxi.extensions.setSubtitle
import com.ministren.kinomaxi.extensions.setTitle
import com.ministren.kinomaxi.ui.film.details.FilmDetailsFragment
import com.ministren.kinomaxi.ui.main.entity.FilmItemViewData
import com.ministren.kinomaxi.ui.main.entity.MainPageData
import com.ministren.kinomaxi.ui.main.top.TopFilmsAdapter

class MainPageFragment : Fragment() {

    private var _binding: FragmentMainPageBinding? = null
    private val binding get() = _binding!!

    private val mainPageViewModel: MainPageViewModel by viewModels() { ViewModelFactory.instance }

    private val favoriteFilmsAdapter = TopFilmsAdapter(this::onFilmClick)
    private val topBestFilmsAdapter = TopFilmsAdapter(this::onFilmClick)
    private val topPopularFilmsAdapter = TopFilmsAdapter(this::onFilmClick)
    private val topAwaitFilmsAdapter = TopFilmsAdapter(this::onFilmClick)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.favFilms.topFilmsView.adapter = favoriteFilmsAdapter
        binding.topBestFilms.topFilmsView.adapter = topBestFilmsAdapter
        binding.topPopularFilms.topFilmsView.adapter = topPopularFilmsAdapter
        binding.topAwaitFilms.topFilmsView.adapter = topAwaitFilmsAdapter

        binding.root.setOnRefreshListener {
            mainPageViewModel.loadData(byRefresh = true)
        }

        mainPageViewModel.state.observe(viewLifecycleOwner, this::showNewState)
        mainPageViewModel.favorites.observe(viewLifecycleOwner, this::showFavorites)

        if (mainPageViewModel.state.value == null) {
            mainPageViewModel.loadData()
        }
    }

    private fun onFilmClick(filmId: Long) {
        (activity as? MainActivity)?.showFragment(FilmDetailsFragment.getInstance(filmId))
    }

    private fun showNewState(state: MainPageState) {
        when (state) {
            MainPageState.Loading -> {
                binding.root.isEnabled = false
                binding.root.isRefreshing = false
                binding.loaderView.visibility = View.VISIBLE
                binding.contentView.visibility = View.GONE
                binding.errorView.visibility = View.GONE
            }
            MainPageState.Error -> {
                binding.root.isEnabled = true
                binding.root.isRefreshing = false
                binding.loaderView.visibility = View.GONE
                binding.contentView.visibility = View.GONE
                binding.errorView.visibility = View.VISIBLE
            }
            is MainPageState.Loaded -> {
                binding.root.isEnabled = true
                binding.root.isRefreshing = false
                binding.loaderView.visibility = View.GONE
                binding.contentView.visibility = View.VISIBLE
                binding.errorView.visibility = View.GONE
                showData(state.data)
            }
            MainPageState.Refreshing -> {
                binding.root.isEnabled = true
                binding.root.isRefreshing = true
            }
        }
    }

    private fun showData(data: MainPageData) {
        setTitle(getString(R.string.app_name))
        setSubtitle(null)

        binding.topBestFilms.topFilmsTitle.text = data.topBestFilms.type.getTitle()
        binding.topPopularFilms.topFilmsTitle.text = data.topPopularFilms.type.getTitle()
        binding.topAwaitFilms.topFilmsTitle.text = data.topAwaitFilms.type.getTitle()

        topBestFilmsAdapter.setItems(data.topBestFilms.films.map { it.toFilmItemViewData() })
        topPopularFilmsAdapter.setItems(data.topPopularFilms.films.map { it.toFilmItemViewData() })
        topAwaitFilmsAdapter.setItems(data.topAwaitFilms.films.map { it.toFilmItemViewData() })
    }

    private fun showFavorites(favoriteFilms: List<FavFilm>) {
        binding.favFilms.root.visibility = if (favoriteFilms.isEmpty()) View.GONE else View.VISIBLE
        binding.favFilms.topFilmsTitle.text = getString(R.string.fav_film_list_title)
        favoriteFilmsAdapter.setItems(favoriteFilms.map { it.toFilmItemViewData() })
    }

    private fun FilmsTopType.getTitle(): String {
        val stringResId = when (this) {
            FilmsTopType.TOP_250_BEST_FILMS -> R.string.top_best_title
            FilmsTopType.TOP_100_POPULAR_FILMS -> R.string.top_popular_title
            FilmsTopType.TOP_AWAIT_FILMS -> R.string.top_await_title
        }
        return getString(stringResId)
    }
}

private fun Film.toFilmItemViewData() = FilmItemViewData(
    id = id,
    posterUrl = posterUrl
)

private fun FavFilm.toFilmItemViewData() = FilmItemViewData(
    id = filmId,
    posterUrl = posterUrl
)
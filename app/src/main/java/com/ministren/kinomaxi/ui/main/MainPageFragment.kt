package com.ministren.kinomaxi.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ministren.kinomaxi.R
import com.ministren.kinomaxi.databinding.FragmentMainPageBinding
import com.ministren.kinomaxi.di.ViewModelFactory
import com.ministren.kinomaxi.entity.FilmsTopType
import com.ministren.kinomaxi.ui.film.details.FilmDetailsFragment
import com.ministren.kinomaxi.ui.main.entity.MainPageData
import com.ministren.kinomaxi.ui.main.top.TopFilmsAdapter

class MainPageFragment : Fragment() {

    private var _binding: FragmentMainPageBinding? = null
    private val binding get() = _binding!!

    private val mainPageViewModel: MainPageViewModel by viewModels() { ViewModelFactory.instance }
    private val topBestFilmsAdapter = TopFilmsAdapter(this::onFilmClick)
    private val topPopularFilmsAdapter = TopFilmsAdapter(this::onFilmClick)
    private val topAwaitFilmsAdapter = TopFilmsAdapter(this::onFilmClick)

    @IdRes
    private var containerResId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        container?.let {
            containerResId = it.id
        }
        _binding = FragmentMainPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topBestFilms.topFilmsView.adapter = topBestFilmsAdapter
        binding.topPopularFilms.topFilmsView.adapter = topPopularFilmsAdapter
        binding.topAwaitFilms.topFilmsView.adapter = topAwaitFilmsAdapter

        binding.root.setOnRefreshListener {
            mainPageViewModel.loadData(byRefresh = true)
        }

        mainPageViewModel.state.observe(viewLifecycleOwner, this::showNewState)

        if (mainPageViewModel.state.value == null) {
            mainPageViewModel.loadData()
        }
    }

    private fun onFilmClick(filmId: Long) {
        parentFragmentManager.beginTransaction()
            .replace(containerResId, FilmDetailsFragment.getInstance(filmId))
            .addToBackStack(null)
            .commit()
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
        binding.topBestFilms.topFilmsTitle.text = data.topBestFilms.type.getTitle()
        binding.topPopularFilms.topFilmsTitle.text = data.topPopularFilms.type.getTitle()
        binding.topAwaitFilms.topFilmsTitle.text = data.topAwaitFilms.type.getTitle()

        topBestFilmsAdapter.setItems(data.topBestFilms.films)
        topPopularFilmsAdapter.setItems(data.topPopularFilms.films)
        topAwaitFilmsAdapter.setItems(data.topAwaitFilms.films)
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
# Учебный проект для курса Android-разработки в ВоГУ

## Описание проекта

Разрабатываемое приложение - клиент для сайта [КиноПоиск](https://www.kinopoisk.ru)

## API

В качестве источника данных используется неофициальное API сайта КиноПоиск: [kinopoiskapiunofficial.tech](https://kinopoiskapiunofficial.tech)

## Материалы курса

[bit.ly/maxi-android-course](https://bit.ly/maxi-android-course)

## Задание на релизацию главной страницы приложения

1. Объект `Network` предоставляет доступ к RestAPI. Пример использвания в `FilmDetailsFragment` при совершении запроса:
```kotlin
val filmDataCall = Network.apiService.getFilmData(263531)
```

2. В файле `layout/fragment_main_page.xml` реализована вёрстка экрана главной страницы. Файл `layout/layout_top_films.xml` определяет вёрстку отдельной подборки фильмов на главной странице (подборка имеет название и список фильмов в виде карточек).

3. Создать файл `layout/item_top_film.xml` и реализовать в нём вёрстку одного элемента списка фильмов. Предлагается использовать компонент `CardView` (карточка), и отображать в нём по крайней мере изображение постера и любую дополнительную информацию по желанию, например название фильма. `CardView` обладает следующими свойствами:
```text
cardCornerRadius - задаёт размер скругления углов
cardElevation - задаёт размер тени под карточкой
cardUseCompatPadding="true" - добавляет для краёв карточки дополнительный отступ для корректного отображения тени

В CardView может быть вложена только одна View дочернего уровня.
Например, это может быть один из контейнеров (Frame/Linear/Constraint Layout) с дочерними View.
```

4. Создать новый фрагмент для главной страницы `MainPageFragment`, а также классы реализующие `RecyclerView.Adapter` и `RecyclerView.ViewHolder` для списка фильмов.

5. В KinopoiskApiService добавить определение функции для работы с RestAPI `/api/v2.2/films/top`

6. Получать и устанавливать данные из нового реста на новый фрагмент главной страницы.

7. Реализовать переход на `FilmDetailsFragment` по клику на карточку фильма в подборке:
- Adapter и ViewHolder принимают в конструкторе обработчик клика:
```kotlin
private val onFilmClick: (filmId: Int) -> Unit
```
- При создании адаптера на фрагменте ему передаётся ссылка на метод - обработчик клика:
```kotlin
private val filmsAdapter = FilmsAdapter(this::onFilmClick)

@IdRes
private var containerResId: Int = 0

override fun onCreateView(
  inflater: LayoutInflater,
  container: ViewGroup?,
  savedInstanceState: Bundle?,
): View? {
  container?.let { containerResId = it.id }
  // Создание связки View и ViewBinding
  return binding.root
}

private fun onFilmClick(filmId: Long) {
  parentFragmentManager.beginTransaction()
    .replace(containerResId, FilmDetailsFragment(filmId))
    .addToBackStack(null)
    .commit()
}
```
- Во ViewHolder'е необходимо привязать обработку клика одной из View к обработчику:
```kotlin
view.setOnClickListener { onFilmClick(film.id) }
```
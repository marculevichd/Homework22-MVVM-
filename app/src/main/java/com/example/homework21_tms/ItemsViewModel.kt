package com.example.homework21_tms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homework21_tms.model.ItemsModel




class ItemsViewModel:ViewModel() {


    private val _items = MutableLiveData<List<ItemsModel>>()
    val items: LiveData<List<ItemsModel>> = _items

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    private val _bundle = MutableLiveData<NavigateWithBundle>()
    val bundle: LiveData<NavigateWithBundle> = _bundle


    fun getData(){

        val listItems = listOf<ItemsModel>(
            ItemsModel(
                "Изгой",
                R.string.cast_away,
                "",
                R.drawable.castaway,
                R.drawable.empty_star
            ),
            ItemsModel(
                "Город в котором меня нет",
                R.string.city,
                "",
                R.drawable.city,
                R.drawable.empty_star
            ),
            ItemsModel(
                "Игра престолов",
                R.string.game_of_thrones,
                "",
                R.drawable.gameofthrones,
                R.drawable.empty_star
            ),
            ItemsModel(
                "Талантливый мистер Рипли",
                R.string.the_talented_mr_ripley,
                "",
                R.drawable.mrripley,
                R.drawable.empty_star
            ),
            ItemsModel(
                "По ту сторону изгороди",
                R.string.over_the_garden_wall,
                "",
                R.drawable.overthegardenwall,
                R.drawable.empty_star
            )
        )
        _items.value=listItems
    }


    fun imageViewClicked(){
        _msg.value = "click"
    }


    fun elementClicked(title: String, description: Int, time: String, image: Int){
        _bundle.value = NavigateWithBundle(
            title = title,
            description = description,
            time = time,
            image = image
        )
    }


}







data class NavigateWithBundle(
    val title: String,
    val description: Int,
    val time: String,
    val image: Int,
)
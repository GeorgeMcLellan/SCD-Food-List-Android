package com.development.georgemcl.scdgapsapp

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.development.georgemcl.scdgapsapp.databinding.ActivityMainBinding
import com.development.georgemcl.scdgapsapp.databinding.DialogViewFoodInfoBinding
import com.development.georgemcl.scdgapsapp.objects.AIPFoodList
import com.development.georgemcl.scdgapsapp.objects.Food
import com.development.georgemcl.scdgapsapp.utils.KeyboardUtil
import timber.log.Timber

class MainActivity: AppCompatActivity(), FoodSelectedListener {

    lateinit var binding: ActivityMainBinding

    private val foodsAdapter = FoodsRecyclerViewAdapter(this)

    private val foods = AIPFoodList.foods.sortedBy { it.name }
    private var filterText: String = ""
    private val filteredFoods: List<Food> get() = if (filterText.isNotBlank()) {
        foods.filter { it.name.contains(filterText, ignoreCase = true) }
    } else {
        foods
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        syncRecyclerViewAdapter()
        binding.mainFoodsRecyclerView.apply {
            adapter = foodsAdapter
            layoutManager = LinearLayoutManager(context)
        }
        val toprint = foods.map { "${it.name} | ${it.allowed} ${if (it.description.isNotEmpty()) "|" else ""} ${it.description}"  }
        for (food in toprint) {
            Timber.d(food)
        }
//        val foodNames = foods.map { it.name }
//        Timber.d(foodNames.groupingBy { it }.eachCount().filter { it.value > 1 }.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchView = menu?.findItem(R.id.menu_main_search_view)?.actionView as SearchView
        searchView.apply {
            setOnSearchClickListener {
                supportActionBar?.setDisplayShowTitleEnabled(false)
            }
            setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    binding.mainParentLayout.requestFocus()
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    filterText = newText ?: ""
                    syncRecyclerViewAdapter()
                    return false
                }
            })
            setOnCloseListener {
                supportActionBar?.setDisplayShowTitleEnabled(true)
                KeyboardUtil.hideSoftKeyboard(this@MainActivity)
                false
            }
        }
        return true
    }

    override fun foodSelected(food: Food) {
        val dialogBinding = DataBindingUtil.inflate<DialogViewFoodInfoBinding>(
            LayoutInflater.from(this),
            R.layout.dialog_view_food_info,
            null,
            false
        )
        dialogBinding.food = food
        val dialog = Dialog(this)
        dialog.setContentView(dialogBinding.root)
        dialog.show()
    }
    private fun syncRecyclerViewAdapter() {
        foodsAdapter.submitList(filteredFoods)
    }
}
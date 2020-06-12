package com.development.georgemcl.eliminationdietapp

import android.animation.LayoutTransition
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.Menu
import android.widget.CheckBox
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.development.georgemcl.eliminationdietapp.databinding.ActivityMainBinding
import com.development.georgemcl.eliminationdietapp.databinding.DialogViewFoodInfoBinding
import com.development.georgemcl.eliminationdietapp.objects.Food
import com.development.georgemcl.eliminationdietapp.objects.FoodList
import com.development.georgemcl.eliminationdietapp.utils.KeyboardUtil
import timber.log.Timber

class MainActivity: AppCompatActivity(), FoodSelectedListener {

    lateinit var binding: ActivityMainBinding

    private val foodsAdapter by lazy {
        FoodsRecyclerViewAdapter(this)
    }

    private val foods = FoodList.foods
    private var filterText: String = ""
    private val filteredFoods: List<Food> get() = if (filterText.isNotBlank()) {
        foods.filter { it.name.contains(filterText, ignoreCase = true) }
    } else {
        foods
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("oncreate")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        syncRecyclerViewAdapter()
        binding.mainFoodsRecyclerView.apply {
            adapter = foodsAdapter
            layoutManager = LinearLayoutManager(context)
        }



//        binding.mainToolbarSearch.apply {
//            layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
//            setOnQueryTextListener(object: SearchView.OnQueryTextListener {
//                override fun onQueryTextSubmit(query: String?): Boolean {
//                    KeyboardUtil.hideSoftKeyboard(this@MainActivity)
//                    return false
//                }
//
//                override fun onQueryTextChange(newText: String?): Boolean {
//                    filterText = newText ?: ""
//                    syncRecyclerViewAdapter()
//                    return false
//                }
//            })
//            setOnCloseListener {
//                KeyboardUtil.hideSoftKeyboard(this@MainActivity)
//                false
//            }
//        }
//        Timber.d("list ${foodsAdapter.currentList}")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchView = menu?.findItem(R.id.menu_main_search_view)?.actionView as SearchView
        searchView.apply {
            setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    KeyboardUtil.hideSoftKeyboard(this@MainActivity)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    filterText = newText ?: ""
                    syncRecyclerViewAdapter()
                    return false
                }
            })
            setOnCloseListener {
                KeyboardUtil.hideSoftKeyboard(this@MainActivity)
                false
            }
        }
        return true
    }

    override fun foodSelected(food: Food) {
        if (food.description.isNotEmpty()) {
            val dialogBinding = DataBindingUtil.inflate<DialogViewFoodInfoBinding>(
                LayoutInflater.from(this),
                R.layout.dialog_view_food_info,
                null,
                false
            )
            dialogBinding.food = food
//            val createListDialogLayout = layoutInflater.inflate(R.layout.dialog_add_category_list, null)
//            val listNameEditText = createListDialogLayout.findViewById<TextInputEditText>(R.id.dialog_add_list_name_input_edit_text)
//            val privateCheckBox = createListDialogLayout.findViewById<CheckBox>(R.id.dialog_add_list_private_checkbox)
            val dialog = Dialog(this)
            dialog.setContentView(dialogBinding.root)
            dialog.show()
        }
    }
    private fun syncRecyclerViewAdapter() {
        foodsAdapter.submitList(filteredFoods)
    }
}
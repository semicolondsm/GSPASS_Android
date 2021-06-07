package com.example.gspass_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.gspass_android.base.SingleLiveEvent
import com.example.gspass_android.databinding.*
import com.example.gspass_android.viewmodel.MainViewModel

class MealAdapter(val viewModel : MainViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_ONE = 1
    private val TYPE_TWO = 2
    private val TYPE_THREE = 3
    private val TYPE_FOUR = 4
    private val TYPE_FIVE = 5

    var pos = SingleLiveEvent<Int>()

    inner class PagerViewHolder(val binding: ItemMealsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, viewModel : MainViewModel) {
            binding.position = position
            binding.vm = viewModel
            println("1번")
        }
    }
    inner class PagerViewHolder2(val binding: ItemMeals2Binding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, viewModel : MainViewModel) {
            binding.position = position
            binding.vm = viewModel
            println("2번")

        }
    }
    inner class PagerViewHolder3(val binding: ItemMeals3Binding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, viewModel : MainViewModel) {
            binding.position = position
            binding.vm = viewModel
            println("3번")
        }
    }
    inner class PagerViewHolder4(val binding: ItemMeals4Binding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, viewModel : MainViewModel) {
            binding.position = position
            binding.vm = viewModel
            println("4번")

        }
    }
    inner class PagerViewHolder5(val binding: ItemMeals5Binding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, viewModel : MainViewModel) {
            binding.position = position
            binding.vm = viewModel
            println("5번")

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType){
            TYPE_FOUR ->{
                val binding = ItemMealsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                PagerViewHolder(binding)
            }
            TYPE_FIVE -> {
                val binding =
                    ItemMeals2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
                PagerViewHolder2(binding)
            }
            TYPE_ONE -> {
                val binding = ItemMeals3Binding.inflate(LayoutInflater.from(parent.context),parent,false)
                PagerViewHolder3(binding)
            }
            TYPE_TWO -> {
                val binding = ItemMeals4Binding.inflate(LayoutInflater.from(parent.context),parent,false)
                PagerViewHolder4(binding)
            }
            else -> {
                val binding = ItemMeals5Binding.inflate(LayoutInflater.from(parent.context),parent,false)
                PagerViewHolder5(binding)
            }
        }
    }

    override fun getItemCount(): Int = Int.MAX_VALUE
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        fun setMeal(){
            pos.setValue(position-1073741822)
            println("이 숫자를 관찰 해야됨 $position")
        }
        if(position % 5 ==3){
            (holder as PagerViewHolder)
            holder.bind(position, viewModel)
            setMeal()
        }
        if(position % 5 == 4){
            (holder as PagerViewHolder2)
            holder.bind(position, viewModel)
            setMeal()
        }
        if(position % 5 == 0){
            (holder as PagerViewHolder3)
            holder.bind(position, viewModel)
            setMeal()
        }
        if(position % 5 == 1){
            (holder as PagerViewHolder4)
            holder.bind(position, viewModel)
            setMeal()
        }
        if(position % 5 == 2){
            (holder as PagerViewHolder5)
            holder.bind(position, viewModel)
            setMeal()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(position % 5 ==0){
            TYPE_ONE
        }else if(position % 5 == 1){
            TYPE_TWO
        }else if(position % 5 ==2){
            TYPE_THREE
        }
        else if(position % 5 == 3){
            TYPE_FOUR
        }else{
            TYPE_FIVE
        }
    }
}
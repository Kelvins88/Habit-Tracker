package com.example.habittracker.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.habittracker.R

@BindingAdapter("habitIcon")
fun loadHabitIcon(imageView: ImageView, photoUrl: String?) {
    when (photoUrl) {
        "Baca" -> imageView.setImageResource(R.drawable.icon_baca)
        "Fitness" -> imageView.setImageResource(R.drawable.icon_fitness)
        "Meditasi" -> imageView.setImageResource(R.drawable.icon_meditasi)
        "Water" -> imageView.setImageResource(R.drawable.icon_water)
        else -> imageView.setImageResource(R.mipmap.ic_launcher)
    }
}
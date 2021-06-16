package com.redspot.kotlinpractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.redspot.kotlinpractice.ui.details.DetailsFragment
import com.redspot.kotlinpractice.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    /**
     * Пока что переход между фрагментами не работает. Для теста другого фрагмента нужно внести
     * изменения здесь
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DetailsFragment.newInstance()) // DetailsFragment.newInstance()
                .commitNow()
        }
    }
}
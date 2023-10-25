package com.example.weahter.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.manager.Lifecycle
import com.example.weahter.ui.Days.Days
import com.example.weahter.ui.Today.Today
import kotlin.time.Duration.Companion.days

class FragmentpageAdapter(
    fragmentManager: FragmentManager,
    lifecycle:androidx.lifecycle.Lifecycle

) :FragmentStateAdapter(fragmentManager,lifecycle){
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
      return  if(position==0){
          Today()
        }
        else
            Days()
    }

}
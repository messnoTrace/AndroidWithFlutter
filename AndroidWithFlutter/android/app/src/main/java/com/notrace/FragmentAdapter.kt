package com.notrace

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter

class FragmentAdapter<T : Fragment>(fm: FragmentManager) : FragmentStatePagerAdapter(
    fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {

    val titleList = mutableListOf<String>()
    val titleResList = mutableListOf<Int>()
    val fragmentList = mutableListOf<T>()
    override fun getItem(position: Int): Fragment {

        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    public fun removeTitle(position: Int):FragmentAdapter<T>{
       titleList.removeAt(position)
        return this

    }

    fun addFragment(index:Int,f:T):FragmentAdapter<T>{
        fragmentList.add(index,f)
        return this

    }

    fun addFragment(f:T,title:String):FragmentAdapter<T>{
        titleList.add(title)
        fragmentList.add(f)
        return this
    }
    fun addFragment(index:Int,f:T,titleRes:Int):FragmentAdapter<T>{
        titleResList.add(index,titleRes)
        fragmentList.add(index,f)
        return this
    }


    fun updateFragment(index:Int,f:T,titleRes:Int):FragmentAdapter<T>{

        fragmentList.set(index,f)
        titleResList.set(index,titleRes)
        return this
    }

    fun updateFragment(index:Int,f:T):FragmentAdapter<T>{
        fragmentList.set(index,f)
        return this
    }



    fun addFragment(f:T):FragmentAdapter<T>{
        fragmentList.add(f)
        return this
    }

    fun getFragment(position: Int):T{

        return fragmentList[position]
    }

    fun getFragmentLists():List<T>{
        return fragmentList
    }
    fun getTitleLists():List<String>{
        return titleList
    }
    fun updateTitle(position: Int,title: String){
        titleList.set(position,title)
    }
    override fun getItemPosition(`object`: Any): Int {
        if (`object` != null) {

            if (`object` is Fragment) {
                if (fragmentList.contains(`object`)) {
                    return fragmentList.indexOf(`object`)
                } else {
                    return PagerAdapter.POSITION_NONE
                }
            } else {
                return super.getItemPosition("")
            }
        } else {
            return super.getItemPosition("")
        }
    }
}
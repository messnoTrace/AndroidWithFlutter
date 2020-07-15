package com.notrace

import android.os.Bundle
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val mPageAdapter by lazy {
        FragmentAdapter<Fragment>(supportFragmentManager)
    }

    val firstFragment by lazy {
        FirstFragment.newInstance()
    }
    val secondFragment by lazy {
        SecondFragment.newInstance()
    }
    val threeFragment by lazy {
        ThreeFragment.newInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

    }

    var currentIndex=0
    private var isFirstInitFlutterView = true
    fun init(){

        mPageAdapter.addFragment(firstFragment)
        mPageAdapter.addFragment(secondFragment)
        mPageAdapter.addFragment(threeFragment)
        mViewPager.offscreenPageLimit=mPageAdapter.count
        mViewPager.adapter=mPageAdapter
        mViewPager.currentItem = 0
        mViewPager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {

                if (position==2){
                    if (isFirstInitFlutterView){
                        isFirstInitFlutterView=false
                        initFlutterFragment()
                    }
                }
            }
        })
        rg_foot_bar.setOnCheckedChangeListener { group, checkId ->

            when(checkId){
                R.id.radio_button_one->{
                    currentIndex=0
                }
                R.id.radio_button_two->{
                    currentIndex=1
                }
                R.id.radio_button_three->{
                    currentIndex=2
                    if (isFirstInitFlutterView){
                        isFirstInitFlutterView=false
                        initFlutterFragment()
                    }
                }
                else->{}
            }
            mViewPager.currentItem=currentIndex
        }

    }

    fun initFlutterFragment(){
        mPageAdapter.updateFragment(2,MyFlutterFragment.newInstance("tab_fragment","ssssss"))
        mPageAdapter.notifyDataSetChanged()
    }
}
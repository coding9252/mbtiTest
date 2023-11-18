package com.leegahyeon.mbtitest

import android.content.Intent
import android.media.tv.AdResponse
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import java.util.jar.Attributes.Name

class TestActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2

    val  questionnaireResults = QuestionnaireResults()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = ViewPagerAdapter(this)
        viewPager.isUserInputEnabled = false
    }

    fun moveToNextQuestion(){
        if (viewPager.currentItem==3){
            //마지막 페이지 -> 결과 화면으로 이동
            val intent = Intent(this, ResultActivity::class.java)
            intent.putIntegerArrayListExtra("results", ArrayList(questionnaireResults.results))
            startActivity(intent)


        }else{
            val nextItem = viewPager.currentItem + 1  //currentItem 현재 페이지 번호
            if (nextItem < viewPager.adapter?.itemCount ?: 0) {
                viewPager.setCurrentItem(nextItem,true)
            }
        }
    }
}

class QuestionnaireResults {
    val results = mutableListOf<Int>()  //1,2,1,2

    fun addResponses(response: List<Int>) {  //2,1,2
        val mostFrequent = response.groupingBy { it }.eachCount().maxByOrNull { it.value }?.key
        mostFrequent?.let { results.add(it) }
    }
}
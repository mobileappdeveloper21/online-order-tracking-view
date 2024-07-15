package com.orderTrackerIndicator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.orderTrackerIndicator.databinding.ActivityMainBinding
import com.orderTrackerIndicator.util.DummyObj

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    var descriptionData = arrayOf("Details", "Status", "Photo", "Confirm")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setView()

    }

    private fun setView() {

        binding.trackerStatusProgressId.addTrackerViews(DummyObj.verticalOrderListData(this))
    }
}
package com.example.openinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import android.graphics.Color
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.openinapp.databinding.ActivityMainBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var isBlue: Boolean = true
    private lateinit var lineChart: LineChart
    lateinit var appViewModel: AppViewModel
    lateinit var linkAdapter: LinkAdapter
    var rams: ApiResult? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val openInAppApi = RetrofitHelper.getInstance(this).create(OpenInAppApi::class.java)
        val resultRepo = ResultRepo(openInAppApi)
        appViewModel =
            ViewModelProvider(this, AppViewModelFactory(resultRepo)).get(AppViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setGreeting()
        runBlocking {
            val job = launch {
                val result = appViewModel.getResults()
                if (result.body() != null) {
                    rams = result.body()
                }
            }
            job.join()
        }
        setUpTopLinkRV()
        setUpDataResult()
        binding.recentLinks.setOnClickListener {
            setBG(!isBlue, binding.topLinks)
            setBG(isBlue, binding.recentLinks)
            setUpRecentLinkRV()
            linkAdapter.notifyDataSetChanged()
        }
        binding.topLinks.setOnClickListener {
            setBG(isBlue, binding.topLinks)
            setBG(!isBlue, binding.recentLinks)
            setUpTopLinkRV()
            linkAdapter.notifyDataSetChanged()
        }
    }

    private fun setUpDataResult() {
        val dataResult: DataResult? =
            rams?.let {
                DataResult(
                    it.startTime,
                    it.support_whatsapp_number,
                    it.today_clicks.toString(),
                    it.top_location,
                    it.top_source,
                    it.total_clicks.toString(),
                    it.total_links.toString()
                )
            }
        binding.result = dataResult


        setLineChart()
    }

    private fun setLineChart() {
        lineChart = binding.lineChart
        val s = rams?.data?.overall_url_chart
        val entries = mutableListOf<Entry>()
        if (s != null) {
            entries.add(Entry(0f, s.`2023-04-22`.toFloat()))
            entries.add(Entry(1f, s.`2023-04-23`.toFloat()))
            entries.add(Entry(2f, s.`2023-04-24`.toFloat()))
            entries.add(Entry(3f, s.`2023-04-25`.toFloat()))
            entries.add(Entry(4f, s.`2023-04-26`.toFloat()))
            entries.add(Entry(5f, s.`2023-04-27`.toFloat()))
            entries.add(Entry(6f, s.`2023-04-28`.toFloat()))
            entries.add(Entry(7f, s.`2023-04-29`.toFloat()))
            entries.add(Entry(8f, s.`2023-04-30`.toFloat()))
            entries.add(Entry(9f, s.`2023-05-01`.toFloat()))
            entries.add(Entry(10f, s.`2023-05-02`.toFloat()))
        }

        val dataSet = LineDataSet(entries, "Data")
        dataSet.color = Color.BLUE
        dataSet.valueTextColor = Color.BLACK
        val lineData = LineData(dataSet)

        // Customize the appearance of the chart
        lineChart.apply {
            data = lineData
            description.isEnabled = true
            description.text = "Overall"
            description.setPosition(3f, 3f)
            setDrawGridBackground(true)
            animateX(1000)
            legend.isEnabled = false

            // Configure X-axis
            xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                setDrawGridLines(false)
                valueFormatter =
                    IndexAxisValueFormatter(getXAxisLabels()) // Custom labels for X-axis
            }

            // Configure Y-axis
            axisLeft.apply {
                setDrawGridLines(true)
                axisMinimum = 0f
            }

            axisRight.isEnabled = false

            // Refresh the chart
            invalidate()
        }
    }

    private fun getXAxisLabels(): List<String> {
        // Replace with your custom labels for X-axis
        return listOf(
            "2023-04-22",
            "2023-04-23",
            "2023-04-24",
            "2023-04-25",
            "2023-04-26",
            "2023-04-27",
            "2023-04-28",
            "2023-04-29",
            "2023-04-30",
            "2023-05-01",
        )
    }


    private fun setGreeting() {
        val time = Calendar.getInstance().time
        val f = SimpleDateFormat("HH", Locale.getDefault())
        val current = f.format(time)
        binding.greet.text = if (current.toInt() in 8..12) "Good Morning"
        else if (current.toInt() in 13..18) "Good Afternoon"
        else "Good Evening"

    }

    private fun setBG(isBlue: Boolean, tv: TextView) {
        if (isBlue) {
            tv.setBackgroundResource(R.drawable.blue_bg)
            tv.setTextColor(ContextCompat.getColor(this, R.color.white))
        } else {
            tv.setBackgroundResource(R.color.my_grey)
            tv.setTextColor(ContextCompat.getColor(this, R.color.brown))
        }
    }

    private fun setUpTopLinkRV() {
        linkAdapter = LinkAdapter(rams?.data?.top_links as ArrayList<TopLink>)
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = linkAdapter
    }

    private fun setUpRecentLinkRV() {
        linkAdapter = LinkAdapter(rams?.data?.recent_links as ArrayList<TopLink>)
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = linkAdapter
    }
}
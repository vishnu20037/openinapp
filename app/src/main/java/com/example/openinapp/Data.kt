package com.example.openinapp

data class Data(
    val overall_url_chart: OverallUrlChart,
    val recent_links: List<TopLink>,
    val top_links: List<TopLink>
)
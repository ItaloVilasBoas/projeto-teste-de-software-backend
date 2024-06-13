package com.movielist.external.dto

data class AggregateWatchProvider(var link: String?, var buy: List<WatchProvider>?, var flatrate: List<WatchProvider>?, var rent: List<WatchProvider>?) {
    constructor(): this("", ArrayList(), ArrayList(), ArrayList())
}
data class WatchProvider(var logo_path: String?, var provider_id: Int?, var provider_name: String?, var display_priority: Int?) {
    constructor(): this("", Integer.MIN_VALUE, "", Integer.MIN_VALUE)
}

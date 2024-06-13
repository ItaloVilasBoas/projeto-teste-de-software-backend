package com.movielist.external.dto

class TmdbWatchProvidersDTO (
    var id: Int,
    var results: LinkedHashMap<String, AggregateWatchProvider>
) {
    constructor(): this(Integer.MIN_VALUE, LinkedHashMap())
}

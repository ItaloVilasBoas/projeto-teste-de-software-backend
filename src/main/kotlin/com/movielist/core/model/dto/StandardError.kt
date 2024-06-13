package com.movielist.core.model.dto

import java.time.Instant

class StandardError(
    var timestamp: Instant,
    var status: Int,
    var error: String?,
    var message: String?,
    var path: String?
) {
    constructor() : this(Instant.MIN, Int.MIN_VALUE, "", "", "")
}
package com.movielist

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients


@SpringBootApplication
@EnableFeignClients
class MyMovieListApi

fun main(args: Array<String>) {
	runApplication<MyMovieListApi>(*args)
}

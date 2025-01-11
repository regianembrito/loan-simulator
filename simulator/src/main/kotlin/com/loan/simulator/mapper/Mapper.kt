package com.loan.simulator.mapper

interface Mapper<T, U> {
    fun map(t: T): U
}
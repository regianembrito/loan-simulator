package com.loan.simulator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.loan.simulator", "com.loan.simulator.test"])

class LoanSimulatorApplication

fun main(args: Array<String>) {
	runApplication<LoanSimulatorApplication>(*args)
}

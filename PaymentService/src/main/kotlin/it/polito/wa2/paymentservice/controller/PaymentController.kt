package it.polito.wa2.paymentservice.controller

import it.polito.wa2.paymentservice.entities.Payment
import it.polito.wa2.paymentservice.services.PaymentService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PaymentController {

    @Autowired
    lateinit var paymentService: PaymentService

    private val principal = ReactiveSecurityContextHolder.getContext()
        .map { it.authentication.principal as Long }

    @GetMapping("/admin/transactions")
    fun getAllOrders(): Flow<Payment> {
        return paymentService.getAllPayments()
    }

    @GetMapping("/transactions")
    suspend fun getAllOrdersByUser(): Flow<Payment> {
        val userId = principal.awaitSingle()
        return paymentService.getAllPaymentsByUser(userId)
    }

}
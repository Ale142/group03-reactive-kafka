package it.polito.wa2.bankservicemock.entities

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class PaymentRequest(

    @JsonProperty("paymentId")
    val paymentId: Long,

    @JsonProperty("creditCardNumber")
    val creditCardNumber: Int,

    @JsonProperty("cvv")
    val cvv: Int,

    @JsonProperty("expirationDate")
    val expirationDate: String,

    @JsonProperty("amount")
    val amount: Int

)

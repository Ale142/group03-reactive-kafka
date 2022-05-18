package it.polito.wa2.paymentservice.entities

import com.fasterxml.jackson.annotation.JsonProperty

data class PaymentResponse(

    @JsonProperty("paymentId")
    val paymentId: String,

    @JsonProperty("status")
    val status: Int

)

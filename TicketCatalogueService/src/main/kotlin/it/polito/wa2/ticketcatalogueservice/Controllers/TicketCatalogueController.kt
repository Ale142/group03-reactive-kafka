package it.polito.wa2.ticketcatalogueservice.Controllers

import it.polito.wa2.ticketcatalogueservice.Entities.Order
import it.polito.wa2.ticketcatalogueservice.Entities.Ticket
import it.polito.wa2.ticketcatalogueservice.Services.TicketCatalogueService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.core.context.SecurityContext
import org.springframework.web.bind.annotation.*

@RestController
class TicketCatalogueController {
    @Autowired
    lateinit var ticketCatalogueService: TicketCatalogueService

    @GetMapping("/admin/orders")
    fun getAllOrders(): Flow<Order> {
        return ticketCatalogueService.getAllOrders()
    }

    @GetMapping("/admin/orders/{id}")
    suspend fun getAllUserOrdersAdmin(@PathVariable id: Long): Flow<Order> {
        return ticketCatalogueService.getAllUserOrders(id)
    }

    @GetMapping("/tickets")
    fun getAllTickets(): Flow<Ticket> {
        return ticketCatalogueService.getAllTickets()
    }

    @PostMapping("/admin/tickets")
    suspend fun addNewTicket(@RequestBody newTicket: Ticket): Flow<Ticket> {
        return ticketCatalogueService.createNewTicket(newTicket)
    }

    @GetMapping("/orders")
    suspend fun getAllUserOrders(): Flow<Order> {
        //TODO Fix coroutine --- Error 500
        var authorizedUser : SecurityContext?
        withContext(Dispatchers.IO) {
            authorizedUser =
                ReactiveSecurityContextHolder.getContext().block()
        }
        return ticketCatalogueService.getAllUserOrders(authorizedUser.toString().toLong())
    }

    @GetMapping("/orders/{id}")
    suspend fun getOrderById(@PathVariable id: Long): Order? {
        return ticketCatalogueService.getOrderById(id)
    }
}
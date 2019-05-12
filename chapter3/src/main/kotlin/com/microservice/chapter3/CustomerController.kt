package com.microservice.chapter3

import com.microservice.chapter3.error.CustomNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap
import java.util.logging.Filter
import javax.xml.ws.Response

@RestController
class CustomerController {
    @Autowired
    lateinit var customerService: CustomerService


    // GET요청을 받으면 200 못찾으면 404
    @GetMapping(value = "/customer/{id}" )
    //PathVariable을 이용해 URL로 들어오는 값을 얻을 수 있다
    fun getCustomer(@PathVariable id : Int) : ResponseEntity<Customer?> {
        var customer = customerService.getCustomer(id) ?:
        throw CustomNotFoundException("customer `$id` not found")
        return ResponseEntity(customer, HttpStatus.OK)
    }

    @GetMapping(value = "/customers")
    fun getCustomers(@RequestParam(required = false, defaultValue = "") nameFilter: String ) : ResponseEntity<List<Customer>> {
        var customer = customerService.searchCustomers(nameFilter)
        var status = if ( customer ==null) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(customer, status)
    }

    @PostMapping(value ="/customer/" ) // @RequestMapping(value ="" , method=arrayOf(RequestMethod.POST)
    fun createCustomer(@RequestBody customer : Customer) : ResponseEntity<Unit?> { //Objectmapper을 사용해 지정된 클래스의 JVM 객체를 생성
        customerService.createCustomer(customer)
        return ResponseEntity<Unit?>(null, HttpStatus.CREATED)  //존재하지 않는 ID를 가지고 delete나 update를 호출하면 빈객체가 반환됨
    } // RequestBody를 사용해 객체를 보내도록 설정




    @RequestMapping(value="/customer/{id}" ,method = arrayOf(RequestMethod.DELETE)) //RequestMapping 대신 DeleteMapping 사용 가능
    fun deleteCustomer(@PathVariable id: Int) : ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if( customerService.getCustomer(id) != null)
        {
            customerService.deleteCustomer(id)
            status = HttpStatus.OK
        }
        return ResponseEntity(Unit, status)
    }

    //puT을이용한 갱신
    @PutMapping(value="/customer/{id}") // @RequestMapping(value="/customer/{id}", method = arrayOf(RequestMethod.PUT))
    fun updateCustomer(@PathVariable id : Int, @RequestBody customer : Customer) : ResponseEntity<Unit?> {
        var status = HttpStatus.NOT_FOUND
        if (customerService.getCustomer(id) != null)
        {
            customerService.updateCustomer(id, customer)
            status = HttpStatus.ACCEPTED
        }
        return ResponseEntity(Unit, status)
    }
}

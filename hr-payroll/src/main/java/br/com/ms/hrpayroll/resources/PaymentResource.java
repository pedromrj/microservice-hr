package br.com.ms.hrpayroll.resources;

import br.com.ms.hrpayroll.entity.Payment;
import br.com.ms.hrpayroll.services.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResource {

    @Autowired
    private PaymentService service;

    @HystrixCommand(fallbackMethod = "getPaymentsAlternative")
    @GetMapping(value = "/{workerId}/days/{days}")
    public ResponseEntity<Payment> getPayment(@PathVariable Long workerId, @PathVariable int days) {
        return ResponseEntity.ok(service.getPayment(workerId,days));
    }

    public ResponseEntity<Payment> getPaymentsAlternative( Long workerId, int days) {
        Payment payment = new Payment("Brann", 400.0, days);
        return ResponseEntity.ok(payment);
    }


}

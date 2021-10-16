package br.com.ms.hrpayroll.services;

import br.com.ms.hrpayroll.entity.Payment;
import br.com.ms.hrpayroll.entity.Worker;
import br.com.ms.hrpayroll.interfaces.WorkerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {

    @Autowired
    private WorkerFeignClient workerFeignClient;

    public Payment getPayment(long workerId, int days) {
        //Map<String,String> uriVariable = new HashMap<String, String>();
        //uriVariable.put("id", String.valueOf(workerId));
        //Worker worker = restTemplate.getForObject(workerHost.concat("/workers/{id}"), Worker.class, uriVariable);
        Worker worker = workerFeignClient.findById(workerId).getBody();
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }

}

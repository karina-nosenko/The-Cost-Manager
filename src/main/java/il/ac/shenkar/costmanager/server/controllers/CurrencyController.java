package il.ac.shenkar.costmanager.server.controllers;

import il.ac.shenkar.costmanager.CostManagerException;
import il.ac.shenkar.costmanager.entities.Currency;
import il.ac.shenkar.costmanager.server.models.CurrencyModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CurrencyController {

    @RequestMapping
    public String hello() {
        return "Hello from Cost Manager Server!";
    }

    @RequestMapping("/currencies")
    public List<Currency> getAll() throws ClassNotFoundException, CostManagerException {

        CurrencyModel currencyModel = new CurrencyModel();
        return currencyModel.getAll();
    }
}

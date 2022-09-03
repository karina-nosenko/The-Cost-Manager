package il.ac.shenkar.costmanager.server.controllers;

import il.ac.shenkar.costmanager.CostManagerException;
import il.ac.shenkar.costmanager.entities.Currency;
import il.ac.shenkar.costmanager.server.models.CurrencyModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CurrencyController {

    @RequestMapping
    public String hello() {
        return "Hello from Cost Manager Server✨";
    }

    @GetMapping("/currencies")
    public List<Currency> getAll() throws CostManagerException {

        CurrencyModel currencyModel = new CurrencyModel();
        return currencyModel.getAll();
    }

    @GetMapping("/currencies/{currencyId}")
    public Currency getById(@PathVariable("currencyId") String currencyId) throws CostManagerException {

        CurrencyModel currencyModel = new CurrencyModel();
        return currencyModel.getById(currencyId);
    }

    @PostMapping("/currencies")
    public void add(@RequestBody Currency currencyObj) throws CostManagerException {

        CurrencyModel currencyModel = new CurrencyModel();
        currencyModel.add(currencyObj);
    }

    @DeleteMapping("/currencies/{currencyId}")
    public void delete(@PathVariable("currencyId") String currencyId) throws CostManagerException {

        CurrencyModel currencyModel = new CurrencyModel();
        currencyModel.delete(currencyId);
    }
}

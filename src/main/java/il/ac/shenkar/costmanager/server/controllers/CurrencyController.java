package il.ac.shenkar.costmanager.server.controllers;

import il.ac.shenkar.costmanager.CostManagerException;
import il.ac.shenkar.costmanager.entities.Currency;
import il.ac.shenkar.costmanager.server.models.CurrencyModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Currencies REST API
 */
@RestController
public class CurrencyController {

    /**
     * Default path
     * @return hello message
     */
    @RequestMapping
    public String hello() {
        return "Hello from Cost Manager Server✨";
    }

    /**
     * Get all currencies
     * @return currencies list
     * @throws CostManagerException
     */
    @GetMapping("/currencies")
    public List<Currency> getAll() throws CostManagerException {

        CurrencyModel currencyModel = new CurrencyModel();
        return currencyModel.getAll();
    }

    /**
     * Get currency by currencyId
     * @param currencyId
     * @return currency with the given currencyId
     * @throws CostManagerException
     */
    @GetMapping("/currencies/{currencyId}")
    public Currency getById(@PathVariable("currencyId") String currencyId) throws CostManagerException {

        CurrencyModel currencyModel = new CurrencyModel();
        return currencyModel.getById(currencyId);
    }

    /**
     * Add new currency
     * @body currencyObj
     * @throws CostManagerException
     */
    @PostMapping("/currencies")
    public void add(@RequestBody Currency currencyObj) throws CostManagerException {

        CurrencyModel currencyModel = new CurrencyModel();
        currencyModel.add(currencyObj);
    }

    /**
     * Delete a currency with the given currencyId
     * @param currencyId
     * @throws CostManagerException
     */
    @DeleteMapping("/currencies/{currencyId}")
    public void delete(@PathVariable("currencyId") String currencyId) throws CostManagerException {

        CurrencyModel currencyModel = new CurrencyModel();
        currencyModel.delete(currencyId);
    }
}

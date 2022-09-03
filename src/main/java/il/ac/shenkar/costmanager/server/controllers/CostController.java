package il.ac.shenkar.costmanager.server.controllers;

import il.ac.shenkar.costmanager.CostManagerException;
import il.ac.shenkar.costmanager.entities.Cost;
import il.ac.shenkar.costmanager.server.models.CostModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CostController {

    @GetMapping("/costs")
    public List<Cost> getAll() throws CostManagerException {

        CostModel costModel = new CostModel();
        return costModel.getAll();
    }

    @GetMapping("/costs/users/{userId}")
    public List<Cost> getByUserId(@PathVariable("userId") String userId) throws CostManagerException {

        CostModel costModel = new CostModel();
        return costModel.getByUserId(userId);
    }

    @GetMapping("/costs/{costId}")
    public Cost getById(@PathVariable("costId") String costId) throws ClassNotFoundException, CostManagerException {

        CostModel costModel = new CostModel();
        return costModel.getById(costId);
    }

    @PostMapping("/costs")
    public void add(@RequestBody Cost costObj) throws CostManagerException {

        CostModel costModel = new CostModel();
        costModel.add(costObj);
    }

    @DeleteMapping("/costs/{costId}")
    public void delete(@PathVariable("costId") String costId) throws CostManagerException {

        CostModel costModel = new CostModel();
        costModel.delete(costId);
    }
}

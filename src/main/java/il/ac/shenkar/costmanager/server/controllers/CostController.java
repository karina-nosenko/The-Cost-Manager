package il.ac.shenkar.costmanager.server.controllers;

import il.ac.shenkar.costmanager.CostManagerException;
import il.ac.shenkar.costmanager.entities.Cost;
import il.ac.shenkar.costmanager.server.models.CostModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Costs REST API
 */
@RestController
public class CostController {

    /**
     * Get all costs
     * @return costs list
     * @throws CostManagerException
     */
    @GetMapping("/costs")
    public List<Cost> getAll() throws CostManagerException {

        CostModel costModel = new CostModel();
        return costModel.getAll();
    }

    /**
     * Get all the costs of the given user
     * @param userId
     * @return costs list with the given userId
     * @throws CostManagerException
     */
    @GetMapping("/costs/users/{userId}")
    public List<Cost> getByUserId(@PathVariable("userId") String userId) throws CostManagerException {

        CostModel costModel = new CostModel();
        return costModel.getByUserId(userId);
    }

    /**
     * Get cost by costId
     * @param costId
     * @return cost with the given costId
     * @throws CostManagerException
     */
    @GetMapping("/costs/{costId}")
    public Cost getById(@PathVariable("costId") String costId) throws ClassNotFoundException, CostManagerException {

        CostModel costModel = new CostModel();
        return costModel.getById(costId);
    }

    /**
     * Add new cost
     * @body costObj
     * @throws CostManagerException
     */
    @PostMapping("/costs")
    public void add(@RequestBody Cost costObj) throws CostManagerException {

        CostModel costModel = new CostModel();
        costModel.add(costObj);
    }

    /**
     * Delete a cost with the given costId
     * @param costId
     * @throws CostManagerException
     */
    @DeleteMapping("/costs/{costId}")
    public void delete(@PathVariable("costId") String costId) throws CostManagerException {

        CostModel costModel = new CostModel();
        costModel.delete(costId);
    }
}

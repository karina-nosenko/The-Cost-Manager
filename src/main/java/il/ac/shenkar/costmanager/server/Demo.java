package il.ac.shenkar.costmanager.server;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo {

    @RequestMapping
    public ResponseEntity<Object> exchangeRate() {
        String json = "{ \"USD\": 3.243, \"GBP\": 3.9093, \"JPY\": 2.3986, \"EUR\": 3.2987 }";
        JSONObject obj = new JSONObject(json);

        return new ResponseEntity<>(obj.toMap(), HttpStatus.OK);
    }
}

package th.mfu;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
public class HelloController {

    @RequestMapping("/aaa")
    String hello() {
        return "Hello World!";
    }

    String[] validName = { "Tao1", "Tao2", "Tao3" };

    @GetMapping("/hi/{name}")
    ResponseEntity<String> hi(@PathVariable String name) {
        // return new ResponseEntity<>("Hi "+name, HttpStatus.OK);
        //
        for (String a : validName) {
            if (a.equals(name)) {
                return ResponseEntity.ok("Hi, " + name);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
package pl.mkapiczy.springsecuritytest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController("b")
@RequiredArgsConstructor
public class ControllerB {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public HttpEntity<String> validateToken() {
        return new HttpEntity<>("b");
    }
}

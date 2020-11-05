package pl.mkapiczy.springsecuritytest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController("a")
@RequiredArgsConstructor
public class ControllerA {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public HttpEntity<String> a() {
        return new HttpEntity<>("a");
    }

}

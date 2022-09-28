package uqtr.ecompagnon.config;

import org.springframework.context.annotation.Configuration;

import java.util.function.Predicate;
@Configuration
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
        return true;
    }
}

package blu.cloud.catalogservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
    }

    public ProductNotFoundException(String s) {
        super(s);
    }

    public ProductNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ProductNotFoundException(Throwable throwable) {
        super(throwable);
    }

    public ProductNotFoundException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}

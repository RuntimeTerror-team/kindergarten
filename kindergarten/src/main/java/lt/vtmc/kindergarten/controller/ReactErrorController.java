package lt.vtmc.kindergarten.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ReactErrorController implements ErrorController {
    // Not clear if the right resource imported
    @Value("classpath:public/index.html")
    private Resource index;
    @RequestMapping("/error")
    public ResponseEntity<Resource> index(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if (statusCode == HttpStatus.UNAUTHORIZED.value() ||
                    statusCode == HttpStatus.FORBIDDEN.value()) {
                return ResponseEntity.status(HttpStatus.valueOf(statusCode)).build();
            }
        }
        return ResponseEntity.ok(index);
    }
    @Override
    public String getErrorPath() {
        return null;
    }
}
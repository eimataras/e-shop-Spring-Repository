package lt.eimantas.eshop.reqResLogger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class MyCustomInterceptor extends HandlerInterceptorAdapter {

    private static Logger log = LoggerFactory.getLogger(MyCustomInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("--------------------- interceptor preHandle ----------------------------------------- ");
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        log.info("--------------------- interceptor postHandle ---------------------------------------- ");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        log.info("--------------------- interceptor afterCompletion ----------------------------------- ");
//        log.info("Exception: [" + ex + "]");
    }
}

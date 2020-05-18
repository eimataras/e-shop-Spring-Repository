//package lt.eimantas.eshop.req_res_logger;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpRequest;
//import org.springframework.http.client.ClientHttpRequestExecution;
//import org.springframework.http.client.ClientHttpRequestInterceptor;
//import org.springframework.http.client.ClientHttpResponse;
//import org.springframework.util.StreamUtils;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.concurrent.atomic.AtomicInteger;
//
//
//public class LoggingInterceptor implements ClientHttpRequestInterceptor {
//
//    private final Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);
//    private AtomicInteger requestNumberSequence = new AtomicInteger(0);
//
//    @Override
//    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
//        int requestNumber = requestNumberSequence.incrementAndGet();
//        logRequest(requestNumber, request, body);
//        ClientHttpResponse response = execution.execute(request, body);
//        response = new BufferedClientHttpResponse(response);
//        logResponse(requestNumber, response);
//        return response;
//    }
//
//    private void logRequest(int requestNumber, HttpRequest request, byte[] body) {
//        if (log.isDebugEnabled()) {
//            String prefix = requestNumber + " > ";
//            log.debug("{} Request: {} {}", prefix, request.getMethod(), request.getURI());
//            log.debug("{} Headers: {}", prefix, request.getHeaders());
//            if (body.length > 0) {
//                log.debug("{} Body: \n{}", prefix, new String(body, StandardCharsets.UTF_8));
//            }
//        }
//    }
//
//    private void logResponse(int requestNumber, ClientHttpResponse response) throws IOException {
//        if (log.isDebugEnabled()) {
//            String prefix = requestNumber + " < ";
//            log.debug("{} Response: {} {} {}", prefix, response.getStatusCode(), response.getStatusCode().name(), response.getStatusText());
//            log.debug("{} Headers: {}", prefix, response.getHeaders());
//            String body = StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8);
//            if (body.length() > 0) {
//                log.debug("{} Body: \n{}", prefix, body);
//            }
//        }
//    }
//}
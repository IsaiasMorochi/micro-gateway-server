package com.isaias.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class ExampleGlobalFilter implements GlobalFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(ExampleGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        LOGGER.info("Ejecautando filter PRE");
        // TODO : Todo el codigo aqui es el PRE filter
        

        // creamos un objeto reactivo MONO
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            // TODO : Todo el codigo aqui es el POST filter
            LOGGER.info("Ejecutando filter POST");
            exchange.getResponse().getCookies().add("color", ResponseCookie.from("color", "black").build());
            exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
        }));
    }
}

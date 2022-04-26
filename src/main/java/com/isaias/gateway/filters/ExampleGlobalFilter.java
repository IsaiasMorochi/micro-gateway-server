package com.isaias.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
//import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class ExampleGlobalFilter implements GlobalFilter, Ordered {

    private final Logger LOGGER = LoggerFactory.getLogger(ExampleGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        LOGGER.info("Ejecautando filter PRE");
        // TODO : Todo el codigo aqui es el PRE filter
        exchange.getRequest().mutate().headers(httpHeaders -> httpHeaders.add("token", "123456"));

        // creamos un objeto reactivo MONO
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            // TODO : Todo el codigo aqui es el POST filter
            LOGGER.info("Ejecutando filter POST");

            Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("token"))
                    .ifPresent(valor -> {
                exchange.getResponse().getHeaders().add("token", valor);
            });

            exchange.getResponse().getCookies().add("color", ResponseCookie.from("color", "black").build());
            //exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
        }));
    }

    @Override
    public int getOrder() {
        return -1;
    }

}

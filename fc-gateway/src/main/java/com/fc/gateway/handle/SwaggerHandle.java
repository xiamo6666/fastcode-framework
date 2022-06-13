package com.fc.gateway.handle;

import org.springdoc.core.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/10 15:42
 */
@RestController
public class SwaggerHandle {
    @Value("${spring.application.name}")
    private String serviceName;

    private static final Map<String, String> CACHE = new ConcurrentHashMap<>(1);

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping(Constants.DEFAULT_API_DOCS_URL + "/{modelName}")
    public Mono<?> api(@PathVariable String modelName) {

        if (!CACHE.containsKey(serviceName)) {
            List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
            if (CollectionUtils.isEmpty(instances)) {
                return Mono.empty();
            }
            ServiceInstance serviceInstance = instances.get(0);
            CACHE.put(serviceName, serviceInstance.getUri().toString());
        }
        List<ServiceInstance> instances = discoveryClient.getInstances(modelName.toUpperCase(Locale.ROOT));
        if (CollectionUtils.isEmpty(instances)) {
            return Mono.empty();
        }
        ServiceInstance serviceInstance = instances.get(0);
        return WebClient.create(serviceInstance.getUri() + Constants.DEFAULT_API_DOCS_URL)
                .get()
                .retrieve()
                .bodyToMono(HashMap.class)
                .filter(map -> {
                    //设置为使用gateway做 try it out server
                    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
                    LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
                    linkedHashMap.put("url", CACHE.get(serviceName) + "/" + modelName);
                    arrayList.add(linkedHashMap);
                    map.put("servers", arrayList);
                    return true;
                });
    }
}

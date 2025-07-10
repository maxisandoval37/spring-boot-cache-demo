# Pasos para implementar caché en Spring Boot

### 1. Agregar dependencias en el POM:
```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-cache</artifactId>
        </dependency>
        <dependency>
            <groupId>com.github.ben-manes.caffeine</groupId>
            <artifactId>caffeine</artifactId>
        </dependency>
```

### 2. Habilitar caché en la aplicación:
Agregar la anotación **@EnableCaching**

```java
@SpringBootApplication
@EnableCaching
public class CacheDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(CacheDemoApplication.class, args);
    }
}
```

### 3. Anotaciones en los los métodos:
 • @Cacheable
 
 • @CachePut
 
 • @CacheEvict

## Recursos útiles
[Documentación oficial](https://docs.spring.io/spring-framework/reference/integration/cache.html)

[Swagger](http://localhost:8080/swagger-ui/index.html)

<hr>

## Información Adicional
Para cualquier información adicional o consultas: <maxisandoval98@gmail.com>

**¡Muchas gracias!**

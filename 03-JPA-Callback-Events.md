# Callback events in JPA lifecycle

## PrePersist

```java
@PrePersist
public void prePersist() {
    this.createDate = LocalDateTime.now();
}
```

## PreUpdate

```java
@PreUpdate
public void preUpdate() {
    this.lastUpdateDate = LocalDateTime.now();
}
```
> No more than one occurrence of the same callback annotation can exist within a class

```shell
jakarta.persistence.PersistenceException: You can only annotate one callback method with jakarta.persistence.PreUpdate in bean class: com.code.truck.ecommerce.model.Order

	at org.hibernate.jpa.event.internal.CallbackDefinitionResolverLegacyImpl.resolveEntityCallbacks(CallbackDefinitionResolverLegacyImpl.java:85)
	at org.hibernate.boot.model.internal.EntityBinder.bindCallbacks(EntityBinder.java:1111)
	at org.hibernate.boot.model.internal.EntityBinder.bindEntityClass(EntityBinder.java:247)
        ...
```

> Two callback events can be applied at the same time

```java
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class Order {
    @Id
    private Integer id;
    
    //... code ommited
    
    @PrePersist
    @PreUpdate
    public void calculateTotal() {
        if (orderItems != null) {
            total = this.orderItems.stream()
                    .map(
                            i -> i.getProductPrice()
                                    .multiply(BigDecimal.valueOf(i.getQuantity()))
                    )
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }
}
```
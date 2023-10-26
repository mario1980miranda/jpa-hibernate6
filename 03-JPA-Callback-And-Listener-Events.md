# Callback and Listener events in JPA lifecycle

## Callbacks

Callbacks involves operations strongly linked to specific entities.

> PrePersist

```java
@PrePersist
public void prePersist() {
    this.createDate = LocalDateTime.now();
}
```

> PreUpdate

```java
@PreUpdate
public void preUpdate() {
    this.lastUpdateDate = LocalDateTime.now();
}
```
> **Attention:** No more than one occurrence of the same callback annotation can exist within a class

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
    
    //... code omitted for brevity
    
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

> Other callback events :

```java
import jakarta.persistence.PostPersist;

    //... code omitted for brevity

    @PostPersist
    public void postPersist(){
        System.out.println("After insert");
    }
    
    @PostUpdate
    public void postUpdate(){
        System.out.println("After update");
    }
    
    @PreRemove
    public void preRemove(){
        System.out.println("Before delete command");
    }
    
    @PostRemove
    public void postRemove(){
        System.out.println("After remove");
    }
    
    @PostLoad
    public void postLoad(){
        System.out.println("After load from database");
    }
```

## Listener

Listeners are more used in operations between entities.

It must be annotated within the class with **@EntityListeners({ Listener.class })**

```java
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners( { InvoiceListener.class } ) // it accepts a list of listeners
@Entity
@Table(name = "tb_order")
public class Order {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
```

```java
import com.code.truck.ecommerce.model.Order;
import com.code.truck.ecommerce.services.InvoiceService;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class InvoiceListener {

    private final InvoiceService invoiceService = new InvoiceService();

    @PrePersist
    @PreUpdate
    public void generate(Order order) {
        if (order.isPaid() && order.getInvoice() == null) {
            invoiceService.generate(order);
        }
    }
}
```
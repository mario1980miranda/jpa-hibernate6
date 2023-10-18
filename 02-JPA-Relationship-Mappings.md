# JPA Relationship Mapping

## Types of relationship

* Many to one
* One to many
* One to one
* Many to many

### Many to one

```mermaid
erDiagram
    TB_ORDER }o--|| TB_CLIENT : has
    TB_ORDER {
        integer id PK
        datetime creation_date
        integer client_id FK
    }
    TB_CLIENT {
        integer id PK
        varchar(100) name
    }
```

In java

```java
import com.code.truck.ecommerce.model.Client;
import jakarta.persistence.*;

@Entity
@Table(name = "TB_ORDER")
public class Order {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
```

### One to many

```java
import com.code.truck.ecommerce.model.Client;
import com.code.truck.ecommerce.model.Order;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "TB_CLIENT")
public class Client {
    @Id
    private Integer id;

    @OneToMany(mappedBy = "client")
    private List<Order> orders;
}
```

#### Auto relationship

```java
import jakarta.persistence.*;

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "parent_category_id") // JPA owning
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory") // JPA non-owning
    private List<Category> categories;
}
```

```mermaid
erDiagram
    TB_CATEGORY }o--o| TB_CATEGORY : has
    TB_CATEGORY {
        integer id PK
        varchar name
        integer parent_category_id FK
    }
```

### One to one

```mermaid
---
title: One to one
---
erDiagram
    TB_ORDER ||--|| TB_INVOICE : has
    "TB_ORDER is the owner of the data for JPA"
    TB_ORDER {
        integer id PK
        datetime creation_date
        integer invoice_id FK
    }
    TB_INVOICE {
        integer id PK
        text xml
        datetime issue_date
    }
```

In java

```java
import com.code.truck.ecommerce.model.Invoice;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_ORDER")
public class Order {
    @Id
    private Integer id;

    @OneToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
}
```

```java
import com.code.truck.ecommerce.model.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_INVOICE")
public class Invoice {
    @Id
    private Integer id;
    private String xml;

    @OneToOne(mappedBy = "invoice")
    private Order order;
}
```
#### One to one with JoinTable

```mermaid
erDiagram
    TB_INVOICE_ORDER ||--|| TB_INVOICE : contains
    TB_INVOICE_ORDER ||--|| TB_ORDER : contains
    TB_INVOICE {
        integer id PK
        varchar xml
    }
    TB_ORDER {
        integer id PK
        timestamp create_date
    }
    TB_INVOICE_ORDER {
        integer order_id FK "unique"
        integer invoice_id FK "unique"
    }
```

```java
import jakarta.persistence.Id;

public class Order {
    @Id
    private Integer id;

    @OneToOne(mappedBy = "order")
    private Invoice invoice;
}
```

```java
import jakarta.persistence.Id;

public class Invoice {
    @Id
    private Integer id;

    @OneToOne
    @JoinTable(name = "tb_invoice_order",
            joinColumns = @JoinColumn(name = "invoice_id", unique = true),
            inverseJoinColumns = @JoinColumn(name = "order_id", unique = true))
    private Order order;
}
```

### Many to many

```mermaid
erDiagram
    TB_PRODUCT ||--|{ TB_PRODUCT_CATEGORY : contains
    TB_CATEGORY ||--|{ TB_PRODUCT_CATEGORY : contains
    TB_PRODUCT {
        integer id PK
        varchar name
    }
    TB_CATEGORY {
        integer product_id FK
        integer category_id FK
    }
    TB_PRODUCT_CATEGORY {
        integer id PK
        varchar name
    }
```

In java

```java
import com.code.truck.ecommerce.model.Category;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "TB_PRODUCT")
public class Product {
    @Id
    private Integer id;

    @ManyToMany
    @JoinTable(name = "TB_PRODUCT_CATEGORY",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;
}
```

```java
import com.code.truck.ecommerce.model.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "TB_CATEGORY")
public class Category {
    @Id
    private Integer id;

    @ManyToMany(mappedBy = "categories")
    private List<Product> products;
}
```

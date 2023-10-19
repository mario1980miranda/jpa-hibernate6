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

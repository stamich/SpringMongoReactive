package io.codeswarm.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "account")
@Data
@AllArgsConstructor
public class Account {

    @Indexed
    private String ownerEmail;
    private BigDecimal balance;
    private Boolean active;
}

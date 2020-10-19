package io.codeswarm.persistence.model;

import lombok.Data;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.SortedSet;
import java.util.TreeSet;

@Document(collection = "user")
@TypeAlias("client")
@Data
public class Client {

    public enum ClientType {
        INDIVIDUAL,
        SMALL_BUSINESS,
        LIMITED_PARTNERSHIP,
        JOINT_STOCK_COMPANY,
        CORPORATE
    }

    @Indexed
    private String firstName;

    private String lastName;

    private String email;

    private SortedSet<ClientType> clientTypes = new TreeSet<>();
}

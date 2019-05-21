package org.yaodong.telegram.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@DynamoDBTable(tableName = "Profile")
public class Profile {

    private Integer id;

    private String userName;

    private Boolean validated;

    private Boolean activated;

}

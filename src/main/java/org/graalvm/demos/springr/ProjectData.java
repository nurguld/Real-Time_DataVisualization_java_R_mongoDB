package org.graalvm.demos.springr;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "pro1_data")
public class ProjectData {

    @Field("Col-17")
    private Double col17;


    public Double getCol17() { return col17; }
}
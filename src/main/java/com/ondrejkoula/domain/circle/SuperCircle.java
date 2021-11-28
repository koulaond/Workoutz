package com.ondrejkoula.domain.circle;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Adjusted circle training where each set has exercises specified.
 */
@Data
@Document
public class SuperCircle extends Circle {

    private List<CircleSet> definedSets;

}

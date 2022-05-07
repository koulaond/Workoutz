package com.ondrejkoula.exception;

import java.util.Map;

public class CascadeDependenciesException extends Exception {

    private Long parentEntityId;

    private Map<String, Integer> typesAndOccurrences;

}

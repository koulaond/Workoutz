package com.ondrejkoula.service.dependencies;

import com.ondrejkoula.dto.Dependencies;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NoDependencyService extends DependencyService {
   
    @Override
    public void doCollect(Long parentEntityId, List<Dependencies> dependenciesList) {
        
    }
}

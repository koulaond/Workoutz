package com.ondrejkoula.service.exercise;

import com.ondrejkoula.domain.Status;
import com.ondrejkoula.domain.exercise.weights.WeightSingleSet;
import com.ondrejkoula.domain.exercise.weights.Weights;
import com.ondrejkoula.exception.InconsistentPositionsException;
import com.ondrejkoula.repository.jpa.exercise.ExercisePrescriptionRepository;
import com.ondrejkoula.repository.jpa.exercise.WeightsRepository;
import com.ondrejkoula.service.dependencies.exercise.ExerciseDependencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Comparator.comparing;

@Slf4j
@Component
public class WeightsService extends ExercisePrescriptionOwnerService<Weights> {

    @Autowired
    public WeightsService(WeightsRepository repository, 
                          ExercisePrescriptionRepository exercisePrescriptionRepository, 
                          ExerciseDependencyService dependencyService) {
        super(repository, dependencyService, exercisePrescriptionRepository);
    }

    public Weights markAsReady(Long id) {
        Weights weights = findById(id);
        Status status = Status.valueOf(weights.getStatus());

        if (Status.READY.equals(status)) {
            log.info("Sets and repetitions with ID {} are already set as ready", id);
            return weights;
        }

        weights.setStatus(Status.READY.name());
        return create(weights);
    }

    public Weights updateSetsInWeightsExercise(Long weightsId, List<WeightSingleSet> sets) {
        sortSetsByPosition(sets);
        validateSetsListConsistency(sets);

        Weights found = findById(weightsId);
        found.setSets(sets);

        dataValidator.validateAllMandatoryDataPresent(found);

        return repository.save(found);
    }

    private void sortSetsByPosition(List<WeightSingleSet> sets) {
        sets.sort(comparing(WeightSingleSet::getPosition));
    }

    private void validateSetsListConsistency(List<WeightSingleSet> sets) {
        if (sets.get(0).getPosition() != 1) {
            throw new InconsistentPositionsException("Sets do not start with position 1.");
        }
        for (int i = 0; i < sets.size() - 1; i++) {
            if (sets.get(i).getPosition() != sets.get(i + 1).getPosition() - 1) {
                throw new InconsistentPositionsException("Sets positions are inconsistent.");
            }
        }
    }
}

package com.ondrejkoula.service.exercise;

import com.ondrejkoula.domain.Status;
import com.ondrejkoula.domain.exercise.weights.SingleSet;
import com.ondrejkoula.domain.exercise.weights.Weights;
import com.ondrejkoula.exception.InconsistentPositionsException;
import com.ondrejkoula.repository.jpa.exercise.WeightsRepository;
import com.ondrejkoula.service.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Comparator.comparing;

@Slf4j
@Component
public class WeightsService extends GenericService<Weights> {

    @Autowired
    public WeightsService(WeightsRepository repository) {
        super(repository);
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

    public Weights updateSetsInWeightsExercise(Long weightsId, List<SingleSet> sets) {
        sortSetsByPosition(sets);
        validateSetsListConsistency(sets);

        Weights found = findById(weightsId);
        found.setSets(sets);

        dataValidator.validateAllMandatoryDataPresent(found);

        return repository.save(found);
    }

    private void sortSetsByPosition(List<SingleSet> sets) {
        sets.sort(comparing(SingleSet::getPosition));
    }

    private void validateSetsListConsistency(List<SingleSet> sets) {
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

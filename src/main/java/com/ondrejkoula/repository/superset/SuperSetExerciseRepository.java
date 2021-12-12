package com.ondrejkoula.repository.superset;

import com.ondrejkoula.domain.superset.SuperSetExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SuperSetExerciseRepository extends JpaRepository<SuperSetExercise, Long> {

    List<SuperSetExercise> findSuperSetExercisesByParentIdOrderByPosition(Long superSetId);

    @Query("select s from SuperSetExercise s where s.parent.id = ?1 order by s.position")
    List<SuperSetExercise> findBySuperSetId(Long parentId);


    @Query("select s from SuperSetExercise s where s.parent.id = ?1 and s.position >= ?2 order by s.position")
    List<SuperSetExercise> findByPositionGreaterThan(Long parentId, Integer position);

    @Query("select count(s) from SuperSetExercise s where s.parent.id = ?1")
    long countBySuperSetId(Long parentId);

    @Query("select s from SuperSetExercise s where s.parent.id = ?1 and s.position between ?2 and ?3")
    List<SuperSetExercise> findByPositionBetween(Long parentId, Integer positionFrom, Integer positionTo);

}

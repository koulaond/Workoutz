package com.ondrejkoula.domain.superset;

import lombok.*;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "super_sets")
public class SuperSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @Column(name = "status")
    protected String status;

    @Column(name = "series_count")
    private Integer seriesCount;

    @Column(name = "series_count_goal")
    private Integer seriesCountGoal;

    @OneToMany(mappedBy = "superSet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<SuperSetExercise> seriesContent;

    public void setSeriesContent(List<SuperSetExercise> newContent) {
        this.seriesContent.clear();
        if (!CollectionUtils.isEmpty(newContent)) {
            this.seriesContent.addAll(newContent);
        }
    }

}

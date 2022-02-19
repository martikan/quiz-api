package com.martikan.quizapi.domain.assignment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.martikan.quizapi.domain.Group;
import com.martikan.quizapi.domain.question.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entity for `assignments` table.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "assignments")
public class Assignment implements Serializable {

    private static final long serialVersionUID = -190649689366896804L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    @Column
    private String title;

    @Size(max = 4000)
    @Column
    private String description;

    @Column
    private LocalDateTime createdAt;

    @CreatedBy
    @NotNull
    private Long createdBy;

    @Column
    private LocalDateTime validBefore;

    @Column(name = "is_closed")
    private boolean closed = false;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "assignment")
    private Set<Question> questions = new HashSet<>();

    @JsonIgnore
    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "assignment")
    private SavedAssignment savedAssignment;

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "assignments")
    private Set<Group> groups = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

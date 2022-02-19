package com.martikan.quizapi.domain.assignment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entity for `saved_assignments` table.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "saved_assignments")
public class SavedAssignment implements Serializable {

    private static final long serialVersionUID = -1918587593513261433L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    @Column
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SavedAssignment that = (SavedAssignment) o;
        return assignment.equals(that.assignment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assignment);
    }
}

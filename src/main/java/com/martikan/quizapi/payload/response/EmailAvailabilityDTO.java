package com.martikan.quizapi.payload.response;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO to send email availability.
 */
@Builder
@Data
@EqualsAndHashCode
public class EmailAvailabilityDTO implements Serializable {

    private static final long serialVersionUID = 9014053931316127247L;

    @NotNull
    private boolean emailAvailable;

}

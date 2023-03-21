package study.sharedcalendar.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

	private int id;

	private int userId;

	private int connecteeId;

	@NotBlank
	private String content;

	@NotNull
	private LocalDate date;

	@NotNull
	private boolean activate;

}

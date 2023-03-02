package study.sharedcalendar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Connection {
	private int id;
	private int connectorId;
	private int connecteeId;
	boolean activate;

}

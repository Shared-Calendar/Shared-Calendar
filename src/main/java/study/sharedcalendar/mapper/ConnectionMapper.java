package study.sharedcalendar.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import study.sharedcalendar.dto.Connection;

@Mapper
public interface ConnectionMapper {
	String getInviteUrlCode(int id);

	Connection getConnection(@Param("connectorId") int connectorId,
		@Param("connecteeId") int connecteeId);

	void modifyActivate(int id, boolean status);

	void createConnection(@Param("connectorId") int connectorId,
		@Param("connecteeId") int connecteeId);
}
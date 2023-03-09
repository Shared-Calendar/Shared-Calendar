package study.sharedcalendar.mapper;

import java.util.List;

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

	int countConnection(int userId);

	List<String> findConnection(@Param("userId") int userId, @Param("lastViewId") int lastViewId);

	List<String> findRecentConnection(int userId);
}

package ${groupId}.ws.persistence;

import org.springframework.data.jpa.repository.JpaRepository;


import ${groupId}.ws.ui.model.response.RestEntity;

public interface GenericEntityRepository
extends JpaRepository<RestEntity, String> {

	
}

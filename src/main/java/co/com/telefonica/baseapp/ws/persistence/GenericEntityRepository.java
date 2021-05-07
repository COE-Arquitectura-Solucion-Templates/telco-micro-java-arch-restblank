package co.com.telefonica.baseapp.ws.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.telefonica.baseapp.ws.ui.model.response.RestEntity;

public interface GenericEntityRepository
extends JpaRepository<RestEntity, String> {

	
}

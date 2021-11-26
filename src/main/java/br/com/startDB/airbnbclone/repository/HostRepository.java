package br.com.startDB.airbnbclone.repository;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import br.com.startDB.airbnbclone.model.Host;

public interface HostRepository extends CrudRepository<Host, UUID> {
	
	Host findHostByEmailIgnoreCase (String email);
}

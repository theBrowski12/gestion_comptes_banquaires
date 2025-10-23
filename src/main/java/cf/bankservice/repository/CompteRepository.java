package cf.bankservice.repository;

import cf.bankservice.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.JavaBean;

public interface CompteRepository extends JpaRepository<Compte, Integer> {

}

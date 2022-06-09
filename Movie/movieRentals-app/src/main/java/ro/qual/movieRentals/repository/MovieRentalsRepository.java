package ro.qual.movieRentals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ro.qual.movieRentals.model.BaseEntity;

import java.io.Serializable;

@NoRepositoryBean
public interface MovieRentalsRepository <T extends BaseEntity<ID>, ID extends Serializable>
        extends JpaRepository<T, ID> {
}

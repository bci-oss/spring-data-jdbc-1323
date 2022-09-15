package com.example.demo;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PersonRepository extends CrudRepository<Person, UUID> {


    @Query("""
			select p.lastname  from person p
			join (values :combinations ) as t (input_lastname, input_firstname)
			ON p.lastname = input_lastname AND p.firstname = input_firstname
        """
    )
    List<String> findLastnameByComplexBindParameter(@Param("combinations") List<String[]> combinations);

}
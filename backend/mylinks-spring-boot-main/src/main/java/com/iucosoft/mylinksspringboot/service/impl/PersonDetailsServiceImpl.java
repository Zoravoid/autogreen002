package com.iucosoft.mylinksspringboot.service.impl;

import com.iucosoft.mylinksspringboot.entities.PersonDetails;
import com.iucosoft.mylinksspringboot.repositories.PersonDetailsRepository;
import com.iucosoft.mylinksspringboot.service.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonDetailsServiceImpl extends AbstractServiceImpl<PersonDetails, Long> implements PersonDetailsService {

    private final PersonDetailsRepository personDetailsRepository;

    @Autowired
    public PersonDetailsServiceImpl(PersonDetailsRepository personDetailsRepository) {
        this.personDetailsRepository = personDetailsRepository;
    }

    @Override
    protected JpaRepository<PersonDetails, Long> getRepository() {
        return personDetailsRepository;
    }
}

package com.nhnacademy.spring.jpa_security.entity;

import com.nhnacademy.spring.jpa_security.config.RootConfig;
import com.nhnacademy.spring.jpa_security.config.WebConfig;
import com.nhnacademy.spring.jpa_security.repository.resident.ResidentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class ResidentTest {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ResidentRepository residentRepository;

    @Test
    void test() {
//        Resident resident = entityManager.find(Resident.class, 1L);

        entityManager.getEntityGraphs(Resident.class).stream().forEach(System.out::println);
    }

    @Test
    void find() {
        List<Resident> residents = residentRepository.findAll();

        for (Resident resident : residents) {
            System.out.println(resident.getName());
        }
    }


}
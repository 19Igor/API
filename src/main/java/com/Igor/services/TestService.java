package com.Igor.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @PersistenceContext
    private EntityManager em;
    public int a = 6;
    public String b = "string";
}

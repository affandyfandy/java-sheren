package com.example.lecture_5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lecture_5.model.Contact;

@Repository
public interface ContactRepository  extends JpaRepository<Contact, String> {
}


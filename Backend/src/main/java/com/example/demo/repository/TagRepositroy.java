package com.example.demo.repository;

import com.example.demo.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepositroy extends JpaRepository<Tag,Long> {
}

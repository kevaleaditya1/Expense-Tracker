
package com.example.expensetracker.repository;

import com.example.expensetracker.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<User, String> {

    boolean existsByEmail(String email);

    User findByEmail(String email);
}

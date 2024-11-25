package br.com.sysaba.modules.stripe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StripeRepository extends JpaRepository<StripeCustomers, UUID> {
    StripeCustomers findByEmail(String email);
}

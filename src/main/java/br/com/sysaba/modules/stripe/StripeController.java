package br.com.sysaba.modules.stripe;

import br.com.sysaba.core.util.MapperUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth/stripe")
public class StripeController {

    private final StripeRepository stripeRepository;

    public StripeController(StripeRepository stripeRepository) {
        this.stripeRepository = stripeRepository;
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody StripeSubscriptionDTO stripeSubscriptionDTO) {
        StripeCustomers stripeCustomers = MapperUtil.converte(stripeSubscriptionDTO, StripeCustomers.class);
        stripeRepository.save(stripeCustomers);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{email}")
    public ResponseEntity<StripeCustomers> getUserStripeSubscriptionId(@PathVariable("email") String email) {
        StripeCustomers stripeCustomers = stripeRepository.findByEmail(email);
        return ResponseEntity.ok(stripeCustomers);
    }
}

package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 9/24/17.
 */
@Component
@Slf4j
public class Bootstrap implements CommandLineRunner{

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadCategories();
        loadCustomers();

    }

    private void loadCustomers() {

        Customer bob = Customer.builder().firstName("Robert").lastName("Martin").build();
        Customer jhon = Customer.builder().firstName("Jhon").lastName("Thomson").build();
        Customer endios = Customer.builder().firstName("Endios").lastName("Deloro").build();

        customerRepository.save(bob);
        customerRepository.save(jhon);
        customerRepository.save(endios);

        log.info(String.format("Saved %d customers", customerRepository.count()));
    }

    private void loadCategories() {

        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        log.info(String.format("Saved %d categories", categoryRepository.count()));
    }
}

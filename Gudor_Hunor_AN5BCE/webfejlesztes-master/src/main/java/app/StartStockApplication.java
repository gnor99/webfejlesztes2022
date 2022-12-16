package app;

import app.data.Stock;
import app.data.StockRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StartStockApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(StartStockApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(StockRepository beverageRepository)
    {
        return args ->
        {
            beverageRepository.save(new Stock("ItemA", "NameA", 1, false));
            beverageRepository.save(new Stock("ItemB", "NameB", 2, false));
            beverageRepository.save(new Stock("ItemC", "NameC", 3, true));
        };
    }
}

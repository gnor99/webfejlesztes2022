package app.rest;

import app.data.Stock;
import app.data.StockRepository;
import app.error.NotFoundException;
import app.error.PatchException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class StockController {
    @Autowired
    private StockRepository repository;

    @GetMapping("/stocks")
    List<Stock> findAll()
    {
        return repository.findAll();
    }

    @PostMapping("/stocks")
    @ResponseStatus(HttpStatus.CREATED)
    Stock newStock(@RequestBody StockRequest request)
    {
        Stock stock = new Stock();
        stock.setIsMetal(request.getIsMetal());
        stock.setItem(request.getItem());
        stock.setMass(request.getMass());
        stock.setName(request.getName());

        return repository.save(stock);
    }

    @GetMapping("/stocks/{id}")
    Stock findOne(@PathVariable Long id)
    {
        return repository
            .findById(id)
            .orElseThrow(() -> new NotFoundException(id));
    }

    @PutMapping("/stocks/{id}")
    Stock saveOrUpdate(@RequestBody Stock newStock, @PathVariable Long id)
    {
        return repository.findById(id)
            .map(stock -> {
                stock.setId(id);
                stock.setItem(newStock.getItem());
                stock.setName(newStock.getName());
                stock.setMass(newStock.getMass());
                stock.setIsMetal(newStock.getIsMetal());

                return repository.save(stock);
            })
            .orElseGet(() -> {
                newStock.setId(id);
                return repository.save(newStock);
            });
    }

    @PatchMapping("/stocks/{id}")
    Stock patch(@RequestBody Map<String, String> update, @PathVariable Long id)
    {
        return repository.findById(id)
            .map(stock -> {
                HashSet<String> fieldSet = new HashSet<>();
                fieldSet.add("item");
                fieldSet.add("name");
                fieldSet.add("mass");
                fieldSet.add("IsMetal");

                for (String field : update.keySet()) {
                    if (!fieldSet.contains(field)) {
                        throw new PatchException(field);
                    }
                }

                String item = update.get("item");
                if (!StringUtils.isEmpty(item)) {
                    stock.setItem(item);
                }

                String name = update.get("name");
                if (!StringUtils.isEmpty(name)) {
                    stock.setName(name);
                }

                String mass = update.get("Mass");
                if (!StringUtils.isEmpty(mass)) {
                    stock.setMass(Integer.getInteger(mass));
                }

                String IsMetal = update.get("IsMetal");
                if (!StringUtils.isEmpty(IsMetal)) {
                    stock.setIsMetal(Boolean.valueOf(IsMetal));
                }

                return repository.save(stock);
            })
            .orElseThrow(() -> new NotFoundException(id));
    }

    @DeleteMapping("/stocks/{id}")
    void deleteStock(@PathVariable Long id)
    {
        if (!repository.existsById(id)) {
            return;
        }

        repository.deleteById(id);
    }
}

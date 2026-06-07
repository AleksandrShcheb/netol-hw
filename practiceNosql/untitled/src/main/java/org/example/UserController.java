package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository repo;

    @GetMapping
    public List<User> getAll() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<User> getOne(@PathVariable String id) {
        User user = repo.findById(id).orElse(null);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody User user) { return repo.save(user); }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable String id, @Valid @RequestBody User user) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        user.setId(id);
        return ResponseEntity.ok(repo.save(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<User> search(@RequestParam String name) { return repo.findByName(name); }
}
package two.db.connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import two.db.connection.sql.entity.user.User;
import two.db.connection.sql.repository.user.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test/")
public class mainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> getAlldata() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping
    public ResponseEntity<User> createData(@RequestBody User user) {
        User user1 = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getEachdata(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        return ResponseEntity.ok(user);

    }
}

package hr.katarina.library.users;

import hr.katarina.library.users.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return userService.get(id).orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @Valid @RequestBody User user ) {
        return userService.update(id, user).orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.userService.delete(id).orElseThrow(() -> new UserNotFoundException("User not found."));
    }

}

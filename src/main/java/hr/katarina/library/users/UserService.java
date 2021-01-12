package hr.katarina.library.users;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> get(Long id) {
        return userRepository.findById(id);
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public Optional<User> update(Long id, User userData) {
        Optional<User> opUser = userRepository.findById(id);
        if (opUser.isPresent()) {
            User user = opUser.get();
            user.setName(userData.getName());
            user.setSurname(userData.getSurname());
            user.setDateOfBirth(userData.getDateOfBirth());
        }
        return opUser;
    }

    public Optional<User> delete(Long id) {
        Optional<User> opUser = userRepository.findById(id);
        if (opUser.isPresent()) {
            userRepository.delete(opUser.get());
        }
        return opUser;
    }
}

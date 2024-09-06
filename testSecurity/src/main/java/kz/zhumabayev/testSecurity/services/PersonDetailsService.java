package kz.zhumabayev.testSecurity.services;

import kz.zhumabayev.testSecurity.models.Person;
import kz.zhumabayev.testSecurity.repositories.PeopleRepository;
import kz.zhumabayev.testSecurity.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByUsername(username);
        if (person.isEmpty()) {
            System.out.println("Takogo net Dalbayop");
            throw new UsernameNotFoundException("User not found");
        }

        System.out.println("Uspeh broski gandoski");
        return new PersonDetails(person.get());
    }
}

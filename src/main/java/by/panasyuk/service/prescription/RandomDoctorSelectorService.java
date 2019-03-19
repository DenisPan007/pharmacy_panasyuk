package by.panasyuk.service.prescription;

import by.panasyuk.domain.User;
import by.panasyuk.repository.Repository;
import by.panasyuk.repository.RepositoryFactory;
import by.panasyuk.repository.exception.RepositoryException;
import by.panasyuk.repository.impl.JdbcRepositoryFactory;
import by.panasyuk.repository.impl.UserRepository;
import by.panasyuk.repository.specification.Specification;
import by.panasyuk.repository.specification.user.GetByRole;
import by.panasyuk.service.exception.ServiceException;

import java.util.List;

public class RandomDoctorSelectorService implements DoctorSelectorForPrescriptionService {
    private RepositoryFactory repositoryFactory = JdbcRepositoryFactory.getInstance();
    private Repository<User, Integer> repository = repositoryFactory.getRepository(UserRepository::new);
    @Override
    public User select() throws ServiceException {
        Specification<User> specification = new GetByRole();
        try {
            User doctor = new User();
            doctor.setRole("doctor");
            List<User> doctorList = repository.getQuery(doctor,specification);
            int randomIndex = (int)(Math.random()* (doctorList.size()-1));
            return doctorList.get(randomIndex);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}

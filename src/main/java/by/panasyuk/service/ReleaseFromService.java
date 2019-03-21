package by.panasyuk.service;

import by.panasyuk.domain.ReleaseForm;
import by.panasyuk.repository.Repository;
import by.panasyuk.repository.RepositoryFactory;
import by.panasyuk.repository.exception.RepositoryException;
import by.panasyuk.repository.impl.JdbcRepositoryFactory;
import by.panasyuk.repository.impl.ReleaseFormRepository;
import by.panasyuk.repository.specification.Specification;
import by.panasyuk.repository.specification.release_form.GetAllReleaseForms;
import by.panasyuk.repository.specification.release_form.GetByDescription;
import by.panasyuk.service.exception.ServiceException;

import java.util.List;

public class ReleaseFromService {
    private RepositoryFactory repositoryFactory = JdbcRepositoryFactory.getInstance();
    private Repository<ReleaseForm, Integer> releaseFormRepository = repositoryFactory.getRepository(ReleaseFormRepository::new);

    public ReleaseForm getByDescription(String description) throws ServiceException {
        Specification<ReleaseForm> specification = new GetByDescription();
        try {
            ReleaseForm releaseForm = new ReleaseForm();
            releaseForm.setDescription(description);
            List<ReleaseForm> releaseFormList = releaseFormRepository.getQuery(releaseForm, specification);
            if (releaseFormList.isEmpty()) {
                return null;
            }
            return releaseFormList.get(0);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<ReleaseForm> getAllReleaseForms() throws ServiceException {
        Specification<ReleaseForm> specification = new GetAllReleaseForms();
        try {
            return releaseFormRepository.getQuery(new ReleaseForm(), specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}


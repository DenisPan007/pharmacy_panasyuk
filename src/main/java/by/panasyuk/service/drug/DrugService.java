package by.panasyuk.service.drug;

import by.panasyuk.repository.Repository;
import by.panasyuk.repository.RepositoryFactory;
import by.panasyuk.repository.impl.DrugRepository;
import by.panasyuk.repository.impl.JdbcRepositoryFactory;
import by.panasyuk.domain.Drug;

public abstract class DrugService {
    protected RepositoryFactory repositoryFactory = JdbcRepositoryFactory.getInstance();
    protected Repository<Drug, Integer> drugRepository = repositoryFactory.getRepository(DrugRepository::new);

    public DrugService() {
    }
}

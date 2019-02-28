package by.panasyuk.service.drug;

import by.panasyuk.dao.Repository;
import by.panasyuk.dao.RepositoryFactory;
import by.panasyuk.dao.impl.DrugRepository;
import by.panasyuk.dao.impl.JdbcRepositoryFactory;
import by.panasyuk.domain.Drug;

public abstract class DrugService {
    protected RepositoryFactory repositoryFactory = JdbcRepositoryFactory.getInstance();
    protected Repository<Drug, Integer> drugRepository = repositoryFactory.getRepository(DrugRepository::new);

    public DrugService() {
    }
}

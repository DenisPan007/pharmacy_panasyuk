package by.panasyuk.service.drug;

import by.panasyuk.domain.Manufacturer;
import by.panasyuk.domain.ReleaseForm;
import by.panasyuk.repository.Repository;
import by.panasyuk.repository.RepositoryFactory;
import by.panasyuk.repository.impl.DrugRepository;
import by.panasyuk.repository.impl.JdbcRepositoryFactory;
import by.panasyuk.domain.Drug;
import by.panasyuk.repository.impl.ManufacturerRepository;
import by.panasyuk.repository.impl.ReleaseFormRepository;

public abstract class DrugService {
    protected RepositoryFactory repositoryFactory = JdbcRepositoryFactory.getInstance();
    protected Repository<Drug, Integer> drugRepository = repositoryFactory.getRepository(DrugRepository::new);
    protected Repository<Drug, Integer> drugTransactionalRepository = repositoryFactory.getTransactionalRepository(DrugRepository::new);
    protected Repository<Manufacturer, Integer> manufacturerTransactionalRepository = repositoryFactory.getTransactionalRepository(ManufacturerRepository::new);
    protected Repository<ReleaseForm, Integer> releaseFormTransactionalRepository = repositoryFactory.getTransactionalRepository(ReleaseFormRepository::new);

    public DrugService() {
    }
}

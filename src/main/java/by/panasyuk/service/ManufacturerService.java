package by.panasyuk.service;

import by.panasyuk.domain.Manufacturer;
import by.panasyuk.repository.Repository;
import by.panasyuk.repository.RepositoryFactory;
import by.panasyuk.repository.exception.RepositoryException;
import by.panasyuk.repository.impl.JdbcRepositoryFactory;
import by.panasyuk.repository.impl.ManufacturerRepository;
import by.panasyuk.repository.specification.Specification;
import by.panasyuk.repository.specification.manufacturer.GetAllManufacturers;
import by.panasyuk.repository.specification.manufacturer.GetByManufacturerName;
import by.panasyuk.service.exception.ServiceException;

import java.util.List;

public class ManufacturerService {
    private RepositoryFactory repositoryFactory = JdbcRepositoryFactory.getInstance();
    private Repository<Manufacturer, Integer> manufacturerRepository = repositoryFactory.getRepository(ManufacturerRepository::new);

    public Manufacturer getByName(String name) throws ServiceException {
        Specification<Manufacturer> specification = new GetByManufacturerName();
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(name);
        try {
            List<Manufacturer> manufacturerList = manufacturerRepository.getQuery(manufacturer, specification);
            if (manufacturerList.isEmpty()) {
                return null;
            }
            return manufacturerList.get(0);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<Manufacturer> getAllManufacturers() throws ServiceException {
        Specification<Manufacturer> specification = new GetAllManufacturers();
        try {
            return manufacturerRepository.getQuery(new Manufacturer(), specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

}

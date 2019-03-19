package by.panasyuk.service.drug;

import by.panasyuk.domain.Manufacturer;
import by.panasyuk.domain.ReleaseForm;
import by.panasyuk.repository.exception.RepositoryException;
import by.panasyuk.repository.specification.Specification;
import by.panasyuk.repository.specification.drug.GetAllDrugs;
import by.panasyuk.domain.Drug;
import by.panasyuk.repository.specification.drug.GetDrugsByName;
import by.panasyuk.service.exception.ServiceException;

import java.util.List;

public class CrudDrugService extends DrugService {
    public Drug add(String name, boolean isPrescriptionRequired,int price, Manufacturer manufacturer, ReleaseForm releaseForm ) throws ServiceException {
        Drug drug = new Drug(name,isPrescriptionRequired,price,manufacturer, releaseForm);
        try {
            return drugRepository.add(drug);
        } catch (RepositoryException e) {
            throw new ServiceException("Can't add drug", e);
        }

    }
    public void delete(int id) throws ServiceException {
        Drug drug = new Drug();
        drug.setId(id);
        try {
            drugRepository.delete(drug);
        } catch (RepositoryException e) {
            throw new ServiceException("Can't delete drug", e);
        }
    }
    public List<Drug> getAll() throws ServiceException{
        Specification<Drug> spec = new GetAllDrugs();
        try {
            return drugRepository.getQuery(new Drug(), spec);
        } catch (RepositoryException e) {
            throw new ServiceException("Can't get all drugs",e);
        }
    }
    public List<Drug> getByName(String name) throws ServiceException{
        Drug drug = new Drug();
        drug.setName(name);
        Specification<Drug> spec = new GetDrugsByName();
        try {
            return drugRepository.getQuery(drug, spec);
        } catch (RepositoryException e) {
            throw new ServiceException("Can't get drug",e);
        }
    }
}

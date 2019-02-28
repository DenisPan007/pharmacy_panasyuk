package by.panasyuk.service.drug;

import by.panasyuk.dao.exception.RepositoryException;
import by.panasyuk.dao.specification.Specification;
import by.panasyuk.dao.specification.drug.GetAllDrugs;
import by.panasyuk.domain.Drug;
import by.panasyuk.service.exception.ServiceException;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CrudDrugService extends DrugService {
    private static CrudDrugService instance;
    private static Lock lockForSingleTone = new ReentrantLock();

    public static CrudDrugService getInstance() {
        lockForSingleTone.lock();
        try {
            if (instance == null) {
                instance = new CrudDrugService();
            }

        } finally {
            lockForSingleTone.unlock();
        }
        return instance;
    }
    public void delete(int id) throws ServiceException {
        Drug drug = new Drug();
        drug.setId(id);
        try {
            drugRepository.delete(drug);
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to get user DAO. ", e);
        }
    }
    public List<Drug> getAll() throws ServiceException{
        Specification<Drug> spec = new GetAllDrugs();
        try {
            return drugRepository.getQuery(new Drug(), spec);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}

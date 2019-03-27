package by.panasyuk.service.prescription;

import by.panasyuk.domain.Prescription;
import by.panasyuk.repository.Repository;
import by.panasyuk.repository.RepositoryFactory;
import by.panasyuk.repository.exception.RepositoryException;
import by.panasyuk.repository.impl.JdbcRepositoryFactory;
import by.panasyuk.repository.impl.PrescriptionRepository;
import by.panasyuk.repository.specification.Specification;
import by.panasyuk.repository.specification.prescription.GetByDoctorId;
import by.panasyuk.repository.specification.prescription.GetByUserAndDrugId;
import by.panasyuk.repository.specification.prescription.GetPrescriptionById;
import by.panasyuk.service.exception.ServiceException;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class PrescriptionService {
    private RepositoryFactory repositoryFactory = JdbcRepositoryFactory.getInstance();
    private Repository<Prescription, Integer> repository = repositoryFactory.getRepository(PrescriptionRepository::new);

    private Prescription getByUserAndDrugId(int userId, int drugId) throws ServiceException {
        Specification<Prescription> specification = new GetByUserAndDrugId();
        Prescription prescription = new Prescription();
        prescription.setUserId(userId);
        prescription.setDrugId(drugId);
        try {
            List<Prescription> prescriptionList = repository.getQuery(prescription, specification);
            if (prescriptionList.isEmpty()){
                return null;
            }
            return prescriptionList.get(0);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public void givePrescription(int id, String description, Date issueDate, Date validityDate) throws ServiceException {
        Prescription prescription = new Prescription();
        prescription.setId(id);
        try {
            List<Prescription> prescriptionList = repository.getQuery(prescription, new GetPrescriptionById());
            if (prescriptionList.isEmpty()) {
                throw new IllegalArgumentException("can't find by id");
            }
            prescription = prescriptionList.get(0);
            prescription.setDescription(description);
            prescription.setValidityDate(validityDate);
            prescription.setIssueDate(issueDate);
            repository.update(prescription);
        } catch (RepositoryException | IllegalArgumentException e) {
            throw new ServiceException(e);
        }
    }

    public Prescription requestPrescription(int userId, int drugId, int doctorId) throws ServiceException {
        Prescription prescription = getByUserAndDrugId(userId,drugId);
        if(prescription == null){
            prescription = new Prescription();
            prescription.setDrugId(drugId);
            prescription.setUserId(userId);
            prescription.setDoctorId(doctorId);
            try {
                return repository.add(prescription);
            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }
        }
        else {
            try {
                prescription.setIssueDate(null);
                repository.update(prescription);
                return prescription;
            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }
        }

    }

    public boolean isUserQuirePrescription(int userId, int drugId) throws ServiceException {
        Prescription prescription = getByUserAndDrugId(userId,drugId);
        return prescription!=null;
    }

    public boolean isUserHasInvalidPrescription(int userId, int drugId) throws ServiceException {
        Prescription prescription = getByUserAndDrugId(userId,drugId);
        if (prescription==null){
            return false;
        }
        if (prescription.getValidityDate()==null){
            return false;
        }
        long validTime =  prescription.getValidityDate().getTime();
        long currentTime = GregorianCalendar.getInstance().getTimeInMillis();
        return  validTime < currentTime;
    }

    public boolean isUserHasValidPrescription(int userId, int drugId) throws ServiceException {
           Prescription prescription = getByUserAndDrugId(userId,drugId);
            if (prescription==null){
                return false;
            }
            if (prescription.getValidityDate()==null){
                return false;
            }
            long validTime =  prescription.getValidityDate().getTime();
            long currentTime = GregorianCalendar.getInstance().getTimeInMillis();
            return  validTime >= currentTime;
    }

    public List<Prescription> getAllDoctorPrescriptions(int doctorId) throws ServiceException {
        Specification<Prescription> specification = new GetByDoctorId();
        Prescription prescription = new Prescription();
        prescription.setDoctorId(doctorId);
        try {
            return repository.getQuery(prescription, specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}

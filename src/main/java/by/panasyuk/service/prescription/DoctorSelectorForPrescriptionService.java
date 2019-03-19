package by.panasyuk.service.prescription;

import by.panasyuk.domain.User;
import by.panasyuk.service.exception.ServiceException;

public interface DoctorSelectorForPrescriptionService {
    User  select() throws ServiceException;
}

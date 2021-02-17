package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.KindergartenApplicationFormDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KindergartenApplicationFormService {
    @Autowired
    private KindergartenApplicationFormDao kindergartenApplicationFormDao;

    /**
     * Deletes applications to concrete kindergartens by parent application id.
     * Used to cleanup kindergartenApplicationForms table in order to get rid of existing applications.
     * @param applicationId parent application id that contains applications to concrete kindergartens.
     */
    @Transactional
    public void deleteApplicationFormsByApplicationId(Long applicationId){
        kindergartenApplicationFormDao.deleteByApplicationId(applicationId);
    }
}

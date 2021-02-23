package lt.vtmc.kindergarten.service;



import lt.vtmc.kindergarten.dao.ApplicationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class Algorithm {

    @Autowired
    private ApplicationDao applicationDao;



    public void countChildAge(){
        String personalCode = "4904180333";

        String birthdayYear = personalCode.substring(1,3);
        String birthdayMonth = personalCode.substring(4,5);
        String birthdayDay = personalCode.substring(6,7);

        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedString = localDate.format(formatter);
        String year = formattedString.substring(4,8);
        String day = formattedString.substring(0,1);
        String month = formattedString.substring(4,5);



    }


}

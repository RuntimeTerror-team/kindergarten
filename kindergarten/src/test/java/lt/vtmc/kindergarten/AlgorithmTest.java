package lt.vtmc.kindergarten;

import io.swagger.models.auth.In;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@DisplayName("When running algorithm")
public class AlgorithmTest {


    @Test
    @DisplayName("counting Child Age")
    public void countChildAge() {

        String personalCode = "61501223111";

        String birthdayYear = personalCode.substring(1, 3);
        String birthdayMonth = personalCode.substring(3, 5);
        String birthdayDay = personalCode.substring(5, 7);

        //SUKARPO DABARTINI LAIKA (METAI, MENUO, DIENA)
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String formattedString = localDate.format(formatter);

        String currentYear = formattedString.substring(2, 4);
        String currentMonth = formattedString.substring(5, 7);
        String currentDay = formattedString.substring(8, 10);


        //PASKAICIUOTAS VAIKO AMZIUS DABAR
        String childAgeInYearsNow = "";
        String childAgeInMonthNow = "";
        String childAgeInDaysNow = "";


        if (Integer.parseInt(currentMonth) == Integer.parseInt(birthdayMonth)) {
            if (Integer.parseInt(currentDay) >= Integer.parseInt(birthdayDay)) {
                childAgeInYearsNow = Integer.toString(Integer.parseInt(currentYear) - Integer.parseInt(birthdayYear));
            } else {
                childAgeInYearsNow = Integer.toString(Integer.parseInt(currentYear) - Integer.parseInt(birthdayYear) - 1);
            }
            childAgeInMonthNow = Integer.toString(Integer.parseInt(currentMonth) - Integer.parseInt(birthdayMonth));
        } else if ((Integer.parseInt(currentMonth) > Integer.parseInt(birthdayMonth))) {
            childAgeInYearsNow = Integer.toString((Integer.parseInt(currentYear) - Integer.parseInt(birthdayYear)));
            childAgeInMonthNow = Integer.toString(Integer.parseInt(currentMonth) - Integer.parseInt(birthdayMonth));
        } else if ((Integer.parseInt(currentMonth) < Integer.parseInt(birthdayMonth))) {
            childAgeInYearsNow = Integer.toString((Integer.parseInt(currentYear) - Integer.parseInt(birthdayYear))-1);
            childAgeInMonthNow = Integer.toString(Integer.parseInt(birthdayMonth) - Integer.parseInt(currentMonth));
        }


        assertEquals("6", childAgeInYearsNow, "age");
        assertEquals("1", childAgeInMonthNow, "month");


        //PASKAICIUOTAS VAIKO AMZIUS ARTIMIAUSIA RUGSEJI
//        if(childAgeMonthInteger > 12){
//            childAgeYearInteger += 1;
//            childAgeYear = Integer.toString(childAgeYearInteger);
//            childAgeMonthInteger = 0;
//            childAgeMonth = Integer.toString(childAgeMonthInteger);
//        } else {
//            childAgeYear = Integer.toString(childAgeYearInteger);
//            childAgeMonth = Integer.toString(childAgeMonthInteger);
//
//        }


//        assertEquals("16", birthdayYear, "Should return the right year");
//        assertEquals("02", birthdayMonth, "Should return the right month");
//        assertEquals("18", birthdayDay, "Should return the right day");
//
////        assertEquals("5", childAgeYear, "Should return the correct child age");
////        assertEquals("0", childAgeMonth, "Should return the correct child age");
//
//        assertEquals("2021/02/23", formattedString, "Should return whole current year");
//        assertEquals("21", currentYear, "Should return the current year");
//        assertEquals("02", currentMonth, "Should return the current month");
//        assertEquals("23", currentDay, "Should return the current day");

    }


}

package lt.vtmc.kindergarten;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@DisplayName("When running algorithm")
public class AlgorithmTest {

    @Test
    @DisplayName("counting Child Age")
    public void countChildAgeInYearsTest() {

        assertEquals(5, countChildAge("61602183111"), "Should return child age in years");
        assertEquals(4, countChildAge("61702183111"), "Should return child age in years");
        assertEquals(3, countChildAge("61802183111"), "Should return child age in years");
        assertEquals(2, countChildAge("61902183111"), "Should return child age in years");
        assertEquals(1, countChildAge("62002183111"), "Should return child age in years");
        assertEquals(0, countChildAge("62102183111"), "Should return child age in years");

    }


    public int countChildAge(String personalCode) {

        int birthdayYear = Integer.parseInt(personalCode.substring(1, 3));

        LocalDate localDate = LocalDate.now();

        int currentYear = localDate.getYear() - 2000;

        return currentYear - birthdayYear;
    }






}

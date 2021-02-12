package lt.vtmc.kindergarten.controller;


import lt.vtmc.kindergarten.domain.CityEnum;
import lt.vtmc.kindergarten.domain.District;
import lt.vtmc.kindergarten.domain.Kindergarten;


public final class KindergartenTestUtil {

        public static District createDistrict(){
            District district = new District();
            district.setTitle("Antakalnis");
           return  district;
        }

        public static Kindergarten createKindergarten(){
            Kindergarten kindergarten = new Kindergarten();
            kindergarten.setTitle("Pušaitė");
            kindergarten.setAddress("Gatves g. 56");
            kindergarten.setCity(CityEnum.VILNIUS);
            kindergarten.setPostalCode("12546");
            kindergarten.setPhoneNumber("862403523");
            kindergarten.setEmail("darzelispusaite@gmail.com");
            kindergarten.setWebsite("www.darzelis.lt");
            kindergarten.setCompanyCode("19555587");
            kindergarten.setDistrict(createDistrict());

            return kindergarten;
        }

}

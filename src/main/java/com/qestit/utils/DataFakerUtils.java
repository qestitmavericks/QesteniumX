package com.qestit.utils;

import com.qestit.constants.FrameworkConstants;
import net.datafaker.Faker;

import java.util.Locale;

public class DataFakerUtils {
    //Java Locale List: https://www.viralpatel.net/java-locale-list-tutorial/
    //private static Faker faker = new Faker(new Locale("de")); //Germany
    private static Faker faker = null; //English US

    public static Faker getFaker() {
        if (faker == null) {
            faker = new Faker(new Locale(FrameworkConstants.LOCATE));
        }
        return faker;
    }

    public static void setFaker(Faker faker) {
        DataFakerUtils.faker = faker;
    }

    public static void setLocate(String LocateName) {
        faker = new Faker(new Locale(LocateName));
    }
    
    public static String generateFakeEmail() {
    	// Set the Faker instance before generating a fake email
    	DataFakerUtils.setFaker(DataFakerUtils.getFaker());
        if (faker == null) {
            throw new IllegalStateException("Faker instance has not been initialized. Call setFaker or getFaker first.");
        }
        return faker.internet().emailAddress();
    }

}

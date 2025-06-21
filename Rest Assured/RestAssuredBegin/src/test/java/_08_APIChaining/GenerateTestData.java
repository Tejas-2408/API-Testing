package _08_APIChaining;

import org.testng.annotations.Test;
import com.github.javafaker.Faker;

public class GenerateTestData {

	@Test
	void fakDataGenerator() {
		Faker faker = new Faker();
		
		String name = faker.name().fullName();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		
		String email = faker.internet().emailAddress();
		String email2 = faker.internet().safeEmailAddress();
		
		String password = faker.internet().password();
		String phoneNo = faker.phoneNumber().cellPhone();
		
		System.out.println("Full Name: "+name);
		System.out.println("First Name: "+firstName);
		System.out.println("Last Name: "+lastName);
		System.out.println("Email: "+email);
		System.out.println("Email2: "+email2);
		System.out.println("password: "+password);
		System.out.println("Phone Number: "+phoneNo);
		
	}
}

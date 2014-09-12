package com.github.jdk.jdk8;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

import com.github.jdk.jdk8.PersonT.Gender;

/**
 * 
 * @author see
 *         http://technology.amis.nl/2013/10/05/java-8-collection-enhancements-leveraging-lambda-expressions-or-how-java-emulates-sql/
 *
 */
public class HowJavaEmulaesSql {

	public static void main(String[] args) {
		List<PersonT> people = new ArrayList<PersonT>();

		people.add(new PersonT("Louise", "Smith", "Dallas", 16000, LocalDate.of(1943, Month.NOVEMBER, 1), PersonT.Gender.FEMALE));
		people.add(new PersonT("Tobias", "Jellema", "Hagestein", 4, LocalDate.of(2000, Month.OCTOBER, 30), PersonT.Gender.MALE));
		people.add(new PersonT("Mike", "Smith", "Melbourne", 2000, LocalDate.of(1943, Month.NOVEMBER, 1), PersonT.Gender.MALE));
		people.add(new PersonT("Mike", "Weber", "Pretoria", 4411, LocalDate.of(1961, Month.DECEMBER, 5), PersonT.Gender.MALE));
		people.add(new PersonT("John", "Smith", "London", 9000, LocalDate.of(1975, Month.JANUARY, 11), PersonT.Gender.MALE));
		people.add(new PersonT("John", "Williams", "Dublin", 900, LocalDate.of(1985, Month.APRIL, 19), PersonT.Gender.MALE));
		people.add(new PersonT("Anna", "Kolokova", "Kiev", 6000, LocalDate.of(1983, Month.JULY, 14), PersonT.Gender.FEMALE));

		System.out.println(">>>>  List all employees");
		people.forEach(System.out::println);
		System.out.println(">>>>  List all employees");

		/*
		 * The first example in SQL: find the sum of all the salaries of all the
		 * females.
		 * 
		 * select sum(salary) from people where gender = 'FEMALE' the equivalent
		 * in Java:
		 */
		int sum = people.parallelStream().filter(p -> p.getGender() == PersonT.Gender.FEMALE).mapToInt(p -> p.getSalary()).sum();
		System.out.println(" sum :" + sum);

		/*
		 * select distinct(firstName) from people where gender = 'MALE'
		 */
		List<PersonT> collect = people.parallelStream().filter(p -> p.getGender() == PersonT.Gender.MALE).distinct().collect(Collectors.toList());
		collect.forEach(System.out::println);

		/*
		 * select firstName||' '||lastName from people where age < 40 order by
		 * age
		 */
		List<String> collect2 = people.parallelStream().filter(p -> p.getAge() < 40).sorted((a, b) -> a.getAge() - b.getAge()).map(p -> p.getFirstName() + p.getLastName()).collect(Collectors.toList());
		collect2.forEach(System.out::println);

		/*
		 * select listagg( firstName, ',') from ( select distinct firstName from
		 * people where gender = 'MALE' )
		 */

		Optional<String> reduce = people.parallelStream().filter(p -> p.getGender() == PersonT.Gender.MALE).map(p -> p.getFirstName()).distinct().reduce((a, b) -> a + "," + b);
		System.out.println(reduce);

		/*
		 * select gender , sum(age) as aggregated_age from people group by
		 * gender
		 */
		Map<PersonT.Gender, List<PersonT>> collect3 = people.stream().collect(Collectors.groupingBy(PersonT::getGender));
		Map<PersonT.Gender, Integer> sumMap = new HashMap<>();
		for (Gender gender : collect3.keySet()) {
			int sum2 = collect3.get(gender).stream().mapToInt(p -> p.getAge()).sum();
			sumMap.put(gender, sum2);
		}

		sumMap.forEach((k, v) -> System.out.println(k + "sum age:" + v));

		/*
		 * select * 
		 * from people
		 *  where age = ( 
		 *  				select max(age) 
		 *                  from people 
		 *                  where gender ='MALE' ) 
		 *       and gender ='MALE'
		 */
		PersonT personT = people.parallelStream().filter(p -> p.getGender() == PersonT.Gender.MALE).max((a,b)-> a.getAge().compareTo(b.getAge())).get();
		System.out.println(personT);
		
		/*
		 * select * 
		 * from people
		 *  where age = ( 
		 *  				select max(age) 
		 *                  from people 
		 *                  where gender ='MALE' ) 
		 *       and gender ='FEMALE'
		 */
		OptionalInt ageMax = people.stream().filter(pp -> pp.getGender() == PersonT.Gender.MALE).mapToInt(pp->pp.getAge()).max();
		System.out.println(ageMax);
		List<PersonT> list = people.stream().filter(p -> p.getGender() == PersonT.Gender.FEMALE && p.getAge() == ageMax.getAsInt() ).collect(Collectors.toList());
		 list.forEach(System.out::println);
		
	}

}

class PersonT {
	public enum Gender {
		MALE, FEMALE
	}

	private String firstName;
	private String lastName;
	private String city;
	private Integer salary;
	private LocalDate dateOfBirth;
	private Gender gender;

	public PersonT(String firstName, String lastName, String city, Integer salary, LocalDate dateOfBirth, Gender gender) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.salary = salary;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		LocalDate now = LocalDate.now();
		long year = ChronoUnit.YEARS.between(this.dateOfBirth, now);

		return Long.valueOf(year).intValue();
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(PersonT.class.getSimpleName()).append(" { ");

		Field[] fields = PersonT.class.getDeclaredFields();
		for (Field field : fields) {
			stringBuffer.append(field.getName()).append("=");
			try {
				stringBuffer.append(field.get(this));
				stringBuffer.append(", ");
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		stringBuffer.deleteCharAt(stringBuffer.lastIndexOf(","));
		stringBuffer.append(" } ");
		return stringBuffer.toString();
	}

}
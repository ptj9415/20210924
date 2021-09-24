package Parktaejoon;

import java.util.Scanner;

public class PersonExe {

	static Scanner scn = new Scanner(System.in);
	private static PersonExe singleton = new PersonExe();
	private Person[] people;

	private PersonExe() {
		people = new Person[100];
		people[0] = new Person("홍길동", Gender.MAN, "010-1234-5678");
		people[1] = new Person("김영희", Gender.WOMAN, "010-9876-5423");
		people[2] = new Student("최길동", Gender.MAN, "010-1515-3737", "서울대학교");
		people[3] = new Worker("홍하나", Gender.WOMAN, "010-1764-2833", "삼성");
		people[4] = new Student("박정현", Gender.WOMAN, "010-4836-2256", "경북대학교");
		people[5] = new Worker("김수진", Gender.MAN, "010-4938-0982", "카카오");
	}

	public static PersonExe getInstance() {
		return singleton;
	}

	public void excute() {
		while (true) {
			System.out.println("---------------------------------------------------------");
			System.out.println("1. 친구추가    2. 친구조회    3. 친구수정    4. 친구삭제    5. 종료");
			System.out.println("---------------------------------------------------------");
			int menu = readInt("사용할 메뉴의 번호를 입력하세요.");
			if (menu == 1) {
				System.out.println("===친구 추가 메뉴입니다.===");
				addPerson();
			} else if (menu == 2) {
				System.out.println("===친구 조회 메뉴입니다.===");
				showPerson();
			} else if (menu == 3) {
				System.out.println("===친구 수정 메뉴입니다.===");
				modifyPerson();
			} else if (menu == 4) {
				System.out.println("===친구 삭제 메뉴입니다.===");
				deletePerson();
			} else if (menu == 5) {
				System.out.println("===프로그램을 종료합니다.===");
				break;
			}
		}
	}

	private void addPerson() {
		System.out.println("추가할 친구를 선택하세요.");
		int choice = readInt("1. 일반 친구   2. 학교 친구   3. 회사 친구");
		String name = readStr("이름을 입력하세요.");
		int gender = readInt("남성이면 1, 여성이면 2를 입력하세요.");
		Gender gend = null;
		if (gender == 1) {
			gend = Gender.MAN;
		}
		if (gender == 2) {
			gend = Gender.WOMAN;
		}
		String phone = readStr("전화번호를 입력하세요.");
		Person person = null;
		if (choice == 1) {
			person = new Person(name, gend, phone);
		} else if (choice == 2) {
			String school = readStr("학교를 입력하세요.");
			person = new Student(name, gend, phone, school);
		} else if (choice == 3) {
			String company = readStr("회사를 입력하세요.");
			person = new Worker(name, gend, phone, company);
		}
		for (int i = 0; i < people.length; i++) {
			if (people[i] == null) {
				people[i] = person;
				break;
			}
		}
	}

	private void showPerson() {
		System.out.println("친구 조회 메뉴입니다.");
		String name;
		String gender;
		Gender gend = null;
		while (true) {
			name = readStr("이름을 입력하세요.");
			gender = readStr("성별을 입력하세요. 남성이면 1, 여성이면 2를 입력하세요.");
			if (gender.equals("1")) {
				gend = Gender.MAN;
			}
			if (gender.equals("2")) {
				gend = Gender.WOMAN;
			}
			boolean nameflag = true;
			boolean genderflag = true;
			if (name.equals(""))
				nameflag = false;
			if (gender.equals(""))
				genderflag = false;
			if ((nameflag || genderflag) == true)
				break;
		}
		for (Person person : people) {
			if (person == null)
				continue;
			if (name.equals("") == false && gender.equals("") == false) {
				if (person.getName().indexOf(name) != -1 && person.getGender() == gend)
					System.out.println(person.toString());
			} else if (name.equals("") == false && gender.equals("") == true) {
				if (person.getName().indexOf(name) != -1)
					System.out.println(person.toString());
			} else if (name.equals("") == true && gender.equals("") == false) {
				if (person.getGender() == gend)
					System.out.println(person.toString());

			}
		}
	}

	private void modifyPerson() {

		System.out.println("===친구 목록===");
		for (int i = 0; i < people.length; i++) {
			if (people[i] != null) {
				System.out.println("[" + i + "] " + people[i].toString());
			}
		}
		int num = readInt("수정할 친구의 번호를 선택하세요.");
		String phone = readStr("변경할 전화번호를 입력하세요.");
		if (phone.equals(""))
			System.out.println("전화번호를 변경하지 않습니다.");
		else
			people[num].setPhone(phone);
		if (people[num] instanceof Student) {
			String school = readStr("변경할 학교를 입력하세요.");
			if (school.equals(""))
				System.out.println("학교를 변경하지 않습니다.");
			else
				((Student) people[num]).setSchool(school);
		}
		if (people[num] instanceof Worker) {
			String worker = readStr("변경할 회사를 입력하세요.");
			if (worker.equals(""))
				System.out.println("회사를 변경하지 않습니다.");
			else
				((Worker) people[num]).setWorker(worker);
		}
	}

	private void deletePerson() {
		System.out.println("[친구 목록]");
		for (int i = 0; i < people.length; i++) {
			if (people[i] != null)
				System.out.println("[" + i + "]" + people[i].toString());
		}
		int num = readInt("삭제할 친구의 번호를 입력하세요.");
		if (people[num] == null) {
			System.out.println("삭제할 친구가 없습니다.");
		} else {
			people[num] = null;
			System.out.println("삭제 완료");
		}
	}

	public static String readStr(String msg) {
		System.out.println(msg);
		return scn.nextLine();
	}

	public static int readInt(String msg) {
		System.out.println(msg);
		int result = 0;
		while (true) {
			String val = scn.nextLine();
			try {
				result = Integer.parseInt(val);
				break;
			} catch (Exception e) {
				System.out.println("잘못된 값을 입력하였습니다. 다시 입력하세요.");
			}
		}
		return result;
	}
}

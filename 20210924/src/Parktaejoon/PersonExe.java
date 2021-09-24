package Parktaejoon;

import java.util.Scanner;

public class PersonExe {

	static Scanner scn = new Scanner(System.in);
	private static PersonExe singleton = new PersonExe();
	private Person[] people;

	private PersonExe() {
		people = new Person[100];
		people[0] = new Person("ȫ�浿", Gender.MAN, "010-1234-5678");
		people[1] = new Person("�迵��", Gender.WOMAN, "010-9876-5423");
		people[2] = new Student("�ֱ浿", Gender.MAN, "010-1515-3737", "������б�");
		people[3] = new Worker("ȫ�ϳ�", Gender.WOMAN, "010-1764-2833", "�Ｚ");
		people[4] = new Student("������", Gender.WOMAN, "010-4836-2256", "��ϴ��б�");
		people[5] = new Worker("�����", Gender.MAN, "010-4938-0982", "īī��");
	}

	public static PersonExe getInstance() {
		return singleton;
	}

	public void excute() {
		while (true) {
			System.out.println("---------------------------------------------------------");
			System.out.println("1. ģ���߰�    2. ģ����ȸ    3. ģ������    4. ģ������    5. ����");
			System.out.println("---------------------------------------------------------");
			int menu = readInt("����� �޴��� ��ȣ�� �Է��ϼ���.");
			if (menu == 1) {
				System.out.println("===ģ�� �߰� �޴��Դϴ�.===");
				addPerson();
			} else if (menu == 2) {
				System.out.println("===ģ�� ��ȸ �޴��Դϴ�.===");
				showPerson();
			} else if (menu == 3) {
				System.out.println("===ģ�� ���� �޴��Դϴ�.===");
				modifyPerson();
			} else if (menu == 4) {
				System.out.println("===ģ�� ���� �޴��Դϴ�.===");
				deletePerson();
			} else if (menu == 5) {
				System.out.println("===���α׷��� �����մϴ�.===");
				break;
			}
		}
	}

	private void addPerson() {
		System.out.println("�߰��� ģ���� �����ϼ���.");
		int choice = readInt("1. �Ϲ� ģ��   2. �б� ģ��   3. ȸ�� ģ��");
		String name = readStr("�̸��� �Է��ϼ���.");
		int gender = readInt("�����̸� 1, �����̸� 2�� �Է��ϼ���.");
		Gender gend = null;
		if (gender == 1) {
			gend = Gender.MAN;
		}
		if (gender == 2) {
			gend = Gender.WOMAN;
		}
		String phone = readStr("��ȭ��ȣ�� �Է��ϼ���.");
		Person person = null;
		if (choice == 1) {
			person = new Person(name, gend, phone);
		} else if (choice == 2) {
			String school = readStr("�б��� �Է��ϼ���.");
			person = new Student(name, gend, phone, school);
		} else if (choice == 3) {
			String company = readStr("ȸ�縦 �Է��ϼ���.");
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
		System.out.println("ģ�� ��ȸ �޴��Դϴ�.");
		String name;
		String gender;
		Gender gend = null;
		while (true) {
			name = readStr("�̸��� �Է��ϼ���.");
			gender = readStr("������ �Է��ϼ���. �����̸� 1, �����̸� 2�� �Է��ϼ���.");
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

		System.out.println("===ģ�� ���===");
		for (int i = 0; i < people.length; i++) {
			if (people[i] != null) {
				System.out.println("[" + i + "] " + people[i].toString());
			}
		}
		int num = readInt("������ ģ���� ��ȣ�� �����ϼ���.");
		String phone = readStr("������ ��ȭ��ȣ�� �Է��ϼ���.");
		if (phone.equals(""))
			System.out.println("��ȭ��ȣ�� �������� �ʽ��ϴ�.");
		else
			people[num].setPhone(phone);
		if (people[num] instanceof Student) {
			String school = readStr("������ �б��� �Է��ϼ���.");
			if (school.equals(""))
				System.out.println("�б��� �������� �ʽ��ϴ�.");
			else
				((Student) people[num]).setSchool(school);
		}
		if (people[num] instanceof Worker) {
			String worker = readStr("������ ȸ�縦 �Է��ϼ���.");
			if (worker.equals(""))
				System.out.println("ȸ�縦 �������� �ʽ��ϴ�.");
			else
				((Worker) people[num]).setWorker(worker);
		}
	}

	private void deletePerson() {
		System.out.println("[ģ�� ���]");
		for (int i = 0; i < people.length; i++) {
			if (people[i] != null)
				System.out.println("[" + i + "]" + people[i].toString());
		}
		int num = readInt("������ ģ���� ��ȣ�� �Է��ϼ���.");
		if (people[num] == null) {
			System.out.println("������ ģ���� �����ϴ�.");
		} else {
			people[num] = null;
			System.out.println("���� �Ϸ�");
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
				System.out.println("�߸��� ���� �Է��Ͽ����ϴ�. �ٽ� �Է��ϼ���.");
			}
		}
		return result;
	}
}

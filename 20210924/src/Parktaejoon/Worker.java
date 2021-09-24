package Parktaejoon;

public class Worker extends Person {
	private String worker;

	public Worker(String name, Gender gender, String phone, String worker) {
		super(name, gender, phone);
		this.worker = worker;
	}

	public String getWorker() {
		return worker;
	}

	public void setWorker(String worker) {
		this.worker = worker;
	}

	@Override
	public String toString() {
		return "Worker [name=" + name + ", gender=" + gender + ", phone=" + phone + ", worker=" + worker + "]";
	}

}

package backend.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import backend.repository.IUserReponsitory;
import backend.repository.UserReponsitory;
import entity.Project;
import utils.ScannerUtils;

public class UserService implements IUserService {
	private IUserReponsitory userReponsitory;

	public UserService() throws FileNotFoundException, IOException {
		userReponsitory = new UserReponsitory();
	}

	private boolean chuanHoaEmail(String email) {
//		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@vti.com.vn";
		// nguyen.vannam@vti.com.vn
		// Chú thích : \\w : đại diện cho bảng chữ cái và số
		// $ : đánh dấu kết thúc của 1 dòng
		// ^ : bắt đầu 1 dòng hoặc 1 chuỗi
		String email1 = email;
		Boolean check = email1.matches(EMAIL_REGEX);
		return check;
	}

	private boolean checkPassword(String pass) {
		// Password chi trog khoang tu 6-12 ky tuu
		// Password phai co 1 chu cai viet hoa
		boolean check = false;
		boolean check2 = false;

		for (int i = 0; i < pass.length(); i++) {
			if (Character.isUpperCase(pass.charAt(i)))
				check = true;
		}

		if (pass.length() > 5 && pass.length() < 13 && check == true) {
			check2 = true;
		}

		return check2;
	}

	public void registerManager(String firstName, String lastName, String phone, String email, String pass,
			int expInYear) throws SQLException {

		boolean check = false;
		String phone1 = phone;
		String email1 = email;
		int expInYear1 = expInYear;
		String pass1 = pass;

		while (!check) {
			if (phone1.length() >= 9 && phone1.length() <= 12) {
				check = true;
			} else {
				System.out.println("So dien thoai nhap chua dung dinh dang(9-12 so). Nhap lai!");
				phone1 = ScannerUtils.inputString("Vui long nhap!");
			}
		}
		check = false;
		while (!check) {
			if (chuanHoaEmail(email1)) {
				check = true;
			} else {
				System.out.println("Email nhap chua dung dinh dang. Nhap lai!");
				System.out.println("Nhap theo mau :  nguyen.vannam@vti.com.vn");
				email1 = ScannerUtils.inputString("Vui long nhap!");
			}
		}
		check = false;
		while (!check) {
			if (expInYear1 >= 0 && expInYear1 <= 20) {
				check = true;
			} else {
				System.out.println("Nhập số nguyên từ 0 - 20");
				System.out.println("So nam quan ly nhap chua dung. Nhap lai!");
				expInYear1 = ScannerUtils.inputInt("Vui long nhap!");
			}
		}
		check = false;
		while (!check) {
			if (checkPassword(pass1)) {
				check = true;
			} else {
				System.out.println("Pass nhập sai định dạng. Nhap lai!");
				System.out.println("Độ dài password từ 7-13 ký tự và có ít nhất 1 chữ cái viết hoa");
				pass1 = ScannerUtils.inputString("Vui long nhap!");
			}
		}

		userReponsitory.registerManager(firstName, lastName, phone1, email1, pass1, expInYear1);
	}

	public void registerEmployee(String firstName, String lastName, String phone, String email, String pass,
			String projectName, String proSkill) throws SQLException {
		boolean check = false;
		String phone1 = phone;
		String email1 = email;
		String pass1 = pass;
		String projectName1 = projectName;

		while (!check) {
			if (phone1.length() >= 9 && phone1.length() <= 12) {
				check = true;
			} else {
				System.out.println("So dien thoai nhap chua dung dinh dang(9-12 so). Nhap lai!");
				phone1 = ScannerUtils.inputString("Vui long nhap!");
			}
		}
		check = false;
		while (!check) {
			if (chuanHoaEmail(email1)) {
				check = true;
			} else {
				System.out.println("Email nhap chua dung dinh dang. Nhap lai!");
				System.out.println("Nhap theo mau :  nguyen.vannam@vti.com.vn");
				email1 = ScannerUtils.inputString("Vui long nhap!");
			}
		}
		check = false;
		while (!check) {
			if (checkPassword(pass1)) {
				check = true;
			} else {
				System.out.println("Pass nhập sai định dạng vui lòng nhập lại!");
				System.out.println("Độ dài password từ 7-13 ký tự và có ít nhất 1 chữ cái viết hoa");
				pass1 = ScannerUtils.inputString("Vui long nhap!");
			}
		}
		check = false;
		while (!check) {
			if (projectName1.equals("Testing System")) {
				check = true;
			} else if (projectName1.equals("CRM"))
				check = true;
			else if (projectName1.equals("TimeSheet"))
				check = true;
			else {
				System.out.println("Chi duoc nhap 1 trong 3 loai projectName tren. Nhap lai!");
				projectName1 = ScannerUtils.inputString("Vui long nhap!");
			}
		}

		userReponsitory.registerEmployee(firstName, lastName, phone1, email1, pass1, projectName, proSkill);

	}

	public boolean isUserExists(String email, String pass) throws SQLException {

		String email1 = email;
		String pass1 = pass;
		boolean check = false;

		while (!check) {
			if (chuanHoaEmail(email1)) {
				check = true;
			} else {
				System.out.println("Email nhap chua dung dinh dang. Nhap lai!");
				email1 = ScannerUtils.inputString("Vui long nhap!");
			}
		}
		check = false;
		while (!check) {
			if (checkPassword(pass1)) {
				check = true;
			} else {
				System.out.println("Pass nhập sai định dạng. Nhap lai!");
				pass1 = ScannerUtils.inputString("Vui long nhap!");
			}
		}

		return userReponsitory.isUserExists(email1, pass1);
	}

	public boolean isEmployeeExists(String email, String pass) throws SQLException {

		String email1 = email;
		String pass1 = pass;
		boolean check = false;

		while (!check) {
			if (chuanHoaEmail(email1)) {
				check = true;
			} else {
				System.out.println("Email nhap chua dung dinh dang. Nhap lai!");
				email1 = ScannerUtils.inputString("Vui long nhap!");
			}
		}
		check = false;
		while (!check) {
			if (checkPassword(pass1)) {
				check = true;
			} else {
				System.out.println("Pass nhập sai định dạng. Nhap lai!");
				pass1 = ScannerUtils.inputString("Vui long nhap!");
			}
		}

		return userReponsitory.isEmployeeExists(email1, pass1);
	}

	public boolean isProjectExists(int id) throws SQLException {

		return userReponsitory.isProjectExists(id);
	}

	public List<Project> ShowUserInProject() throws SQLException {

		return ((UserReponsitory) userReponsitory).ShowUserInProject();
	}
	
	public List<Project> ShowManager() throws SQLException {

		return ((UserReponsitory) userReponsitory).ShowUserInProject();
	}

}

package com.bh.CouponProject3.clr;

import javax.security.auth.login.LoginException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.bh.CouponProject3.beans.Company;
import com.bh.CouponProject3.beans.Customer;
import com.bh.CouponProject3.exceptions.CompanyException;
import com.bh.CouponProject3.exceptions.CouponException;
import com.bh.CouponProject3.exceptions.CustomerException;
import com.bh.CouponProject3.exceptions.SecurityException;
import com.bh.CouponProject3.security.login.ClientType;
import com.bh.CouponProject3.security.login.LoginManager;
import com.bh.CouponProject3.services.AdminService;
import com.bh.CouponProject3.utils.ArtUtils;
import com.bh.CouponProject3.utils.FactoryUtils;

import lombok.RequiredArgsConstructor;

@Component
@Order(3)
@RequiredArgsConstructor
public class C_AdminTest implements CommandLineRunner {

	private final LoginManager loginManager;

	@Override
	public void run(String... args)
			throws LoginException, CompanyException, CustomerException, SecurityException, CouponException {

		ArtUtils.printAdminHeadline();
		AdminService adminService = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.ADMIN);

		Company Company1 = FactoryUtils.BringOneCompany();
		adminService.addCompany(Company1);
		ArtUtils.insertToTable("Add Company Id " + Company1.getId(), adminService.getAllCompanies());

		Company Company2 = FactoryUtils.BringAlreadyExistsCompanyName();
		ArtUtils.printXxsHeadLine("Add Company \"" + Company2.getName() + "\" with exception", 1);
		try {
			adminService.addCompany(Company2);
		} catch (CompanyException e) {
			System.out.println(e.getMessage());
		}

		Company1.setEmail("IAm@NewEmail.com");
		adminService.updateCompany(Company1);
		ArtUtils.insertToTable("Update Company Id " + Company1.getId(), adminService.getAllCompanies());

		Company2.setName("Booza");
		ArtUtils.printXxsHeadLine("Update Company \"" + Company2.getName() + "\" with exception ", 1);
		try {
			adminService.updateCompany(Company2);
		} catch (CompanyException e) {
			System.out.println(e.getMessage());
		}

		int idToDelete = 2;
		adminService.deleteCompany(idToDelete);
		ArtUtils.insertToTable("Delete Company Id " + idToDelete, adminService.getAllCompanies());

		idToDelete = 50;
		ArtUtils.printXxsHeadLine("Delete Company " + idToDelete + " with exception ", 1);
		try {
			adminService.deleteCompany(idToDelete);
		} catch (CompanyException e) {
			System.out.println(e.getMessage());
		}

		int GetId = 3;
		ArtUtils.printVvsHeadLine("Get Company Id " + GetId, 1);
		Company company = adminService.getOneCompany(GetId);
		System.out.println(company);

		GetId = 90;
		ArtUtils.printXxsHeadLine("Get Company Id " + GetId + " with exception ", 1);
		try {
			adminService.getOneCompany(GetId);
		} catch (CompanyException e) {
			System.out.println(e.getMessage());
		}
		Customer customer1 = FactoryUtils.BringOneCustomer();
		adminService.addCustomer(customer1);
		ArtUtils.insertToTable("Add customer Id " + customer1.getId(), adminService.getAllCustomers());

		Customer customer2 = FactoryUtils.BringOneCustomer();
		ArtUtils.printXxsHeadLine(
				"Add customer " + customer2.getFirstName() + " " + customer2.getLastName() + " with exception ", 1);
		try {
			adminService.addCustomer(customer2);
		} catch (CustomerException e) {
			System.out.println(e.getMessage());
		}

		customer1.setEmail("UpdatedEmail@g.fr");
		adminService.updateCustomer(customer1);
		ArtUtils.insertToTable("Update customer Id " + customer1.getId(), adminService.getAllCustomers());

		ArtUtils.printXxsHeadLine(
				"Update customer " + customer2.getFirstName() + " " + customer2.getLastName() + " with exception", 1);
		try {
			adminService.updateCustomer(customer2);
		} catch (CustomerException e) {
			System.out.println(e.getMessage());
		}
		idToDelete = 3;
		adminService.deleteCustomer(idToDelete);
		ArtUtils.insertToTable("Delete customer Id " + idToDelete, adminService.getAllCustomers());

		idToDelete = 80;
		ArtUtils.printXxsHeadLine("Delete customer Id " + idToDelete + " with exception ", 1);
		try {
			adminService.deleteCustomer(idToDelete);
		} catch (CustomerException e) {
			System.out.println(e.getMessage());
		}
		GetId = 4;
		ArtUtils.printVvsHeadLine("Get One customer", 1);
		Customer customer = adminService.getOneCustomer(GetId);
		System.out.println(customer);

		GetId = 55;
		ArtUtils.printXxsHeadLine("Get One customer Id " + GetId + " with exception ", 1);
		try {
			adminService.getOneCustomer(GetId);
		} catch (CustomerException e) {
			System.out.println(e.getMessage());
		}
		ArtUtils.insertToTable("Get all customers", adminService.getAllCustomers());
	}

}
